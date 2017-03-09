package sample.hawk.com.mybasicappcomponents.provider;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;


public class MyContentProvider2_Activity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mycontentprovider2_activity);
    }

    public void onClick_QueryBtn(View view){
        Cursor c = getContentResolver().query(
                Uri.parse("content://sample.hawk.com.mybasicappcomponents.provider.MyContentProvider2/MyTable1"),
                null,null,null,null ); // get all
        if( c != null ) {
            c.moveToFirst();
            while(c.isAfterLast()==false) {
                SMLog.i("MyTalbe1 field1:"+c.getString(0)+", field2:"+c.getString(1));
                c.moveToNext();
            }
            c.close();

            c = getContentResolver().query(
                    Uri.parse("content://sample.hawk.com.mybasicappcomponents.provider.MyContentProvider2/MyTable2"),
                    null,null,null,null ); // get all
            c.moveToFirst();
            while(c.isAfterLast()==false) {
                SMLog.i("MyTalbe2 field1:"+c.getString(0)+", field2:"+c.getString(1));
                c.moveToNext();
            }
            c.close();
        }
    }

}