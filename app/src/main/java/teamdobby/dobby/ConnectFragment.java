package teamdobby.dobby;
//VIEWMODEL

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
//import android.content.Intent;
import android.graphics.Color;
import android.os.Parcelable;
import android.text.Spannable;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.KeyEvent;
/*import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;*/
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import com.dd.CircularProgressButton;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.URI;
import java.net.URISyntaxException;
import teamdobby.dobby.SecureWebSockets.WebSocket;
import teamdobby.dobby.SecureWebSockets.WebSocketException;
import teamdobby.dobby.SecureWebSockets.WebSocketOptions;
import teamdobby.dobby.SecureWebSockets.WebSocketConnection;
import com.github.johnpersano.supertoasts.SuperActivityToast;
import com.github.johnpersano.supertoasts.SuperToast;
import com.github.johnpersano.supertoasts.util.OnClickWrapper;
import com.github.johnpersano.supertoasts.util.Wrappers;



/**
 * A simple {@link Fragment} subclass.
 */
public class ConnectFragment extends Fragment implements WebSocket.WebSocketConnectionObserver {

    private static final String TAG_LOG = "WebSocketsClient";
    private static final String TAG_JSON_TYPE = "Type";
    private static final String TAG_JSON_MSG  = "Message";

    private volatile boolean isConnected = false;
    private WebSocketConnection wsConnection;
    private WebSocketOptions wsOptions;
    private URI wsURI;

    private EditText cmdInput;
    private TextView cmdOutput;
    private CircularProgressButton connectButton;

    private String hostname;
    private String portNumber;
    private String timeout;

