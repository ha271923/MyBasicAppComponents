package sample.hawk.com.mybasicappcomponents;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

public class MainActivity extends Activity{
    public Button mMyActivityBtn;
    public Button mMyServiceBtn;
    public Button mMyReceiverBtn;public Button mMyBroadcastBtn;public TextView mMyReceivedOutputTextView;
    public Button mMyProviderBtn;
    private BroadcastReceiver mMyReceiver = new MyReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); SMLog.i();

        // View
        setContentView(R.layout.activity_main);
        // Presenter
        mMyActivityBtn = (Button) findViewById(R.id.ActivityBtn);
        mMyServiceBtn  = (Button) findViewById(R.id.ServiceBtn);
        mMyReceiverBtn = (Button) findViewById(R.id.ReceiverBtn);
        mMyBroadcastBtn = (Button) findViewById(R.id.BroadcastBtn);
        mMyReceivedOutputTextView = (TextView) findViewById(R.id.ReceivedOutputTextView);
        mMyProviderBtn = (Button) findViewById(R.id.ProviderBtn);
        mMyActivityBtn.setOnClickListener(mMyActivityBtnListener);
        mMyServiceBtn.setOnClickListener(mMyServiceBtnListener);
        mMyReceiverBtn.setOnClickListener(mMyReceiverBtnListener);
        mMyBroadcastBtn.setOnClickListener(mMyBroadcastBtnListener);
        mMyProviderBtn.setOnClickListener(mMyProviderBtnListener);
    }


    // Model
    private OnClickListener mMyActivityBtnListener =new OnClickListener() {
        @Override
        public void onClick(View v) {
            SMLog.i();
            Intent  intent = new Intent();
            intent.setClass( MainActivity.this, MyActivity.class);
            startActivity(intent);
        }
    };
    private OnClickListener mMyServiceBtnListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            SMLog.i();
            Intent intent = new Intent();
            intent.setClass( MainActivity.this, MyService.class);
            startService(intent);
        }
    };

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

    // Hawk: For contentProvider
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
