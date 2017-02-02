package sample.hawk.com.mybasicappcomponents.search.text;

/**
 * Source from http://www.worldbestlearningcenter.com/tips/Android-search-ListView.htm
 */

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Arrays;

import sample.hawk.com.mybasicappcomponents.R;

public class TextSearchActivity extends ActionBarActivity {
    String[] items;
    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;
    ListView listView;
    EditText editText;


    public void initList(){
        items=new String[]{"Canada","China","country","Japan","USA","unite-state-america","@Hawk","#Mira","7jim","%eric"};

        listItems=new ArrayList<>(Arrays.asList(items));
        adapter=new ArrayAdapter<String>(this,
                R.layout.textsearch_list_item, R.id.txtitem, listItems);
        listView.setAdapter(adapter);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textsearch_activity);
        listView=(ListView)findViewById(R.id.listview);
        editText=(EditText)findViewById(R.id.txtsearch);
        initList();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("")){
                    // reset listview
                    initList();
                }
                else{
                    // perform search
                    searchItem(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void searchItem(String textToSearch){
        for(String item:items){
            if(!item.contains(textToSearch)){
                listItems.remove(item);
            }
        }
        adapter.notifyDataSetChanged();
    }


}
