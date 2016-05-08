/******************************************************************************
 *
 *  Copyright 2011-2012 Tavendo GmbH
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 ******************************************************************************/

package teamdobby.dobby.SecureWebSockets;
//VIEWMODEL

import android.net.SSLCertificateSocketFactory;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.net.URI;

import javax.net.SocketFactory;

import teamdobby.dobby.R;
import teamdobby.dobby.SecureWebSockets.WebSocket.WebSocketConnectionObserver.WebSocketCloseNotification;
import teamdobby.dobby.SecureWebSockets.WebSocketMessage.WebSocketCloseCode;

/**
 * Created by Marie on 11.04.2016.
 */
public class WebSocketConnection implements WebSocket {
    private final Handler mHandler;

    private WebSocketReader mWebSocketReader;
    private WebSocketWriter mWebSocketWriter;

    private Socket mSocket;
    private SocketThread mSocketThread;

    private URI mWebSocketURI;
    private String[] mWebSocketSubprotocols;

    private WeakReference<WebSocketConnectionObserver> mWebSocketConnectionObserver;

    private WebSocketOptions mWebSocketOptions;
    private boolean mPreviousConnection = false;

    public WebSocketConnection() {
        Log.d(WebSocketStrings.TAG, WebSocketStrings.Conn_created);

        this.mHandler = new ThreadHandler(this);
    }

    // Forward to the writer thread
    public void sendTextMessage(String payload) {
        mWebSocketWriter.forward(new WebSocketMessage.TextMessage(payload));
    }

    public void sendRawTextMessage(byte[] payload) {
        mWebSocketWriter.forward(new WebSocketMessage.RawTextMessage(payload));
    }

    public void sendBinaryMessage(byte[] payload) {
        mWebSocketWriter.forward(new WebSocketMessage.BinaryMessage(payload));
    }

    public boolean isConnected() {
        return mSocket != null && mSocket.isConnected() && !mSocket.isClosed();
    }

