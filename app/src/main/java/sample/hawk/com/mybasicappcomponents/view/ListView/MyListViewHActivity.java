package sample.hawk.com.mybasicappcomponents.view.ListView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;
import sample.hawk.com.mybasicappcomponents.view.MyAdapter;

/**
 * Created by ha271 on 2017/1/4.
 */

public class MyListViewHActivity extends Activity {
    final static int ELEMENT_COUNT = 400;
    MyAdapter adapter;
    static String[] elements;
    static private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mylistviewh_activity); // your listview page layout
        elements = new String[ELEMENT_COUNT];
        // create an DB
        for (int i = 0; i< ELEMENT_COUNT; i++) {
            elements[i] = String.valueOf(i);
        }
        // link DB
        adapter = new MyAdapter(this,elements);
        final MyListViewH list = (MyListViewH) findViewById(R.id.mylistview);
        list.setDivider( null );
        list.setAdapter(adapter);
        // Actions
        mContext = this;
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                String result = "index: "+index;
                Toast.makeText(mContext, result, Toast.LENGTH_SHORT).show();
            }
        });

        AddButton(this);

        AddObserver(list);
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

    // onCreate(), onDraw()....這些方法都是View本身override才能控制的，那該如何在View"外面"得知這些動作發生呢？
    // 就是在ViewTreeObserver啦～
    void AddObserver(final View v){
        // Observer
        v.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                SMLog.i("OnGlobalLayoutListener");
                int width = v.getWidth();
                int height = v.getHeight();
                SMLog.i("View width=" + width + "  height=" + height);
                v.getViewTreeObserver().removeOnGlobalLayoutListener(this); // Remove to prevent infinite loop listener
            }
        });

        v.getViewTreeObserver().addOnWindowAttachListener(new ViewTreeObserver.OnWindowAttachListener() {
            @Override
            public void onWindowAttached() {
                SMLog.i("view is .onWindowAttached");
            }

            @Override
            public void onWindowDetached() {
                SMLog.i("view is .onWindowDetached");
            }
        });


    }


}
