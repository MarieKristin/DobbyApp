package teamdobby.dobby;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.content.Context;
import android.util.Log;

/**
 * Created by Marie on 14.04.2016.
 */
public class Settings extends Activity{
    /*private static final String TAG_LOG = "WebSocketsClient";
    private static final String TAG_HOSTNAME = "hostname";
    private static final String TAG_PORT_NUMBER = "port";
    private static final String TAG_TIMEOUT = "timeout";*/
    private static final String TAG_DISABLE_NOTIFICATIONS = "disable_notifications";
    private static final String TAG_DISABLE_MULTIPLE_NOTIFICATIONS = "disable_multiple_notifications";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager mFragmentManager = getFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        PrefsFragment mPrefsFragment = new PrefsFragment();
        mFragmentTransaction.replace(android.R.id.content, mPrefsFragment);
        mFragmentTransaction.commit();
    }

    public static class PrefsFragment extends PreferenceFragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings);
        }
    }

    /*public static String pref_get_hostname (){
        Log.i(TAG_LOG, "pref_get_hostname() value: " + "192.168.0.1");
        return "192.168.0.1";
    }*/

    /*public static void pref_set_hostname (Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TAG_HOSTNAME, "192.168.0.1");
        editor.commit();
        Log.i(TAG_LOG, "pref_set_hostname() value: " + "192.168.0.1");
    }*/

    /*public static String pref_get_port_number (){
        Log.i(TAG_LOG, "pref_get_port_number() value: " + "8080");
        return "8080";
    }*/

    /*public static void pref_set_port_number (Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TAG_PORT_NUMBER, "8080");
        editor.commit();
        Log.i(TAG_LOG, "pref_set_port_number() value: " + "8080");
    }*/

    /*public static String pref_get_timeout (){
        Log.i(TAG_LOG, "pref_get_timeout() value: " + "3000");
        return "3000";
    }*/

    /*public static void pref_set_timeout (Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TAG_TIMEOUT, "3000");
        editor.commit();
        Log.i(TAG_LOG, "pref_set_timeout() value: " + "3000");
    }*/

    public static Boolean pref_notifications_disabled (Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(TAG_DISABLE_NOTIFICATIONS, false);
    }

    public static Boolean pref_multiple_notifications_disabled (Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(TAG_DISABLE_MULTIPLE_NOTIFICATIONS, false);
    }
}
