package sample.hawk.com.mybasicappcomponents.ipc.bundle;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

public class MyBundleActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mybundle_activity);
    }

    private Intent buildIntentFor(Class<?> cls){
        EditText HeightEditText = (EditText)findViewById(R.id.EditText01);
        double height = Double.parseDouble(HeightEditText.getText().toString());

        String sex="";
        RadioButton SexMale = (RadioButton)findViewById(R.id.RadioButton01);
        if(SexMale.isChecked())
            sex="Man";
        else
            sex="Female";

        // KEY: assemble & send data by bundle
        // new一個intent物件，並指定Activity切換的class
        Intent intent = new Intent();
        intent.setClass(MyBundleActivity.this, cls);
        // new一個Bundle物件，並將要傳遞的資料傳入
        Bundle bundle = new Bundle();
        bundle.putDouble("height",height );
        bundle.putString("sex", sex);
        // 將Bundle物件assign給intent
        intent.putExtras(bundle);
        return intent;
    }

    public void onActivityToActivityClick(View v) {
        Intent i = buildIntentFor(MyBundleActivity2.class);
        // 切換 Activity
        startActivity(i);
    }

    public void onActivityToServiceClick(View v) {
        Intent i = buildIntentFor(MyBundleService.class);
        // 送到 Service
        this.startService(i);
    }

    public Intent buildIntentForBroadcastReceiver() {
        Intent intent = new Intent();
        intent.setAction("i_am_hungry");
        intent.putExtra("HELLO",  "**********************************************************\n"+
                "@@@@@@@@@@@@@@@@@@@  HAWK LOVE EVERYONE @@@@@@@@@@@@@@@@@@@\n"+
                "***********************************************************");
        return intent;
    }
    public void onActivityToBroadcastReceiverClick(View v) {
        Intent i = buildIntentForBroadcastReceiver();
        // 送到 Receiver
        sendBroadcast(i);
    }

    public void onActivityToContentProviderClick(View v) {
        SMLog.i("onActivityToContentProviderClick +++");
        ContentResolver contentResolver = this.getContentResolver();
        String URL = "content://sample.hawk.com.mybasicappcomponents.provider.Birthday/friends";
        Uri uri = Uri.parse(URL);
        // 取回 ContentProvider 的處理結果
        Bundle bundle = contentResolver.call(uri, "method", null, null);
        String returnCall = bundle.getString("returnCall");
        SMLog.i("onActivityToContentProviderClick ---" + returnCall);

    }
}