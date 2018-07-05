package sample.hawk.com.mybasicappcomponents.utils;

import android.app.Activity;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

// System Utils
public class CmdLine {
    private static final String TAG = "CmdLine";

    // execute any command
    private BufferedReader exec(Activity activity, String cmd, boolean toast) {
        Process p = null;
        try {
            p = Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(activity, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            return null;
        }

        if (toast) {
            Toast.makeText(activity, cmd + " / " + p.toString(), Toast.LENGTH_SHORT).show();
            return null;
        }

        int w = -1;
        try {
            w = p.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Toast.makeText(activity, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            return null;
        }

        Log.d(TAG, "exec(" + cmd + ") " + p.toString() + " exit[" + w + "]");
        InputStreamReader isr = new InputStreamReader(p.getInputStream());
        BufferedReader br = new BufferedReader(isr);
        return br;
    }

    // execute command in su<br />
    // Warning! This method not yet implemented!<br />
    // TODO: FIXME
    private BufferedReader exec_su(Activity activity, String cmd, boolean toast) {
        // String[] su = {"su", "-c", cmd};

        Process p = null;
        //try {
        //    p = Runtime.getRuntime().exec("su -c " + cmd);
        //} catch (IOException e) {
        //    e.printStackTrace();
        //    Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        //    return null;
        //}

        DataOutputStream dos = null;
        try {
            p = Runtime.getRuntime().exec("su");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        dos = new DataOutputStream(p.getOutputStream());
        try {
            dos.writeBytes(cmd);
            dos.writeBytes("\n");
            dos.writeBytes("exit\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (toast) {
            Toast.makeText(activity, cmd + " / " + p.toString(), Toast.LENGTH_SHORT).show();
            return null;
        }

        int w = -1;
        try {
            w = p.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Toast.makeText(activity, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            return null;
        }

        Log.d(TAG, "exec(" + cmd + ") " + p.toString() + " exit[" + w + "]");
        InputStreamReader isr = new InputStreamReader(p.getInputStream());
        BufferedReader br = new BufferedReader(isr);
        return br;
    }

    // Get ANDROID_ID
    public static String getAndroid_ID(Activity activity) {
        return Settings.Secure.getString(activity.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

}
