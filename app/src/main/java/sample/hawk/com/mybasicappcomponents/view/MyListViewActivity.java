package sample.hawk.com.mybasicappcomponents.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import sample.hawk.com.mybasicappcomponents.R;

/**
 * Created by ha271 on 2017/1/4.
 */

public class MyListViewActivity extends Activity {
    final static int ELEMENT_COUNT = 400;
    MyAdapter adapter;
    static String[] elements;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mylistview_activity); // your listview page layout
        elements = new String[ELEMENT_COUNT];
        // create an DB
        for (int i = 0; i< ELEMENT_COUNT; i++) {
            elements[i] = String.valueOf(i);
        }
        // link DB
        adapter = new MyAdapter(this,elements);
        final ListView list = (ListView) findViewById(R.id.mylistview);
        list.setDivider( null );
        list.setAdapter(adapter);

        AddButton(this);
    }

    void AddButton(Context context){
        //設定按鈕，按下後更新 ListView 內容。
        Button button = new Button(context);
        //button.setLayoutParams(new LinearLayout.LayoutParams(400, 150));
        button.setText("Click to Update Strings");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 修改陣列data內容
                elements[10] = "更改過字串 10"; // image
                elements[11] = "更改過字串 11";
                elements[12] = "更改過字串 12"; // image
                elements[13] = "更改過字串 13";
                elements[14] = "更改過字串 14"; // image


                // 通知資料被變動，更新 ListView 顯示內容。
                adapter.notifyDataSetChanged();
            }
        });
        // Add a new button into the layout.
        ViewGroup root = (ViewGroup) findViewById(R.id.root);
        root.addView(button, 0, new ViewGroup.LayoutParams(800, 200));
    }

}
