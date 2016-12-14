package sample.hawk.com.mybasicappcomponents.view;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import sample.hawk.com.mybasicappcomponents.R;

/**
 * Created by ha271 on 2016/11/14.
 */

public class SimpleListViewActivity extends Activity {
    private ListView listView;
    private String[] list = {"ITEM 1","ITEM 2","ITEM 3","ITEM 4","ITEM 5"};
    private ArrayAdapter<String> listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // View
        setContentView(R.layout.simplelist_activity);
        listView = (ListView)findViewById(R.id.simplelist_view);
        // Model - Adapter
        listAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(listAdapter);
        // Action
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Choice: " + list[position], Toast.LENGTH_SHORT).show();
            }
        });
    }
}
