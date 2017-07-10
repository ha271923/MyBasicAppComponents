package sample.hawk.com.mybasicappcomponents.activity.preference;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sample.hawk.com.mybasicappcomponents.R;
/**
 *   Your data storage options are the following:
 *      1. Shared Preferences:
 *          Store private primitive data in key-value pairs. 適合少量資料
 *          EX: data/data/sample.hawk.com.mybasicappcomponents/shared_prefs/PPPPPPPP.xml
 *      2. Internal Storage:
 *          Store private data on the device memory. 外部APP不可以存取此空間
 *          EX: data/data/sample.hawk.com.mybasicappcomponents/files
 *              FileInputStream fin = this.openFileInput(filename);
 *          EX: data/data/sample.hawk.com.mybasicappcomponents/cache
 *              Data in CacheFolder may be deleted if so required to free up space by Android OS
 *      3. External Storage:
 *          Store public data on the shared external storage. 外部APP可存取, 路徑也許會不存在, 各機型也可能不同
 *          The path is NOT expectable on diff device! Please refer to FileUtils.java for more details.
 *          EX: SD card or an internal(non-removable) storage,
 *          EX: mnt/sdcard/.showme1/howtos or storage/emulated/0/.showme/
 *      4. SQLite Databases:
 *          Store structured data in a private database. 複雜資料處理
 *          EX: data/data/sample.hawk.com.mybasicappcomponents/database
 *      5. Network Connection:
 *          Store data on the web with your own network server. 雲端, 同步機制複雜
 *      6. ContentProviders and Content Resolvers:
 *          Android Share Data between Activity/Apps  解決多APP資料共享
 *          EX: URL="content://sample.hawk.com.mybasicappcomponents.provider.Birthday/friends"
 *
 * */
public class MySharedPreferenceActivity extends AppCompatActivity {
    private SharedPreferences settings;
    private static final String filename_in_shared_prefs_folder = "MySharedPreferenceActivity"; // MySharedPreferenceActivity.xml will store these data
    private static final String nameField = "NAME";
    private static final String phoneField = "PHONE";
    private static final String sexField = "SEX";
    private EditText name;
    private EditText phone;
    private EditText sex;
    private Button save;
    private Button read;
    private Button clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mysharedpreference_activity);
        initComponent();
        setEventListener();
    }

    public void initComponent(){
        name = (EditText)findViewById(R.id.nameField);
        phone = (EditText)findViewById(R.id.phoneField);
        sex = (EditText)findViewById(R.id.sexField);
        save = (Button)findViewById(R.id.saveButton);
        read = (Button)findViewById(R.id.readButton);
        clear = (Button)findViewById(R.id.clearButton);
    }
    public void readData(){
        settings = getSharedPreferences(filename_in_shared_prefs_folder,0);
        name.setText(settings.getString(nameField, ""));
        phone.setText(settings.getString(phoneField, ""));
        sex.setText(settings.getString(sexField, ""));
    }

    public void saveData(){
        settings = getSharedPreferences(filename_in_shared_prefs_folder,0);
        settings.edit()
                .putString(nameField, name.getText().toString())
                .putString(phoneField, phone.getText().toString())
                .putString(sexField, sex.getText().toString())
                .commit();
    }

    public void setEventListener(){
        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                saveData();
                Toast.makeText(MySharedPreferenceActivity.this, "save_success", Toast.LENGTH_SHORT).show();
            }
        });
        read.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                readData();
                Toast.makeText(MySharedPreferenceActivity.this, "read_success", Toast.LENGTH_SHORT).show();
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                name.setText("");
                phone.setText("");
                sex.setText("");
            }
        });
    }
}