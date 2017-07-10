package sample.hawk.com.mybasicappcomponents.activity.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;


/**
 *
 * java.util.prefs.Preferences :
 *    This class allows applications to store and retrieve user and system preference and configuration data.
 *    This data is stored persistently in an implementation-dependent backing store.
 *
 * android.content.SharedPreferences :
 *    Interface for accessing and modifying preference data returned by getSharedPreferences(String, int).
 *    For any particular set of preferences, there is a single instance of this class that all clients share.
 */

// preference at data/data/<package_name>/shared_prefs/<package_name>_preferences.xml
// adb shell cat <path_file> to show the content.
public class MyXmlPreferenceActivity extends PreferenceActivity {
    Context mContext;
    MySettingsFragment mMySettingsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        mContext = this;
        mMySettingsFragment = new MySettingsFragment();
        getFragmentManager().beginTransaction().replace(android.R.id.content, mMySettingsFragment).commit();
    }


    public static class MySettingsFragment extends PreferenceFragment
            implements SharedPreferences.OnSharedPreferenceChangeListener, Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
            setPreferencesActions();
        }

        private void setPreferencesActions(){
            SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
            sharedPreferences.registerOnSharedPreferenceChangeListener(this);

            CheckBoxPreference checkbox_preference = (CheckBoxPreference) findPreference("checkbox_preference");
            checkbox_preference.setOnPreferenceClickListener(this);

            EditTextPreference edittext_preference = (EditTextPreference) findPreference("edittext_preference");
            edittext_preference.setOnPreferenceClickListener(this);

            ListPreference list_preference = (ListPreference) findPreference("list_preference");
            list_preference.setOnPreferenceClickListener(this);

            PreferenceScreen screen_preference = (PreferenceScreen) findPreference("screen_preference");
            screen_preference.setOnPreferenceClickListener(this);

            CheckBoxPreference next_screen_checkbox_preference = (CheckBoxPreference) findPreference("next_screen_checkbox_preference");
            next_screen_checkbox_preference.setOnPreferenceClickListener(this);

            CheckBoxPreference parent_checkbox_preference = (CheckBoxPreference) findPreference("parent_checkbox_preference");
            parent_checkbox_preference.setOnPreferenceClickListener(this);

            CheckBoxPreference child_checkbox_preference = (CheckBoxPreference) findPreference("child_checkbox_preference");
            child_checkbox_preference.setOnPreferenceClickListener(this);

        }

        @Override // call if any click
        public boolean onPreferenceClick(Preference preference) {
            String key = preference.getKey();
            SMLog.i("onPreferenceClick   key="+key);
            if(key.equals("checkbox_preference")){

            } else if(key.equals("edittext_preference")) {

            } else if(key.equals("list_preference")) {

            } else if(key.equals("screen_preference")) {

            } else if(key.equals("next_screen_checkbox_preference")) {

            } else if(key.equals("parent_checkbox_preference")) {

            } else if(key.equals("child_checkbox_preference")) {

            }
            return false;
        }

        @Override // Hawk: not call here for unknown reason, maybe it's not sharedpreference.
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            String key = preference.getKey();
            SMLog.i("onPreferenceChange   key="+key);
            if(key.equals("checkbox_preference")){

            } else if(key.equals("edittext_preference")) {

            } else if(key.equals("list_preference")) {

            } else if(key.equals("screen_preference")) {

            } else if(key.equals("next_screen_checkbox_preference")) {

            } else if(key.equals("parent_checkbox_preference")) {

            } else if(key.equals("child_checkbox_preference")) {

            }
            return false;
        }

        @Override // call if any changed.
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            SMLog.i("onSharedPreferenceChanged= "+sharedPreferences+"     key= "+key+"    Changed!!");
            if(key.equals("checkbox_preference")){

            } else if(key.equals("edittext_preference")) {

            } else if(key.equals("list_preference")) {

            } else if(key.equals("screen_preference")) {

            } else if(key.equals("next_screen_checkbox_preference")) {

            } else if(key.equals("parent_checkbox_preference")) {

            } else if(key.equals("child_checkbox_preference")) {

            }
        }

    }

}