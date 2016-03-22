package sample.hawk.com.mybasicappcomponents;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

public class MainActivity extends Activity{
    private static final String TAG = "[MainActivity]";
    public static String UPDATE_UI_ACTION = "sample.hawk.com.mybasicappcomponents.MainActivity.UPDATE_UI_ACTION";
    public ProgressBar mMyProgressBar;
    public Button mMyActivityBtn;
    public ToggleButton mMyLocalServiceToggleBtn;
    public ToggleButton mMyBindServiceToggleBtn;
    public Button mMyReceiverBtn;public Button mMyBroadcastBtn;
    public TextView mMyOutputTextView;public TextView mMyTimeTextView;
    public Button mMyProviderBtn;
    public Button mMygetStartServiceResultBtn;
    public Button mMygetBindServiceResultBtn;
    private static int p;
    private BroadcastReceiver mMyReceiver = new MyReceiver();
    // Application Model -----------------------------------------------------------------
    // UPDATE_UI WAY1: an inner class for receive ui update event.
    public class MainActivityUpdateReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(MainActivity.UPDATE_UI_ACTION.equals(action)){
                Bundle bundle = intent.getExtras();
                String To_mMyOutputView = bundle.getString("To_mMyOutputView");
                mMyTimeTextView.setText(To_mMyOutputView);
                if(p>100)
                    p=0;
                mMyProgressBar.setProgress(p++);
            }
        }
    }
    private void register_MainActivityUpdateReceiver(){
        MainActivityUpdateReceiver receiver = new MainActivityUpdateReceiver();
        IntentFilter filter = new IntentFilter(UPDATE_UI_ACTION);
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); SMLog.i();

        // View
        setContentView(R.layout.mainactivity);
        mMyTimeTextView= (TextView) findViewById(R.id.time_textView);
        mMyProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mMyOutputTextView = (TextView) findViewById(R.id.OutputTextView);
        mMyActivityBtn = (Button) findViewById(R.id.ActivityBtn);
        mMyLocalServiceToggleBtn  = (ToggleButton) findViewById(R.id.LocalServiceToggleBtn);
        mMyBindServiceToggleBtn  = (ToggleButton) findViewById(R.id.BindServiceToggleBtn);
        mMyReceiverBtn = (Button) findViewById(R.id.ReceiverBtn);
        mMyBroadcastBtn = (Button) findViewById(R.id.BroadcastBtn);
        mMyProviderBtn = (Button) findViewById(R.id.ProviderBtn);
        mMygetStartServiceResultBtn = (Button) findViewById(R.id.getStartServiceResultBtn);
        mMygetBindServiceResultBtn = (Button) findViewById(R.id.getBindServiceResultBtn);

        register_MainActivityUpdateReceiver();

        // Presenter
        mMyActivityBtn.setOnClickListener(mMyActivityBtnListener);
        mMyLocalServiceToggleBtn.setOnClickListener(mMyLocalServiceToggleBtnListener);
        mMyBindServiceToggleBtn.setOnClickListener(mMyBindServiceToggleBtnListener);
        mMygetStartServiceResultBtn.setOnClickListener(mMygetStartServiceResultBtnListener);
        mMygetBindServiceResultBtn.setOnClickListener(mMygetBindServiceResultBtnListener);
        mMyReceiverBtn.setOnClickListener(mMyReceiverBtnListener);
        mMyBroadcastBtn.setOnClickListener(mMyBroadcastBtnListener);
        mMyProviderBtn.setOnClickListener(mMyProviderBtnListener);

    }



    // Model
    //Only For Activity -------------------------------------------------------------------------
    private OnClickListener mMyActivityBtnListener =new OnClickListener() {
        @Override
        public void onClick(View v) {
            SMLog.i();
            Intent  intent = new Intent();
            intent.setClass( MainActivity.this, MyActivity.class);
            startActivity(intent);
        }
    };

    //Only For Service -------------------------------------------------------------------------
    private OnClickListener mMyLocalServiceToggleBtnListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            SMLog.i();
            Intent intent = new Intent();
            intent.setClass( MainActivity.this, MyLocalService.class);
            if(mMyLocalServiceToggleBtn.isChecked()){
                startService(intent);
                Toast.makeText(MainActivity.this, "START service", Toast.LENGTH_SHORT).show();
                mMyOutputTextView.setText("START Service");
            }
            else{
                stopService(intent);
                Toast.makeText(MainActivity.this, "STOP service", Toast.LENGTH_SHORT).show();
                mMyOutputTextView.setText("STOP Service");
            }
        }
    };
    private OnClickListener mMygetStartServiceResultBtnListener = new OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    // LocalService
    MyLocalService mMyLocalService;
    boolean mBound = false;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className,IBinder service) {
            SMLog.i();
            MyLocalService.LocalBinder binder = (MyLocalService.LocalBinder) service;
            mMyLocalService = binder.getService();
            mBound = true;
        }
        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            SMLog.i();
            mBound = false;
        }
    };
    private OnClickListener mMyBindServiceToggleBtnListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            boolean ret = false;
            SMLog.i();
            if(mMyBindServiceToggleBtn.isChecked()){
                // TODO:mActivity.bindService(new Intent(mActivity, mClz), mConnection, Context.BIND_AUTO_CREATE));
                Intent intent = new Intent(MainActivity.this, MyLocalService.class);
                ret = bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
                Toast.makeText(MainActivity.this, "BIND service", Toast.LENGTH_SHORT).show();
                mMyOutputTextView.setText("bindService = "+ret);
            }
            else{
                // TODO: mActivity.unbindService(mConnection);
                unbindService(mConnection);
                mBound = false;
                Toast.makeText(MainActivity.this, "UNBIND service", Toast.LENGTH_SHORT).show();
                mMyOutputTextView.setText("unbindService");
            }
        }
    };
    private OnClickListener mMygetBindServiceResultBtnListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mBound) {
                // Call API at background
                int num = mMyLocalService.getRandomNumber();
                Toast.makeText(MainActivity.this, "number: " + num, Toast.LENGTH_SHORT).show();
                mMyOutputTextView.setText("number: "+ num);
            }
        }
    };

    //Only For BroadcastReceiver -------------------------------------------------------------------------
    private OnClickListener mMyReceiverBtnListener = new OnClickListener(){
        @Override
        public void onClick(View v) {
            SMLog.i();
            // init Receive side.
            IntentFilter intentFilterForReceiver = new IntentFilter("sample.hawk.com.mybasicappcomponents.broadcast1");
            // <!-- Dynamically register MyReceiver  -->
            registerReceiver(mMyReceiver, intentFilterForReceiver);
        }
    };
    private OnClickListener mMyBroadcastBtnListener = new OnClickListener(){
        @Override
        public void onClick(View v) {
            SMLog.i();
            // send a broadcast only for this APP.
            Intent intent = new Intent();
            intent.setAction("sample.hawk.com.mybasicappcomponents.broadcast1");
            sendBroadcast(intent);
        }
    };

    //Only For ContentProvider -------------------------------------------------------------------------
    private OnClickListener mMyProviderBtnListener = new OnClickListener(){
        @Override
        public void onClick(View v) {
            SMLog.i();
            // Hawk: During launching APP, my APP's ContentProvider will be launched by OS.
            ContentValues values = new ContentValues();
            values.put(MyContentProvider.columnConst.NAME,"MyDefault");
            values.put(MyContentProvider.columnConst.BIRTHDAY,"11/11");
            Uri uri = getContentResolver().insert(
                    MyContentProvider.CONTENT_URI, values);
            Toast.makeText(getBaseContext(),
                    "MyContentProvider : " + uri.toString() + " inserted!", Toast.LENGTH_LONG).show();
        }
    };

    public void addDefaultBirthday (View view) {
            SMLog.i();
    }

    public void deleteAllBirthdays (View view) {  // Hawk: Already write android:onClick="showAllBirthdays" in AndroidManifest.xml .
        SMLog.i();
        // delete all the records and the table of the database provider
        String URL = "content://sample.hawk.com.mybasicappcomponents.provider.Birthday/friends";
        Uri friends = Uri.parse(URL);
        int count = getContentResolver().delete(friends, null, null);
        String countNum = "MyContentProvider Num: "+ count +" records are deleted.";
        Toast.makeText(getBaseContext(),countNum, Toast.LENGTH_LONG).show();

    }

    public void addBirthday(View view) {
        SMLog.i();
        // Add a new birthday record
        ContentValues values = new ContentValues();

        values.put(MyContentProvider.columnConst.NAME,
                ((EditText)findViewById(R.id.name)).getText().toString());

        values.put(MyContentProvider.columnConst.BIRTHDAY,
                ((EditText)findViewById(R.id.birthday)).getText().toString());

        Uri uri = getContentResolver().insert(
                MyContentProvider.CONTENT_URI, values);

        Toast.makeText(getBaseContext(),
                "MyContentProvider : " + uri.toString() + " inserted!", Toast.LENGTH_LONG).show();
    }


    public void showAllBirthdays(View view) {
        SMLog.i();
        // Show all the birthdays sorted by friend's name
        String URL = "content://sample.hawk.com.mybasicappcomponents.provider.Birthday/friends";
        Uri friends = Uri.parse(URL);
        Cursor c = getContentResolver().query(friends, null, null, null, "name");
        String result = "MyContentProvider Results:";

        if (!c.moveToFirst()) {
            Toast.makeText(this, result+" no content yet!", Toast.LENGTH_LONG).show();
        }else{
            do{
                result = result + "\n" +
                        "   ID: " + c.getString(c.getColumnIndex(MyContentProvider.columnConst.ID)) +
                        " NAME: " + c.getString(c.getColumnIndex(MyContentProvider.columnConst.NAME)) +
                        " BDAY: " + c.getString(c.getColumnIndex(MyContentProvider.columnConst.BIRTHDAY));
            } while (c.moveToNext());
            Toast.makeText(this, result, Toast.LENGTH_LONG).show();
        }

    }

}
