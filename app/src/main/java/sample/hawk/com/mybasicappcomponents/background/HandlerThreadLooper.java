package sample.hawk.com.mybasicappcomponents.background;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.widget.TextView;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/4/7.
 */
public class HandlerThreadLooper extends Activity {

    private TextView txtView;
    private static int count = 0; // Hawk: static variable to keep this variable whatever BACK or HOME key pressed.

// 註釋: HandlerThread也是Thread的一種, 不想像Thread一樣去控制looper(ex:Looper.prepare();...Looper.loop();), 可以改用HandlerThread
// Handler只是處理訊息的, Looper是讓Thread本身變成無窮迴圈運行, Runnable放著你要再背景運行的CODE

//region = 寫法1: HandlerThread*1 + Handler*2 + Runnable*2, 更新UI與DATA

    private HandlerThread mhThread;
    private Handler mHandler;
    private Handler mMain_Handler = new Handler(); // In this sample class, the default Looper will be UI looper.
    private Context mContext=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handlerthreadlooper);
        txtView = (TextView) findViewById(R.id.myValue);
        mhThread = new HandlerThread("myHT");
        mhThread.start();
        SMLog.i("TEST", "HandlerThread tID="+ mhThread.getId());
        mHandler = new Handler(mhThread.getLooper()); // KEY point: Not UI looper
        SMLog.i("TEST", "onCreate() tID=" + Thread.currentThread().getId());
        mHandler.post(updateData);
    }
    private Runnable updateData = new Runnable() { // I can't update UI.
        @Override
        public void run() { // run on worker thread
            // TODO: put your background job at here
            try {
                for (; count < 1000; ) {
                    Thread.sleep(1000);
                    count++;
                    SMLog.i("TEST","updateData Runnable() tID="+Thread.currentThread().getId());
                    mMain_Handler.post(updateUI);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    private Runnable updateUI = new Runnable() { // I can update UI.

        @Override
        public void run() { // run on UI thread
            SMLog.i("TEST","updateUI Runnable() tID="+Thread.currentThread().getId());
            txtView.setText(Integer.toString(count));
        }
    };

//endregion

//region = 寫法2: HandlerThread*1 + Handler*1(動態切換Looper以應需求)+ Runnable*2 , 更新UI與DATA
/*
    private HandlerThread mhThread;
    private Handler mHandler;
    //private Handler mMain_Handler = new Handler(); // In this sample class, the default Looper will be UI looper.
    private Context mContext=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handlerthreadlooper);
        txtView = (TextView) findViewById(R.id.myValue);
        mhThread = new HandlerThread("myHT");
        mhThread.start();
        mHandler = new Handler(mhThread.getLooper()); // KEY point: Not UI looper
        SMLog.i("TEST", "onCreate() tID=" + Thread.currentThread().getId());
        mHandler.post(updateData);
    }

    private Runnable updateData = new Runnable() { // I can't update UI.
        @Override
        public void run() {
            // TODO: put your background job at here
            try {
                for (; mCount < 1000; ) {
                    Thread.sleep(1000);
                    mCount++;
                    SMLog.i("TEST","updateData Runnable() tID="+Thread.currentThread().getId());
                    mHandler = new Handler(mContext.getMainLooper()); // KEY point: worker thread switch to UI looper
                    mHandler.post(updateUI);
                    //mMain_Handler.post(updateUI);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    private Runnable updateUI = new Runnable() { // I can update UI, because new Handler(mContext.getMainLooper()); already.

        @Override
        public void run() {
            SMLog.i("TEST","updateUI Runnable() tID="+Thread.currentThread().getId());
            txtView.setText(Integer.toString(mCount));
        }
    };
*/
//endregion

//region = 寫法3: HandlerThread*1 + Handler*2(在handleMessage內部直接處理msg)+ Runnable*1 , 更新UI與DATA
/*
    private HandlerThread mhThread;
    private Handler mHandler;
    private static final int MSG_UPDATE_UI = 12345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handlerthreadlooper);
        txtView = (TextView) findViewById(R.id.myValue);
        mhThread = new HandlerThread("myHT");
        mhThread.start();
        mHandler = new Handler(mhThread.getLooper()); // KEY POINT: The default looper
        SMLog.i("TEST", "onCreate() tID=" + Thread.currentThread().getId());
        mHandler.post(updateData);
    }
    private Runnable updateData = new Runnable() { // I can't update UI.
        @Override
        public void run() {
            // TODO: put your background job at here
            try {
                for (; mCount < 1000; ) {
                    Thread.sleep(1000);
                    mCount++;
                    SMLog.i("TEST","updateData Runnable() tID="+Thread.currentThread().getId());
                    mMain_Handler.sendEmptyMessage(MSG_UPDATE_UI); // KEY POINT:
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    private Handler mMain_Handler = new Handler(){ // I can update UI, because new it under Activity class(UI thread).
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            SMLog.i("TEST", "handleMessage() tID=" + Thread.currentThread().getId());
            switch(msg.what){
                case MSG_UPDATE_UI:
                    txtView.setText(Integer.toString(mCount));
                    break;
                default:
                    break;
            }
        }
    };
*/
//endregion

//region = 寫法4: HandlerThread*0 + Handler*1(在handleMessage內部直接處理msg)+ Runnable*0 +Thread.run()*1 , 更新UI與DATA
/*
    private static final int MSG_UPDATE_UI = 12345;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handlerthreadlooper);
        txtView = (TextView) findViewById(R.id.myValue);
        mThread.start();
        SMLog.i("TEST", "onCreate() tID=" + Thread.currentThread().getId());
    }
    private Thread mThread = new Thread(){ // I can't update UI.
        @Override
        public void run() {
            // TODO: put your background job at here
            try {
                for (; mCount < 1000; ) {
                    Thread.sleep(1000);
                    mCount++;
                    SMLog.i("TEST","updateData workerThread tID="+Thread.currentThread().getId());
                    mMain_Handler.sendEmptyMessage(MSG_UPDATE_UI); // KEY POINT:
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    private Handler mMain_Handler = new Handler(){ // I can update UI.
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            SMLog.i("TEST", "handleMessage() tID=" + Thread.currentThread().getId());
            switch(msg.what){
                case MSG_UPDATE_UI:
                    txtView.setText(Integer.toString(mCount));
                    break;
                default:
                    break;
            }
        }
    };
*/
//endregion

//region = 錯誤寫法5 error: HandlerThread*0 + Handler*0 + Runnable*1 +Thread.run()*0 , ANR error!!
/*
    private static final int MSG_UPDATE_UI = 12345;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handlerthreadlooper);
        txtView = (TextView) findViewById(R.id.myValue);
        Thread mThread = new Thread(update_Data_UI);
        mThread.start();
        SMLog.i("TEST", "onCreate() tID=" + Thread.currentThread().getId());
    }

    private Runnable update_Data_UI = new Runnable() { // worker thread
        @Override
        public void run() {
            try {
                for (; mCount < 1000; ) {
                    Thread.sleep(1000);
                    mCount++;
                    SMLog.e("TEST","updateData Runnable() tID="+Thread.currentThread().getId());
                    txtView.setText(Integer.toString(mCount));// ERROR: worker thread can't update UI.
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
*/
//endregion

}