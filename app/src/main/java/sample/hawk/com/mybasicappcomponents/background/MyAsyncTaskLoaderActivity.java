package sample.hawk.com.mybasicappcomponents.background;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.List;
import java.util.Random;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 Reference from http://www.cnblogs.com/zqlxtt/p/4825384.html

 行為:
    從SMLog可以看出來每隔三秒就會有新的顧客上門，顧客上門後麵包房通知麵包師需要做麵包哦了，接著麵包師就
    會在後台不停的開始使用麵包機（AsyncTask）做麵包。

 總結:
    說到這裡可能有些同學有疑問了，我怎麼從頭到尾都沒有開到AsyncTask的影子呢？你當然看不到，這就
    是AsyncTaskLoader設計精妙之處，它做到了讓你唯一需要考慮的就是烤麵包（Async處理）這個事物邏輯
    ，而不需要考慮異步處理本身的實現上。同時這也充分體現了設計模式中的单一職責和最少知道原則。

 使用場景:
    AsyncTaskLoader一般使用在數據源處於不斷更新，並且請求刷新數據源是個耗時操作的情況下還需要UI去
    同步更新相關數據的場景（這句話怎麼這麼拗口）。

 */

public class MyAsyncTaskLoaderActivity extends Activity {

    Context m_context;
    public static ProgressBar mMainActivityProgressBar;
    public ToggleButton mMyAsyncTaskToggleBtn;
    public TextView mMyOutputTextView;
    public TextView mTvSeller;
    public TextView mTvConsumer;
    public static int pa;

    private LoaderManager.LoaderCallbacks<List<String>> mCallbacks;
    private MyAsyncTaskLoader.Bakery mFactory; //面包房
    private MyAsyncTaskLoader mCooker; //面包师
    private int mNeededBreads; //面包需求量
    private final int mLoaderId = 42; //唯一标识

    public MyAsyncTaskLoader.BakeryCallback mBreadCallback = new MyAsyncTaskLoader.BakeryCallback() {
        @Override
        public int getNeededBreads() {
            return mNeededBreads;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        m_context = this;
        setContentView(R.layout.myasynctaskloaderactivity);
        mMainActivityProgressBar = (ProgressBar) findViewById(R.id.myainactivity_progressBar);
        mMyOutputTextView        = (TextView) findViewById(R.id.OutputTextView);
        mTvSeller                = (TextView) findViewById(R.id.mTvSeller);
        mTvConsumer              = (TextView) findViewById(R.id.tvConsumer);
        mMyAsyncTaskToggleBtn    = (ToggleButton) findViewById(R.id.AsyncTaskToggleBtn);
        mMyAsyncTaskToggleBtn.setOnClickListener(mMyAsyncTaskToggleBtnListener);

        mNeededBreads = 0;
        mCooker = new MyAsyncTaskLoader(this, mBreadCallback);
        mFactory = mCooker.new Bakery(mCooker);
        mCallbacks = new LoaderManager.LoaderCallbacks<List<String>>() {
            @Override
            public Loader<List<String>> onCreateLoader(int id, Bundle args) {
                if (mCooker == null) {
                    mCooker = new MyAsyncTaskLoader(MyAsyncTaskLoaderActivity.this, mBreadCallback);
                }
                return mCooker;
            }
            @Override
            public void onLoadFinished(Loader<List<String>> loader, List<String> data) {
                mNeededBreads = 0 ;
                SMLog.i("sell " + data.size() + " breads"); //面包师完成面包烤制
                mTvSeller.setText("Sell ="+data.size());
                mMainActivityProgressBar.setProgress(pa++);
            }
            @Override
            public void onLoaderReset(Loader<List<String>> loader) {

            }
        };
        getLoaderManager().restartLoader(mLoaderId, null, mCallbacks); //面包师开始工作
        mockCustomer(); //顾客开始上门
    }

    private View.OnClickListener mMyAsyncTaskToggleBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SMLog.i();
            if(mMyAsyncTaskToggleBtn.isChecked()){
                SMLog.i("START  AsyncTaskLoader!!");
                mCooker.startLoading();
            }
            else{
                SMLog.i("STOP   AsyncTaskLoader");
                mCooker.stopLoading();
                mTvSeller.setText("Sell = STOP to sell!");
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mFactory); // Hawk: Maybe this Exception is caused by I modified the Bakery class as inner class.
    }

    //模拟源源不断的顾客需求
    private void mockCustomer(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(3000);
                        Random random = new Random();
                        mNeededBreads =random.nextInt(10);
                        Intent intent = new Intent(MyAsyncTaskLoader.CUSTOMER_ACTION);
                        intent.putExtra("mNeededBreads",mNeededBreads);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mTvConsumer.setText("Customer mNeededBreads="+mNeededBreads);
                            }
                        });
                        sendBroadcast(intent);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}