    public ConnectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_connect, container, false);

        Wrappers wrappers = new Wrappers();
        wrappers.add(onClickWrapperExit);
        SuperActivityToast.onRestoreState(savedInstanceState, getActivity(), wrappers);

        /*hostname = (EditText) view.findViewById(R.id.hostname);
        portNumber = (EditText) view.findViewById(R.id.port);
        timeout = (EditText) view.findViewById(R.id.timeout);*/
        hostname = "192.168.0.1";
        portNumber = "8080";
        timeout = "3000";

        cmdInput = (EditText) view.findViewById(R.id.cmdInput);
        cmdOutput = (TextView) view.findViewById(R.id.cmdOutput);
        connectButton = (CircularProgressButton) view.findViewById(R.id.btnConnect);

        cmdOutput.setMovementMethod(new ScrollingMovementMethod());
        connectButton.setIndeterminateProgressMode(true);

        /*
         * connectButton - onClickListener()
         */
        connectButton.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (connectButton.getProgress() == 0) {
                    connectButton.setProgress(50);

                    /*if ((hostname.getText().toString().equals("")) ||
                            (portNumber.getText().toString().equals("")) ||
                            (timeout.getText().toString().equals(""))) {

                        Log.e(TAG_LOG, "Invalid connection settings");
                        show_info(getResources().getString(R.string.info_msg_1), false);
                        connectButton.setProgress(-1);
                        return;
                    }*/

                    /* save last settings */
                    /*Settings.pref_set_hostname(getActivity().getBaseContext());
                    Settings.pref_set_port_number(getActivity().getBaseContext());
                    Settings.pref_set_timeout(getActivity().getBaseContext());*/
                    Log.i(TAG_LOG, "pref_set_hostname() value: " + "192.168.0.1");
                    Log.i(TAG_LOG, "pref_set_port_number() value: " + "8080");
                    Log.i(TAG_LOG, "pref_set_timeout() value: " + "3000");

                    /* connect */
                    if (!wsConnect()) {
                        show_info(getResources().getString(R.string.info_msg_2), false);
                        connectButton.setProgress(-1);
                    }

                } else if (connectButton.getProgress() == -1) {
                    connectButton.setProgress(0);
                }
            }
        });

        ((EditText)view.findViewById(R.id.cmdInput)).setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    /* send data to server */
                    wsSend();
                    return true;
                }
                return false;
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        hostname = "192.168.0.1";
        Log.i(TAG_LOG, "pref_get_hostname() value: " + hostname);
        portNumber = "8080";
        Log.i(TAG_LOG, "pref_get_port_number() value: " + portNumber);
        timeout = "3000";
        Log.i(TAG_LOG, "pref_get_timeout() value: " + timeout);
        super.onResume();
    }

    boolean wsConnect() {

        if (!this.isConnected) {

            this.wsConnection = new WebSocketConnection();
            this.wsOptions = new WebSocketOptions();
            wsOptions.setSocketConnectTimeout(Integer.parseInt(timeout));

            try {
                this.wsURI = new URI("ws://" + hostname + ":" + portNumber);
                wsConnection.connect(wsURI, this, wsOptions);
            } catch (WebSocketException e) {
                Log.e(TAG_LOG,  "Can't connect to server - 'WebSocketException'");
                this.isConnected = false;
                return false;
            } catch (URISyntaxException e1) {
                Log.e (TAG_LOG, "Can't connect to server - 'URISyntaxException'");
                this.isConnected = false;
                return false;
            } catch (Exception ex) {
                Log.e (TAG_LOG, "Can't connect to server - 'Exception'");
                this.isConnected = false;
                return false;
            }

            Log.i (TAG_LOG, "Connected");
            this.isConnected = true;
            return true;
        }

        Log.w(TAG_LOG, "You are already connected to the server");
        return true;
    }

    void wsDisconnect() {
        if (isConnected) {
            Log.i(TAG_LOG, "Disconnected");
            wsConnection.disconnect();
            connectButton.setProgress(0);
        }
    }

    void wsSend() {
        if (isConnected) {

            /* send message to the server */
            Log.i (TAG_LOG, "Message has been successfully sent");
            wsConnection.sendTextMessage(cmdInput.getText().toString());
            appendText(cmdOutput, "[CLIENT] " + cmdInput.getText().toString() + "\n", Color.RED);

        } else {

            /* no connection to the server */
            connectButton.setProgress(-1);
            show_info(getResources().getString(R.string.info_msg_2), false);
        }
        cmdInput.getText().clear();
    }

    @Override
    public void onOpen() {

        Log.i (TAG_LOG, "onOpen() - connection opened to: " + wsURI.toString());
        this.isConnected = true;
        connectButton.setProgress(100);
    }

    @Override
    public void onClose (WebSocketCloseNotification code, String reason) {

        Log.i (TAG_LOG, "onClose() - " + code.name() + ", " + reason);
        this.isConnected = false;
        connectButton.setProgress(0);
    }

    @Override
    public void onTextMessage (String payload) {

        try {

            Log.i(TAG_LOG, "New message from server");
            JSONObject jsonObj = new JSONObject(payload);

            if ((jsonObj.has(TAG_JSON_TYPE)) && (jsonObj.has(TAG_JSON_MSG))) {

                /*
                 * Notification
                 */
                if (jsonObj.getString(TAG_JSON_TYPE).equals("notification")) {

                    if (Settings.pref_notifications_disabled(getActivity().getBaseContext())) {

                        Log.i(TAG_LOG, "Notifications are disabled");

                    } else {

                        int notification_id;

                        if (Settings.pref_multiple_notifications_disabled(getActivity().getBaseContext()))
                            notification_id = 0;
                        else
                            notification_id = (int) System.currentTimeMillis();

                        /* create new notification */
                        Notification new_notification = new Notification.Builder(getActivity())
                                .setContentTitle(getResources().getString(R.string.app_name))
                                .setContentText(jsonObj.getString(TAG_JSON_MSG))
                                .setSmallIcon(R.mipmap.ic_launcher).build();
                        new_notification.defaults |= Notification.DEFAULT_ALL;
                        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(getActivity().NOTIFICATION_SERVICE);
                        notificationManager.notify(notification_id, new_notification);

                        appendText(cmdOutput, "[SERVER] Asynchronous Notification\n", Color.parseColor("#ff0099cc"));
                    }

                /*
                 * Standard message
                 */
                } else if (jsonObj.getString(TAG_JSON_TYPE).equals("standard")) {

                    appendText(cmdOutput, "[SERVER] " + jsonObj.getString(TAG_JSON_MSG) + "\n", Color.parseColor("#ff99cc00"));

                /*
                 * JSON object is not valid
                 */
                } else {
                    show_info (getResources().getString(R.string.info_msg_4), false);
                    Log.e (TAG_LOG, "Received invalid JSON from server");
                }
            }
        } catch (JSONException e) {

            /* JSON object is not valid */
            show_info (getResources().getString(R.string.info_msg_4), false);
            Log.e (TAG_LOG, "Received invalid JSON from server");
        }
    }

    @Override
    public void onRawTextMessage (byte[] payload) {
        Log.wtf(TAG_LOG, "We didn't expect 'RawTextMessage'");
    }

    @Override
    public void onBinaryMessage (byte[] payload) {
        Log.wtf (TAG_LOG, "We didn't expect 'BinaryMessage'");
    }

    void show_info (String info, boolean showButton) {

        SuperActivityToast superActivityToast;

        if (showButton) {
            superActivityToast = new SuperActivityToast(getActivity(), SuperToast.Type.BUTTON);
            superActivityToast.setOnClickWrapper(onClickWrapperExit);
            superActivityToast.setButtonIcon(SuperToast.Icon.Dark.EXIT, "Exit");
        } else {
            superActivityToast = new SuperActivityToast(getActivity(), SuperToast.Type.STANDARD);
            superActivityToast.setIcon(SuperToast.Icon.Dark.INFO, SuperToast.IconPosition.LEFT);
        }

        superActivityToast.setDuration(SuperToast.Duration.EXTRA_LONG);
        superActivityToast.setAnimations(SuperToast.Animations.FLYIN);
        superActivityToast.setBackground(SuperToast.Background.RED);
        superActivityToast.setText(info);
        superActivityToast.show();
    }

    static void appendText (TextView textView, String text, int textColor) {

        int start;
        int end;

        start = textView.getText().length();
        textView.append(text);
        end = textView.getText().length();

        Spannable spannableText = (Spannable) textView.getText();
        spannableText.setSpan(new ForegroundColorSpan(textColor), start, end, 0);
    }

    /*@Override
    public boolean onKeyDown (int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            onBackPressed();
        }
        return true;
    }*/

    OnClickWrapper onClickWrapperExit = new OnClickWrapper("id_exit", new SuperToast.OnClickListener() {
        @Override
        public void onClick(View view, Parcelable token) {
            wsDisconnect();
            getActivity().finish();
        }
    });


}
