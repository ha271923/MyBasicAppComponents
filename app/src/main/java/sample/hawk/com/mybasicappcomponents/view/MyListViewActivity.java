package sample.hawk.com.mybasicappcomponents.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import sample.hawk.com.mybasicappcomponents.R;

/**
 * Created by ha271 on 2017/1/4.
 */

public class MyListViewActivity extends Activity {
    final static int ELEMENT_COUNT = 400;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mylistview_activity); // your listview page layout
        String[] elements = new String[ELEMENT_COUNT];
        // create an DB
        for (int i = 0; i< ELEMENT_COUNT; i++) {
            elements[i] = String.valueOf(i);
        }
        // link DB
        MyAdapter adapter = new MyAdapter(this,elements);
        final ListView list = (ListView) findViewById(R.id.mylistview);
        list.setDivider( null );
        list.setAdapter(adapter);
    }
}
