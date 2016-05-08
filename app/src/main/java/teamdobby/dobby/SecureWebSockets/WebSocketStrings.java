package teamdobby.dobby.SecureWebSockets;

/**
 * Created by Marie on 07.05.2016.
 */
public class WebSocketStrings {
    public static final String TAG = "SecureWebSockets";

    /* for WebSocketReader */
    public static final String wreader_created = "WebSocket reader created.";
    public static final String reader_run = "WebSocker reader running.";
    public static final String read_fail = "WebSocketReader read() failed.";
    public static final String reader_ended = "WebSocket reader ended.";

    public static final String quit = "quit";
    public static final String logik_err = "logic error";

    public static final String rsv_notNull = "RSV != 0 and no extension negotiated";
    public static final String utf_uni_err = "UTF-8 text message payload ended within Unicode code point";

    public static final String masked_servFrame = "masked server frame";
    public static final String dataFrame_opc = "data frame using reserved opcode ";
    public static final String frag_contrFrame = "fragmented control frame";

    public static final String contr_framePay = "control frame with payload length > 125 octets";
    public static final String contr_frameOpc = "control frame using reserved opcode ";

    public static final String frameLenght_tooLarge = "frame payload too large";
    public static final String msg_tooLarge = "message payload too large";

    public static final String cls_contrFrame = "received close control frame with payload len 1";
    public static final String contFrame = "received continuation data frame outside fragmented message";
    public static final String nonContFrame = "received non-continuation data frame while inside fragmented message";

    public static final String inv_frameLenght_notMinEnc = "invalid data frame length (not using minimal length encoding)";
    public static final String inv_frameLenght = "invalid data frame length (> 2^63)";
    public static final String cls_inv = "invalid close code ";
    public static final String clsRsn_inv = "invalid close reasons (not UTF-8)";
    public static final String msg_inv = "invalid UTF-8 in text message payload";

    public static final String run_connLost = "run() : ConnectionLost";
    public static final String run_wExc = "run() : WebSocketException";
    public static final String run_Exc = "run() : Exception";

    /* for both Writer and Reader */
    public static final String run_sockExc = "run() : SocketException";
    public static final String run_ioExc = "run() : IOException";

    /* for WebSocketWriter */
    public static final String wwriter_created = "WebSocket writer created.";
    public static final String writer_ended = "WebSocket writer ended.";
    public static final String writer_run = "WebSocker writer running.";
    public static final String unkwn_msg = "unknown message received by WebSocketWriter";

    public static final String applBuff_get = "GET ";
    public static final String applBuff_http = " HTTP/1.1";
    public static final String applBuff_host = "Host: ";
    public static final String applBuff_upWeb = "Upgrade: WebSocket";
    public static final String applBuff_conn = "Connection: Upgrade";
    public static final String applBuff_secKey = "Sec-WebSocket-Key: ";
    public static final String applBuff_orig = "Origin: ";
    public static final String applBuff_secProt = "Sec-WebSocket-Protocol: ";
    public static final String applBuff_secVers = "Sec-WebSocket-Version: ";

    public static final String cls_tooLarge = "close payload exceeds 125 octets";
    public static final String pong_tooLarge = "pong payload exceeds 125 octets";
    public static final String msgPay_tooLarge = "message payload exceeds payload limit";

    /* for WebSocketConnection */
    public static final String WS_URI_SCHEME = "ws";
    public static final String WSS_URI_SCHEME = "wss";
    public static final String WS_WRITER = "WebSocketWriter";
    public static final String WS_READER = "WebSocketReader";

    public static final String Conn_created = "WebSocket connection created.";
    public static final String Conn_exists = "already connected";
    public static final String Conn_fail_1 = "fail connection [code = ";
    public static final String Conn_fail_2 = ", reason = ";
    public static final String Conn_failed = "could not connect to WebSockets server";
    public static final String Conn_lost = "WebSockets connection lost";

    public static final String Reconn_scheduled = "WebSocket reconnection scheduled";
    public static final String Reconn_started = "WebSocket reconnecting...";

    public static final String mReader_null = "mReader already NULL";
    public static final String mWriter_null = "mWriter already NULL";
    public static final String close_null = "Could not send WebSocket Close .. writer already null";
    public static final String mTransportChannel_null = "mTransportChannel already NULL";
    public static final String observer_null = "WebSocketObserver null";
    public static final String onTextMessage_null = "could not call onTextMessage() .. handler already NULL";
    public static final String onRawTextMessage_null = "could not call onRawTextMessage() .. handler already NULL";
    public static final String onBinaryMessage_null = "could not call onBinaryMessage() .. handler already NULL";
    public static final String onOpen_null = "could not call onOpen() .. handler already NULL";
    public static final String Uri_null = "WebSockets URI null.";
    public static final String Uri_unsupported = "unsupported scheme for WebSockets URI";

    public static final String ping_received = "WebSockets Ping received";
    public static final String pong_received = "WebSockets Pong received";
    public static final String close_received = "WebSockets Close received";
    public static final String handshake_received = "opening handshake received";

    public static final String protocol_viol = "WebSockets protocol violation";
    public static final String intern_err = "WebSockets internal error";
    public static final String serv_err = "Server error ";

    public static final String writer_created = "WebSocket writer created and started.";
    public static final String reader_created = "WebSocket reader created and started.";

    public static final String worker_stopped = "worker threads stopped";

    /* for SocketThread from WebSocketConnection */
    public static final String WS_CONNECTOR = "WebSocketConnector";;
    public static final String sock_exit = "SocketThread exited.";
}
