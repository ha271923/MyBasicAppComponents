package sample.hawk.com.mybasicappcomponents.data_structure.SQL;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import sample.hawk.com.mybasicappcomponents.R;

import static sample.hawk.com.mybasicappcomponents.data_structure.SQL.MySQL_DB.DATABASE_NAME;
import static sample.hawk.com.mybasicappcomponents.data_structure.SQL.MySQL_DB.TABLE_NAME;

public class MySQL_Activity extends Activity {
    // Button res in R.menu.mysql_menu
    private Button add , edit, del, clear, end;
    private EditText edname , edprice;
    private MySQL_DB db = null;
    private Cursor mCursor;
    private ListView listview;
    private long select_item = 1; //item 預設值 = 1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mysql_activity);
        ((TextView) findViewById(R.id.tablename)).setText("DB:"+DATABASE_NAME + "  Table:"+TABLE_NAME);
        add = (Button) findViewById(R.id.add); add.setOnClickListener(Buttonslistener);
        edit = (Button) findViewById(R.id.edit); edit.setOnClickListener(Buttonslistener);
        del = (Button) findViewById(R.id.del); del.setOnClickListener(Buttonslistener);
        clear = (Button) findViewById(R.id.clear); clear.setOnClickListener(Buttonslistener);
        end =(Button) findViewById(R.id.end); end.setOnClickListener(Buttonslistener);
        edname = (EditText) findViewById(R.id.edname);
        edprice = (EditText) findViewById(R.id.edprice);
        listview = (ListView) findViewById(R.id.listview); listview.setOnItemClickListener(Listviewlistener);
        db = new MySQL_DB(this);
        db.open();
        mCursor = db.getALL();
        UpdateAdapter(mCursor);
    }

    //將傳回的Cursor塞進listview
    private void UpdateAdapter(Cursor c) {
        if (c != null && c.getCount() > 0) {
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, c,
                    new String[] {          "name",      "price"       },
                    new int[] { android.R.id.text1, android.R.id.text2 });
            listview.setAdapter(adapter);
        }
    }

    //ListView listener
    private OnItemClickListener Listviewlistener = new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            select_item = arg3;
            find_data(select_item);
            //將Cursor移動到id的位置 不過似乎沒加上這行對app也沒差
            //mCursor.moveToPosition(arg2);
        }

        //依照id查詢資料
        private void find_data(long search_id) {
            Cursor c = db.getsearchid(search_id);
            // show it!
            edname.setText(c.getString(1));
            edprice.setText(c.getString(2));
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

    //Buttons listener
    private OnClickListener Buttonslistener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.add:
                    if (db.add(edname.getText().toString(), edprice.getText().toString()) > 0) {
                        mCursor = db.getALL();
                        UpdateAdapter(mCursor);
                        clearedit();
                    }
                    break;
                case R.id.edit:
                    if (db.edit(select_item, edname.getText().toString(), edprice.getText().toString())) {
                        mCursor = db.getALL();
                        UpdateAdapter(mCursor);
                        clearedit();
                    }
                    break;
                case R.id.del:
                    if (db.delete(select_item)) {
                        mCursor = db.getALL();
                        UpdateAdapter(mCursor);
                        clearedit();
                    }
                    break;
                case R.id.clear:
                    clearedit();
                    break;
                case R.id.end:
                    finish();
                    break;
            }
        }

        private void clearedit() {
            edname.setText("");
            edprice.setText("");
        }

    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mysql_menu, menu);
        return true;
    }
}