package sample.hawk.com.mybasicappcomponents.listview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import sample.hawk.com.mybasicappcomponents.R;

public class MyListView3DActivity extends Activity {
	final static int ELEMENT_COUNT = 400;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mylist3d_activity);
        String[] elements = new String[ELEMENT_COUNT];
        // create an DB
        for (int i = 0; i< ELEMENT_COUNT; i++) {
        	elements[i] = String.valueOf(i);
        }
        // link DB
        MyAdapter3D adapter = new MyAdapter3D(this,elements);
        final ListView list = (ListView) findViewById(R.id.list3d);
        list.setDivider( null ); 
        list.setAdapter(adapter);
    }
}
