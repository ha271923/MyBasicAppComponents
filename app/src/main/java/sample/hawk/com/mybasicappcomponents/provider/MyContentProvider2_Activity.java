package sample.hawk.com.mybasicappcomponents.provider;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;


public class MyContentProvider2_Activity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mycontentprovider2_activity);
    }

    // "vnd.android.cursor.item/vnd." + AUTHORITY + "." + MyDBHelper._DB_TABLE1; // one row
    public void onClick_QueryTable1Btn(View view){
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
        }
    }

    // "vnd.android.cursor.dir/vnd." + AUTHORITY + "." + MyDBHelper._DB_TABLE2; // multiple rows
    public void onClick_QueryTable2Btn(View view){
        Cursor c = getContentResolver().query(
                Uri.parse("content://sample.hawk.com.mybasicappcomponents.provider.MyContentProvider2/MyTable2"),
                null,null,null,null ); // get all
        if( c != null ) {
            c.moveToFirst();
            while(c.isAfterLast()==false) {
                SMLog.i("MyTalbe2 field1:"+c.getString(0)+", field2:"+c.getString(1));
                c.moveToNext();
            }
            c.close();
        }
    }

}