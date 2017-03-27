package sample.hawk.com.mybasicappcomponents.debugTest.DebugMsgProxy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.Util;

/**
 * Created by ha271 on 2017/3/23.
 */

public class DebugMsgFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener, Preference.OnPreferenceClickListener {
    public String prefKey;
    public SharedPreferences mSharedPref;
    private DebugMsgActivity mHostActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHostActivity = (DebugMsgActivity)getActivity();
        addPreferencesFromResource(R.xml.debugmsgsettings); // This method was deprecated in API level 11. Load the preferences from an XML resource
        this.mSharedPref = PreferenceManager.getDefaultSharedPreferences(mHostActivity);
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);


        // Hawk: We need to stop it always, then restart it by "prefEnable" preference
        Intent intent = new Intent(DebugMsgFragment.this.getActivity(), DebugMsgService.class);
        if (Util.isServiceRunning(mHostActivity,DebugMsgService.class)) {
            mHostActivity.stopService(intent);
        }
        if (this.mSharedPref.getBoolean("prefEnable", true)) {
            mHostActivity.startService(intent);
        }
        findPreference("prefTransparentIcon").setOnPreferenceClickListener(this);
        findPreference("prefDetectAreaHeight").setOnPreferenceClickListener(this);
        findPreference("prefDetectAreaTransparent").setOnPreferenceClickListener(this);
        // mHostActivity.getDrawPermission(mHostActivity); // Hawk: I move it to startService

    }

    public boolean onPreferenceClick(Preference preference) {
        String key = preference.getKey();
        if ("prefTransparentIcon".equals(key) && ((CheckBoxPreference) preference).isChecked()) {
            mHostActivity.showMyDialog(3);

        } else if ("prefDetectAreaHeight".equals(key)) {
            DebugMsgFragmentDialog frag= new DebugMsgFragmentDialog();
            mHostActivity.showMyDialog(7);

        } else if ("prefDetectAreaTransparent".equals(key)) {
            DebugMsgFragmentDialog frag= new DebugMsgFragmentDialog();
            mHostActivity.showMyDialog(8);

        }
        return false;
    }

    public void onResume() {
        super.onResume();
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if (!str.equals("prefAutoStart")) {
            Intent intent = new Intent(mHostActivity, DebugMsgService.class);
            mHostActivity.stopService(intent);
            if (this.mSharedPref.getBoolean("prefEnable", true)) {
                mHostActivity.startService(intent);
            }
        }
    }


}