    private void failConnection(WebSocketCloseNotification code, String reason) {
        Log.d(WebSocketStrings.TAG, WebSocketStrings.Conn_fail_1 + code + WebSocketStrings.Conn_fail_2 + reason);

        if (mWebSocketReader != null) {
            mWebSocketReader.quit();

            try {
                mWebSocketReader.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            Log.d(WebSocketStrings.TAG, WebSocketStrings.mReader_null);
        }

        if (mWebSocketWriter != null) {
            mWebSocketWriter.forward(new WebSocketMessage.Quit());

            try {
                mWebSocketWriter.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            Log.d(WebSocketStrings.TAG, WebSocketStrings.mWriter_null);
        }

        if (mSocket != null) {
            mSocketThread.getHandler().post(new Runnable() {

                @Override
                public void run() {
                    mSocketThread.stopConnection();
                }
            });
        } else {
            Log.d(WebSocketStrings.TAG, WebSocketStrings.mTransportChannel_null);
        }

        mSocketThread.getHandler().post(new Runnable() {

            @Override
            public void run() {
                Looper.myLooper().quit();
            }
        });

        onClose(code, reason);

        Log.d(WebSocketStrings.TAG, WebSocketStrings.worker_stopped);
    }

    public void connect(URI webSocketURI, WebSocket.WebSocketConnectionObserver connectionObserver) throws WebSocketException {
        connect(webSocketURI, connectionObserver, new WebSocketOptions());
    }

    public void connect(URI webSocketURI, WebSocket.WebSocketConnectionObserver connectionObserver, WebSocketOptions options) throws WebSocketException {
        connect(webSocketURI, null, connectionObserver, options);
    }

    public void connect(URI webSocketURI, String[] subprotocols, WebSocket.WebSocketConnectionObserver connectionObserver, WebSocketOptions options) throws WebSocketException {
        if (mSocket != null && mSocket.isConnected()) {
            throw new WebSocketException(WebSocketStrings.Conn_exists);
        }

        if (webSocketURI == null) {
            throw new WebSocketException(WebSocketStrings.Uri_null);
        } else {
            this.mWebSocketURI = webSocketURI;
            if (!mWebSocketURI.getScheme().equals(WebSocketStrings.WS_URI_SCHEME) && !mWebSocketURI.getScheme().equals(WebSocketStrings.WSS_URI_SCHEME)) {
                throw new WebSocketException(WebSocketStrings.Uri_unsupported);
            }

            this.mWebSocketSubprotocols = subprotocols;
            this.mWebSocketConnectionObserver = new WeakReference<WebSocketConnectionObserver>(connectionObserver);
            this.mWebSocketOptions = new WebSocketOptions(options);

            connect();
        }
    }

    public void disconnect() {
        if (mWebSocketWriter != null && mWebSocketWriter.isAlive()) {
            mWebSocketWriter.forward(new WebSocketMessage.Close());
        } else {
            Log.d(WebSocketStrings.TAG, WebSocketStrings.close_null);
        }

        this.mPreviousConnection = false;
    }

    /**
     * Reconnect to the server with the latest options
     * @return true if reconnection performed
     */
    public boolean reconnect() {
        if (!isConnected() && (mWebSocketURI != null)) {
            connect();
            return true;
        }
        return false;
    }

    private void connect() {
        mSocketThread = new SocketThread(mWebSocketURI, mWebSocketOptions);

        mSocketThread.start();
        synchronized (mSocketThread) {
            try {
                mSocketThread.wait();
            } catch (InterruptedException e) {
            }
        }
        mSocketThread.getHandler().post(new Runnable() {

            @Override
            public void run() {
                mSocketThread.startConnection();
            }
        });

        synchronized (mSocketThread) {
            try {
                mSocketThread.wait(mWebSocketOptions.getSocketConnectTimeout());
            } catch (InterruptedException e) {
            }
        }

        this.mSocket = mSocketThread.getSocket();

        if (mSocket == null) {
            onClose(WebSocketCloseNotification.CANNOT_CONNECT, mSocketThread.getFailureMessage());
        } else if (mSocket.isConnected()) {
            try {
                createReader();
                createWriter();

                WebSocketMessage.ClientHandshake clientHandshake = new WebSocketMessage.ClientHandshake(mWebSocketURI, null, mWebSocketSubprotocols);
                mWebSocketWriter.forward(clientHandshake);
            } catch (Exception e) {
                onClose(WebSocketCloseNotification.INTERNAL_ERROR, e.getLocalizedMessage());
            }
        } else {
            onClose(WebSocketCloseNotification.CANNOT_CONNECT, WebSocketStrings.Conn_failed);
        }
    }

    /**
     * Perform reconnection
     *
     * @return true if reconnection was scheduled
     */
    protected boolean scheduleReconnect() {
        /**
         * Reconnect only if:
         *  - connection active (connected but not disconnected)
         *  - has previous success connections
         *  - reconnect interval is set
         */
        int interval = mWebSocketOptions.getReconnectInterval();
        boolean shouldReconnect = mSocket.isConnected() && mPreviousConnection && (interval > 0);
        if (shouldReconnect) {
            Log.d(WebSocketStrings.TAG, WebSocketStrings.Reconn_scheduled);
            mHandler.postDelayed(new Runnable() {

                public void run() {
                    Log.d(WebSocketStrings.TAG, WebSocketStrings.Reconn_started);
                    reconnect();
                }
            }, interval);
        }
        return shouldReconnect;
    }

    /**
     * Common close handler
     *
     * @param code       Close code.
     * @param reason     Close reason (human-readable).
     */
    private void onClose(WebSocketCloseNotification code, String reason) {
        boolean reconnecting = false;

        if ((code == WebSocketCloseNotification.CANNOT_CONNECT) || (code == WebSocketCloseNotification.CONNECTION_LOST)) {
            reconnecting = scheduleReconnect();
        }

        WebSocket.WebSocketConnectionObserver webSocketObserver = mWebSocketConnectionObserver.get();
        if (webSocketObserver != null) {
            try {
                if (reconnecting) {
                    webSocketObserver.onClose(WebSocketConnectionObserver.WebSocketCloseNotification.RECONNECT, reason);
                } else {
                    webSocketObserver.onClose(code, reason);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Log.d(WebSocketStrings.TAG, WebSocketStrings.observer_null);
        }
    }

    protected void processAppMessage(Object message) {
    }

    /**
     * Create WebSockets background writer.
     */
    protected void createWriter() {
        mWebSocketWriter = new WebSocketWriter(mHandler, mSocket, mWebSocketOptions, WebSocketStrings.WS_WRITER);
        mWebSocketWriter.start();

        synchronized (mWebSocketWriter) {
            try {
                mWebSocketWriter.wait();
            } catch (InterruptedException e) {
            }
        }

        Log.d(WebSocketStrings.TAG, WebSocketStrings.writer_created);
    }

    /**
     * Create WebSockets background reader.
     */
    protected void createReader() {

        mWebSocketReader = new WebSocketReader(mHandler, mSocket, mWebSocketOptions, WebSocketStrings.WS_READER);
        mWebSocketReader.start();

        synchronized (mWebSocketReader) {
            try {
                mWebSocketReader.wait();
            } catch (InterruptedException e) {
            }
        }

        Log.d(WebSocketStrings.TAG, WebSocketStrings.reader_created);
    }

    private void handleMessage(Message message) {
        WebSocket.WebSocketConnectionObserver webSocketObserver = mWebSocketConnectionObserver.get();

        if (message.obj instanceof WebSocketMessage.TextMessage) {
            WebSocketMessage.TextMessage textMessage = (WebSocketMessage.TextMessage) message.obj;

            if (webSocketObserver != null) {
                webSocketObserver.onTextMessage(textMessage.mPayload);
            } else {
                Log.d(WebSocketStrings.TAG, WebSocketStrings.onTextMessage_null);
            }

        } else if (message.obj instanceof WebSocketMessage.RawTextMessage) {
            WebSocketMessage.RawTextMessage rawTextMessage = (WebSocketMessage.RawTextMessage) message.obj;

            if (webSocketObserver != null) {
                webSocketObserver.onRawTextMessage(rawTextMessage.mPayload);
            } else {
                Log.d(WebSocketStrings.TAG, WebSocketStrings.onRawTextMessage_null);
            }

        } else if (message.obj instanceof WebSocketMessage.BinaryMessage) {
            WebSocketMessage.BinaryMessage binaryMessage = (WebSocketMessage.BinaryMessage) message.obj;

            if (webSocketObserver != null) {
                webSocketObserver.onBinaryMessage(binaryMessage.mPayload);
            } else {
                Log.d(WebSocketStrings.TAG, WebSocketStrings.onBinaryMessage_null);
            }

        } else if (message.obj instanceof WebSocketMessage.Ping) {
            WebSocketMessage.Ping ping = (WebSocketMessage.Ping) message.obj;
            Log.d(WebSocketStrings.TAG, WebSocketStrings.ping_received);

            WebSocketMessage.Pong pong = new WebSocketMessage.Pong();
            pong.mPayload = ping.mPayload;
            mWebSocketWriter.forward(pong);

        } else if (message.obj instanceof WebSocketMessage.Pong) {
            WebSocketMessage.Pong pong = (WebSocketMessage.Pong) message.obj;

            Log.d(WebSocketStrings.TAG, WebSocketStrings.pong_received + pong.mPayload);

        } else if (message.obj instanceof WebSocketMessage.Close) {
            WebSocketMessage.Close close = (WebSocketMessage.Close) message.obj;

            Log.d(WebSocketStrings.TAG, WebSocketStrings.close_received + "(" + close.getCode() + " - " + close.getReason() + ")");

            mWebSocketWriter.forward(new WebSocketMessage.Close(WebSocketCloseCode.NORMAL));

        } else if (message.obj instanceof WebSocketMessage.ServerHandshake) {
            WebSocketMessage.ServerHandshake serverHandshake = (WebSocketMessage.ServerHandshake) message.obj;

            Log.d(WebSocketStrings.TAG, WebSocketStrings.handshake_received);

            if (serverHandshake.mSuccess) {
                if (webSocketObserver != null) {
                    webSocketObserver.onOpen();
                } else {
                    Log.d(WebSocketStrings.TAG, WebSocketStrings.onOpen_null);
                }
                mPreviousConnection = true;
            }

        } else if (message.obj instanceof WebSocketMessage.ConnectionLost) {
            //			WebSocketMessage.ConnectionLost connectionLost = (WebSocketMessage.ConnectionLost) message.obj;
            failConnection(WebSocketCloseNotification.CONNECTION_LOST, WebSocketStrings.Conn_lost);

        } else if (message.obj instanceof WebSocketMessage.ProtocolViolation) {
            //			WebSocketMessage.ProtocolViolation protocolViolation = (WebSocketMessage.ProtocolViolation) message.obj;
            failConnection(WebSocketCloseNotification.PROTOCOL_ERROR, WebSocketStrings.protocol_viol);

        } else if (message.obj instanceof WebSocketMessage.Error) {
            WebSocketMessage.Error error = (WebSocketMessage.Error) message.obj;
            failConnection(WebSocketCloseNotification.INTERNAL_ERROR, WebSocketStrings.intern_err + "(" + error.mException.toString() + ")");

        } else if (message.obj instanceof WebSocketMessage.ServerError) {
            WebSocketMessage.ServerError error = (WebSocketMessage.ServerError) message.obj;
            failConnection(WebSocketCloseNotification.SERVER_ERROR, WebSocketStrings.serv_err + error.mStatusCode + " (" + error.mStatusMessage + ")");

        } else {
            processAppMessage(message.obj);

        }
    }

    public static class SocketThread extends Thread {
        private final URI mWebSocketURI;
        private Socket mSocket = null;
        private String mFailureMessage = null;
        private Handler mHandler;

        public SocketThread(URI uri, WebSocketOptions options) {
            this.setName(WebSocketStrings.WS_CONNECTOR);

            this.mWebSocketURI = uri;
        }

        @Override
        public void run() {
            Looper.prepare();
            this.mHandler = new Handler();
            synchronized (this) {
                notifyAll();
            }

            Looper.loop();
            Log.d(WebSocketStrings.TAG, WebSocketStrings.sock_exit);
        }

        public void startConnection() {
            try {
                String host = mWebSocketURI.getHost();
                int port = mWebSocketURI.getPort();

                if (port == -1) {
                    if (mWebSocketURI.getScheme().equals(WebSocketStrings.WSS_URI_SCHEME)) {
                        port = 443;
                    } else {
                        port = 80;
                    }
                }

                SocketFactory factory = null;
                if (mWebSocketURI.getScheme().equalsIgnoreCase(WebSocketStrings.WSS_URI_SCHEME)) {
                    factory = SSLCertificateSocketFactory.getDefault();
                } else {
                    factory = SocketFactory.getDefault();
                }

                // Do not replace host string with InetAddress or you lose automatic host name verification
                this.mSocket = factory.createSocket(host, port);
            } catch (IOException e) {
                this.mFailureMessage = e.getLocalizedMessage();
            }

            synchronized (this) {
                notifyAll();
            }
        }

        public void stopConnection() {
            try {
                mSocket.close();
                this.mSocket = null;
            } catch (IOException e) {
                this.mFailureMessage = e.getLocalizedMessage();
            }
        }

        public Handler getHandler() {
            return mHandler;
        }
        public Socket getSocket() {
            return mSocket;
        }
        public String getFailureMessage() {
            return mFailureMessage;
        }
    }

    private static class ThreadHandler extends Handler {
        private final WeakReference<WebSocketConnection> mWebSocketConnection;

        public ThreadHandler(WebSocketConnection webSocketConnection) {
            super();
            this.mWebSocketConnection = new WeakReference<WebSocketConnection>(webSocketConnection);
        }

        @Override
        public void handleMessage(Message message) {
            WebSocketConnection webSocketConnection = mWebSocketConnection.get();
            if (webSocketConnection != null) {
                webSocketConnection.handleMessage(message);
            }
        }
    }
}
