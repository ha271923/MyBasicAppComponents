package sample.hawk.com.mybasicappcomponents.provider;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/12/8.
 */

public class SimpleProviderActivity extends Activity {
    public Button mMyProviderBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.simpleprovider);
        mMyProviderBtn = (Button) findViewById(R.id.ProviderBtn);
        mMyProviderBtn.setOnClickListener(mMyProviderBtnListener);
    }


    //Only For ContentProvider -------------------------------------------------------------------------
    private View.OnClickListener mMyProviderBtnListener = new View.OnClickListener(){
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
