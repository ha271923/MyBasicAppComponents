package sample.hawk.com.mybasicappcomponents.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

import sample.hawk.com.mybasicappcomponents.R;

/**
 * Created by ha271 on 2017/7/20.
 */

public class MyDialogFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener {

    private MyDialogFragActivity mHostActivity;
    public SharedPreferences mSharedPref;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHostActivity = (MyDialogFragActivity)getActivity();
        addPreferencesFromResource(R.xml.mydialogfragment_pref); // This method was deprecated in API level 11. Load the preferences from an XML resource
        this.mSharedPref = PreferenceManager.getDefaultSharedPreferences(mHostActivity);
        findPreference("Item_1_key").setOnPreferenceClickListener(this);
        findPreference("Item_2_key").setOnPreferenceClickListener(this);
        findPreference("Item_3_key").setOnPreferenceClickListener(this);
        findPreference("Item_4_key").setOnPreferenceClickListener(this);

    }

    public boolean onPreferenceClick(Preference preference) {
        String key = preference.getKey();
        Intent intent;
        if (key.equals("Item_1_key")) {
            mHostActivity.showMyDialog(1);
        }
        if (key.equals("Item_2_key")) {
            mHostActivity.showMyDialog(2);
        }
        if (key.equals("Item_3_key")) {
            mHostActivity.showMyDialog(3);
        }
        if (key.equals("Item_4_key")) {
            mHostActivity.showMyDialog(4);
        }
        return false;
    }

}
