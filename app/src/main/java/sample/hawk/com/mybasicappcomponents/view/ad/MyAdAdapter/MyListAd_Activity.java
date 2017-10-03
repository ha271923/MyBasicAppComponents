package sample.hawk.com.mybasicappcomponents.view.ad.MyAdAdapter;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

import static sample.hawk.com.mybasicappcomponents.view.ad.MyAdAdapter.Constants.ALL_ITEMS;

/**
 * Hawk: My Callback(Listener) and Adapter Demo
 */

public class MyListAd_Activity extends Activity {

    private Context mContext;
    private MyListAd_Adapter mMyListAd_Adapter;
    private ListView listView = null;
    private IMyListAd_CallBack mMyListAd_Adapter_Listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.myloader2_activity);

        listView = (ListView)findViewById(R.id.contactlist);
        mMyListAd_Adapter = new MyListAd_Adapter(mContext);
        // Callback for MyListAd_Adapter, it will be called by your call in MyListAd_Adapter().
        mMyListAd_Adapter.setClassListener(new IMyListAd_CallBack() {
            @Override
            public void onAdLoaded(final int position) {
                SMLog.i();
                if(position == ALL_ITEMS)
                    Toast.makeText(mContext, "onAdLoaded!!  All loaded",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(mContext, "onAdLoaded!!  Item["+position+"] loaded",Toast.LENGTH_LONG).show();
            }
        });
        mMyListAd_Adapter_Listener.onAdLoaded(ALL_ITEMS);
        listView.setAdapter(mMyListAd_Adapter);

        // Callback for MyListAd_Adapter if any Android API: notifyDataSetChanged() called
        mMyListAd_Adapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                SMLog.i("ListView onChanged !!!");
            }
            @Override
            public void onInvalidated() {
                super.onInvalidated();
                SMLog.i("ListView onInvalidated~");
            }
        });
    }

    public void setClassListener(@Nullable final IMyListAd_CallBack listener) {
        mMyListAd_Adapter_Listener = listener;
    }


    public void onClick_LoadData(View view){
        mMyListAd_Adapter.LoadAd();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMyListAd_Adapter.destroy();
    }
}
