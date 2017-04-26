package sample.hawk.com.mybasicappcomponents.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

import static sample.hawk.com.mybasicappcomponents.utils.Util.isUiThread;

/**
 * Created by ha271 on 2017/1/4.
 */

public class MyDynamicListViewActivity extends Activity {
    final static int ELEMENT_COUNT = 20;
    MyAdapter_ArrayList adapter;
    static ArrayList<String> elements;
    static private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mylistview_activity); // your listview page layout
        elements = new ArrayList<String>();
        // create an DB
        for (int i = 0; i< ELEMENT_COUNT; i++) {
            elements.add(Integer.toString(i));
        }
        // link DB
        adapter = new MyAdapter_ArrayList(this,elements);
        final ListView list = (ListView) findViewById(R.id.mylistview);
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
                // String in Java is immutable, do not modify the existing one.
                elements.remove(9);
                elements.remove(8);
                elements.remove(7);
                elements.remove(6);

                elements.add(6,"更改過字串 6"); // ImageView
                elements.add(7,"更改過字串 7"); // TextView
                elements.add(8,"更改過字串 8"); // ImageView
                elements.add(9,"更改過字串 9"); // TextView

                ModifyAdapterData(true); // Adapter can only be modified in UI thread.
                // ModifyAdapterData(false);   // java.lang.IllegalStateException: The content of the adapter has changed but ListView did not receive a notification. Make sure the content of your adapter is not modified from a background thread, but only from the UI thread.
                // 通知資料被變動，更新 ListView 顯示內容。
                adapter.notifyDataSetChanged(); // MUST call notifyDataSetChanged() api, even the data was modified in UI thread.
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


    /*
        Android EXCEPTION code:
        ...
        else if (mItemCount != mAdapter.getCount()) {
        throw new IllegalStateException("The content of the adapter has changed but "
        + "ListView did not receive a notification. Make sure the content of "
        + "your adapter is not modified from a background thread, but only from "
        + "the UI thread. Make sure your adapter calls notifyDataSetChanged() "
        + "when its content changes. [in ListView(" + getId() + ", " + getClass()
        + ") with Adapter(" + mAdapter.getClass() + ")]");
        }
    */
    void ModifyAdapterData(boolean InUIThread){
        //
        if(InUIThread == true && isUiThread()){
            elements.add(elements.size(),"新增字串 "+elements.size());
        }
        else // ERROR: java.lang.IllegalStateException: The content of the adapter has changed but ListView did not receive a notification. Make sure the content of your adapter is not modified from a background thread, but only from the UI thread.
        {
            new Thread("Modify Adapter data in worker Thread"){
                @Override
                public void run() {
                    elements.add(elements.size(),"新增字串 "+elements.size());
                }
            }.start();
        }


    }


}
