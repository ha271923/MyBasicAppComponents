package sample.hawk.com.mybasicappcomponents.background;

import android.content.AsyncTaskLoader;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/3/2.
 */

public class MyAsyncTaskLoader extends AsyncTaskLoader<List<String>> {

    static public String CUSTOMER_ACTION = "sample.hawk.com.mybasicappcomponents.background.MyAsyncTaskLoaderActivity.new_customer";

    public class Bakery extends BroadcastReceiver {

        MyAsyncTaskLoader mBaker;

        public Bakery(MyAsyncTaskLoader baker) {
            mBaker = baker;
            IntentFilter filter = new IntentFilter(CUSTOMER_ACTION);
            baker.getContext().registerReceiver(this, filter);
        }

        @Override public void onReceive(Context context, Intent intent) {
            //通知面包师来客人了，要做面包了。
            Bundle bundle = intent.getExtras();
            SMLog.i("Customer is comming! mNeededBreads="+ bundle.getInt("mNeededBreads"));
            mBaker.onContentChanged();
        }

    }

    BakeryCallback mCallback; // 用于查询当前需要多少个面包
    interface BakeryCallback { //面包房callback，用于获得当面面包需求量
        int getNeededBreads();
    }

    public MyAsyncTaskLoader(Context context, BakeryCallback callback) {
        super(context);
        mCallback = callback;
    }

    @Override
    public List<String> loadInBackground() {
        List<String> breads = new ArrayList<String>();
        int needs = mCallback.getNeededBreads(); //获得当前需要做的面包
        for (int i = 0; i < needs; i++) {
            breads.add(new String("i="+i)); //制作面包，耗时操作
        }
        // you will get breads at onLoadFinished(Loader<List<String>> loader, List<String> data)'s data parameter.
        return breads; //面包制作完成
    }

    @Override
    public void deliverResult(List<String> data) {
        super.deliverResult(data);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    protected void onReset() {
        super.onReset();
    }


}
