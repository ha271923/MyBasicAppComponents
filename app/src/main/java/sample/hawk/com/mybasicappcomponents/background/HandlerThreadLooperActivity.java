package sample.hawk.com.mybasicappcomponents.background;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * 整個Handler, Message, MessageQueue, Looper 它們四個 class 只有一個共同的目標:
   A: 就是讓 job程式碼，可以任意丟到其它 thread 去執行
 */
public class HandlerThreadLooperActivity extends Activity {

    private TextView txtView;
    private static int count = 0; // Hawk: static variable to keep this variable whatever BACK or HOME key pressed.
    private final int MAX_COUNT = 10;
// 註釋: HandlerThread也是Thread的一種, 不想像MyThread.java一樣去控制looper(ex:Looper.prepare();...Looper.loop();), 可以改用HandlerThread
// Handler只是處理訊息的, Looper是讓Thread本身變成無窮迴圈運行, Runnable放著你要再背景運行的CODE
    private HandlerThread mHandlerThread;
    private Handler mHandler;
    private Context mContext=this;
    private int mCount;
//region = 寫法1: HandlerThread*1 + Handler*1 + Runnable*2, 更新UI與DATA
    private Handler mMain_Handler1 = new Handler(); // In Activity, this is equal with new Handler(mContext.getMainLooper());.
    void Test1(){
        mHandlerThread = new HandlerThread("myHandlerThread-1");
        mHandlerThread.start();
        mHandler = new Handler(mHandlerThread.getLooper()); // KEY point: Not UI looper
        mHandler.post(updateData1);
    }
    private Runnable updateData1 = new Runnable() { // I can't update UI.
        @Override
        public void run() { // run on worker thread
            // TODO: put your background job at here
            try {
                for (; count < MAX_COUNT; ) {
                    Thread.sleep(1000);
                    count++;
                    SMLog.i("updateData Runnable() tID="+Thread.currentThread().getId()); // tID=worker
                    mMain_Handler1.post(updateUI1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    private Runnable updateUI1 = new Runnable() { // I can update UI.
        @Override
        public void run() { // run on UI thread
            SMLog.i("updateUI Runnable() tID="+Thread.currentThread().getId()); // tID=1
            txtView.setText(Integer.toString(count));
        }
    };
//endregion

//region = 寫法2: HandlerThread*1 + Handler*1(2個Looper間動態切換以應需求UI,Data)+ Runnable*2 , 更新UI與DATA
    //private Handler mMain_Handler2 = new Handler(); // In this sample class, the default Looper will be UI looper.
    void Test2(){
        mHandlerThread = new HandlerThread("myHandlerThread-2");
        mHandlerThread.start();
        mHandler = new Handler(mHandlerThread.getLooper()); // KEY point: Not UI looper
        mHandler.post(updateData2);
    }

    private Runnable updateData2 = new Runnable() { // I can't update UI.
        @Override
        public void run() {
            // TODO: put your background job at here
            try {
                for (; mCount < MAX_COUNT; ) {
                    Thread.sleep(1000);
                    mCount++;
                    SMLog.i("updateData Runnable() tID="+Thread.currentThread().getId()); // tID=worker
                    mHandler = new Handler(mContext.getMainLooper()); // KEY point: worker thread switch to UI looper
                    mHandler.post(updateUI2);
                    //mMain_Handler2.post(updateUI);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    private Runnable updateUI2 = new Runnable() { // I can update UI, because new Handler(mContext.getMainLooper()); already.
        @Override
        public void run() {
            SMLog.i("updateUI Runnable() tID="+Thread.currentThread().getId()); // tID=1
            txtView.setText(Integer.toString(mCount));
        }
    };

//endregion

//region = 寫法3: HandlerThread*1 + Handler*2(在handleMessage內部直接處理msg)+ Runnable*1 , 更新UI與DATA
    private static final int MSG_UPDATE_UI_H3 = 33333;
    void Test3(){
        mHandlerThread = new HandlerThread("myHandlerThread-3");
        mHandlerThread.start();
        mHandler = new Handler(mHandlerThread.getLooper()); // KEY POINT: The default looper
        mHandler.post(updateData3);
    }
    private Runnable updateData3 = new Runnable() { // I can't update UI.
        @Override
        public void run() {
            // TODO: put your background job at here
            try {
                for (; mCount < MAX_COUNT; ) {
                    Thread.sleep(1000);
                    mCount++;
                    SMLog.i("updateData Runnable() tID="+Thread.currentThread().getId()); // tID=worker
                    mMain_Handler3.sendEmptyMessage(MSG_UPDATE_UI_H3); // KEY POINT:
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    private Handler mMain_Handler3 = new Handler(){ // I can update UI, because new it under Activity class(UI thread).
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            SMLog.i("handleMessage() tID=" + Thread.currentThread().getId()); // tID=1
            switch(msg.what){
                case MSG_UPDATE_UI_H3:
                    txtView.setText(Integer.toString(mCount));
                    break;
                default:
                    break;
            }
        }
    };

//endregion

//region = 寫法4: HandlerThread*0 + Handler*1(在handleMessage內部直接處理msg)+ Runnable*0 +Thread.run()*1 , 更新UI與DATA
    private static final int MSG_UPDATE_UI_H4 = 44444;
    void Test4(){
        mThread.start();
    }
    private Thread mThread = new Thread(){
        @Override
        public void run() {
            // TODO: put your background job at here
            try {
                for (; mCount < MAX_COUNT; ) {
                    Thread.sleep(1000);
                    mCount++;
                    SMLog.i("updateData workerThread tID="+Thread.currentThread().getId()); // tID=worker
                    mMain_Handler4.sendEmptyMessage(MSG_UPDATE_UI_H4); // KEY POINT:
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    private Handler mMain_Handler4 = new Handler(){
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            SMLog.i( "handleMessage() tID=" + Thread.currentThread().getId()); // tID=1
            switch(msg.what){
                case MSG_UPDATE_UI_H4:
                    txtView.setText(Integer.toString(mCount));
                    break;
                default:
                    break;
            }
        }
    };

//endregion

//region = 錯誤寫法5 error: HandlerThread*0 + Handler*0 + Runnable*1 +Thread.run()*0 , ANR error!!
    void Test5(){
        mThread = new Thread(update_Data_UI_H5);
        mThread.start();
    }

    private Runnable update_Data_UI_H5 = new Runnable() { // worker thread
        @Override
        public void run() {
            // TODO: put your background job at here
            // Looper.myLooper();
            try {
                for (; mCount < MAX_COUNT; ) {
                    Thread.sleep(1000);
                    mCount++;
                    SMLog.e("updateData Runnable() tID="+Thread.currentThread().getId()); // tID=worker
                    txtView.setText(Integer.toString(mCount));// ERROR: worker thread can't update UI.
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Looper.loop();
        }
    };
//endregion

//region = 錯誤寫法6 error: HandlerThread*1 + Handler*1 + Runnable*1 +Thread.run()*0 , callback*1
    private static final int MSG_UPDATE_UI_H6 = 66666;
    void Test6(){
        mHandlerThread = new HandlerThread("myHandlerThread-6");
        mHandlerThread.start();
        mHandler = new Handler(mHandlerThread.getLooper(), mCallback); // KEY POINT: ERROR: Should be MainLooper if msg will update UI.
        // mHandler = new Handler(this.getMainLooper(), mCallback); // KEY POINT: WARNING: all jobs(run(),handleMessage()) are run at UI thread.
        mHandler.post(updateData6);
    }
    private Runnable updateData6 = new Runnable() { // I can't update UI.
        @Override
        public void run() {
            // TODO: put your background job at here
            try {
                for (; mCount < MAX_COUNT; ) {
                    Thread.sleep(1000);
                    mCount++;
                    SMLog.i("updateData Runnable() tID="+Thread.currentThread().getId()); // tID=worker
                    mHandler.sendEmptyMessage(MSG_UPDATE_UI_H6);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    private Handler.Callback mCallback = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch(msg.what) {
                case MSG_UPDATE_UI_H6: {
                    SMLog.i("Callback{...} tID="+Thread.currentThread().getId()); // tID=worker
                    txtView.setText(Integer.toString(mCount));// ERROR: worker thread can't update UI.
                    return true;
                }
                default:
                    return false;
            }
        }
    };
//endregion

//region =
    void Test7(){

    }
//endregion

//region =
    void Test8(){

    }
//endregion

//region =
    void Test9(){

    }
//endregion

    // UI
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handlerthreadlooper);
        txtView = (TextView) findViewById(R.id.myValue);
        ((Button)findViewById(R.id.button1)).setText("");
        ((Button)findViewById(R.id.button2)).setText("");
        ((Button)findViewById(R.id.button3)).setText("");
        ((Button)findViewById(R.id.button4)).setText("");
        ((Button)findViewById(R.id.button5)).setText("ERR: ");
        ((Button)findViewById(R.id.button6)).setText("ERR: non-MainLooper with callback");
        ((Button)findViewById(R.id.button7)).setText("");
        ((Button)findViewById(R.id.button8)).setText("");
        ((Button)findViewById(R.id.button9)).setText("");
    }

    public void onClick_Buttons(View v) {
        String Tag = v.getTag().toString();
        which_style(Integer.parseInt(Tag));
    }


    private void which_style(int styleId){
        if(mHandlerThread !=null){
            mHandlerThread.interrupt();
            mHandlerThread = null;
        }
        if(mHandler != null){
            mHandler = null;
        }
        mCount = 0;
        SMLog.e("styleId="+styleId+"   -------------------------");
        switch (styleId){
            case 1:
                Test1();
                break;
            case 2:
                Test2();
                break;
            case 3:
                Test3();
                break;
            case 4:
                Test4();
                break;
            case 5:
                Test5();
                break;
            case 6:
                Test6();
                break;
            case 7:
                Test7();
                break;
            case 8:
                Test8();
                break;
            case 9:
                Test9();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        if(mHandlerThread !=null){
            mHandlerThread.interrupt();
            mHandlerThread = null;
        }
        if(mHandler != null){
            mHandler = null;
        }
        mCount = 0;
        mMain_Handler1=null;
        // mMain_Handler2=null;
        mMain_Handler3=null;
        mMain_Handler4=null;

        update_Data_UI_H5 = null;

        super.onDestroy();
    }
}