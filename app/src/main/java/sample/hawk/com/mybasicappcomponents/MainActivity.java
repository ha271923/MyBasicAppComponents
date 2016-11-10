package sample.hawk.com.mybasicappcomponents;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Timer;
import java.util.TimerTask;

import sample.hawk.com.mybasicappcomponents.animation.AnimationActivity;
import sample.hawk.com.mybasicappcomponents.background.HandlerThreadLooper;
import sample.hawk.com.mybasicappcomponents.background.MyIntentService;
import sample.hawk.com.mybasicappcomponents.background.MyJobSchedulerService;
import sample.hawk.com.mybasicappcomponents.background.MyLocalService;
import sample.hawk.com.mybasicappcomponents.background.MyThread;
import sample.hawk.com.mybasicappcomponents.view.MyListViewActivity;
import sample.hawk.com.mybasicappcomponents.oo.MyInterface;
import sample.hawk.com.mybasicappcomponents.oo.MyObjectClass;
import sample.hawk.com.mybasicappcomponents.view.MyListView3DActivity;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;
import sample.hawk.com.mybasicappcomponents.view.MySurfaceView;
import sample.hawk.com.mybasicappcomponents.view.MySurfaceViewActivity;
import sample.hawk.com.mybasicappcomponents.view.MyViewActivity;
import sample.hawk.com.mybasicappcomponents.view.MyViewGroupActivity;

public class MainActivity extends Activity implements MyInterface {
    private static final String TAG = "[MainActivity]";
    public  static final int MY_REQUEST_CODE = 123456789;// Hawk: To Prvent two pendingIntent overwrite, you can define the diff REQUEST_CODE everytime.

    private Context mContext;
    // This don't defined in AndroidManifest.xml
    public static String UPDATE_MAINACTIVITY_ACTION = "sample.hawk.com.mybasicappcomponents.MainActivity.UPDATE_UI_ACTION";
    public static String UPDATE_MAINACTIVITY_PB_ACTION = "sample.hawk.com.mybasicappcomponents.MainActivity.UPDATE_PB_ACTION";

    private MyThread mMyThread1;
    private Thread mThread2;
    private MyThread mMyThread3;

    public static TextView mActivity_status;
    public static TextView mService_status;
    public static TextView mReceiver_status;
    public static TextView mContentprovider_status;

    public static ProgressBar mMainActivityProgressBar;
    public static int pa;
    public ProgressBar mMyServiceProgressBar;
    private static int ps;
    public ProgressBar mMyReceiverProgressBar;
    private static int pr;
    public ProgressBar mMyContentProviderProgressBar;
    private static int pc;

    public Button mMyActivityBtn;
    public Button mMyListViewBtn;
    public Button mMyListView3DBtn;
    public Button mMyViewBtn;
    public Button mMyViewGroupBtn;
    public Button mMySurfaceViewBtn;
    public Button mMyJavaActivityBtn;
    public Button mMyAnimationActivityBtn;
    public Button mHandlerThreadLooperBtn;
    public Button mSendMsg_fromUI_to_Thread_Btn;
    public ToggleButton mMyLocalServiceToggleBtn;
    public ToggleButton mMyBindServiceToggleBtn;
    public ToggleButton mMyIntentServiceToggleBtn;
    public ToggleButton mMyAlarmManagerToggleBtn;
    public ToggleButton mMyAsyncTaskToggleBtn;
    public ToggleButton mMyJobSchedulerToggleBtn;
    public ToggleButton mMyThreadToggleBtn;
    public Button mMyReceiverBtn;public Button mMyBroadcastBtn;
    public TextView mMyOutputTextView;public TextView mMyTimeTextView;
    public Button mMyProviderBtn;
    public Button mMygetBindServiceResultBtn;
    private BroadcastReceiver mMyReceiver = new MyReceiver();

    private JobScheduler mMyJobScheduler;


    public void SettingsBtnClick(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    // Application Model -----------------------------------------------------------------
    // UPDATE_UI WAY1B: an inner class for receive ui update event.
    MainActivityUpdateReceiver mMainActivityUpdateReceiver;
    public class MainActivityUpdateReceiver extends BroadcastReceiver { // inner class can't be registered by AndroidManifest.xml.
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(MainActivity.UPDATE_MAINACTIVITY_ACTION.equals(action)){
                Bundle bundle = intent.getExtras();
                String To_mMyOutputView = bundle.getString("To_mMyOutputView");
                mMyTimeTextView.setText(To_mMyOutputView);
                if(ps>100)
                    ps=0;
                mMyServiceProgressBar.setProgress(ps++);
            }
            if(MainActivity.UPDATE_MAINACTIVITY_PB_ACTION.equals(action)){
                if(ps>100)
                    ps=0;
                mMyServiceProgressBar.setProgress(ps++);
            }
        }
    }
    private void register_MainActivityUpdateReceiver(){
        mMainActivityUpdateReceiver = new MainActivityUpdateReceiver();
        IntentFilter filter = new IntentFilter(UPDATE_MAINACTIVITY_ACTION);
        registerReceiver(mMainActivityUpdateReceiver, filter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); SMLog.i();
        mContext = this;

        // Test abstract class and interface class
        // MyAbstractClass AbObj = new MyAbstractClass(); // JAVA: you can't new a abstract class obj directly.
        MyObjectClass Obj = new MyObjectClass(); // JAVA: OO Inheritance tech, MyAbstractClass->MyObjectClass
        Obj.MyAbstractFunction(333);
        Obj.MyRealFunction(55555);

        MyInterfaceAPI(1111); // JAVA: OO Composition tech

        // Java Anonymous class
        new MyInterface(){
            @Override
            public void MyInterfaceAPI(int i) {
                SMLog.i(TAG, "MyInterfaceAPI = Java Anonymous class" );
            }
        };

        MyInterface ObjIf = new MyInterface(){
            @Override
            public void MyInterfaceAPI(int i) {
                SMLog.i(TAG, "MyInterfaceAPI = Java Anonymous class" );
            }
        };
        ObjIf.MyInterfaceAPI(4444);

        MyInterfaceAPI(9999); // JAVA: OO Composition tech

        // View
        setContentView(R.layout.mainactivity);
        mActivity_status= (TextView) findViewById(R.id.activity_status);
        mService_status= (TextView) findViewById(R.id.service_status);
        mReceiver_status= (TextView) findViewById(R.id.receiver_status);
        mContentprovider_status= (TextView) findViewById(R.id.contentprovider_status);

        mMyTimeTextView= (TextView) findViewById(R.id.time_textView);
        mMainActivityProgressBar = (ProgressBar) findViewById(R.id.myainactivity_progressBar);
        mMyServiceProgressBar = (ProgressBar) findViewById(R.id.myservice_progressBar);
        mMyReceiverProgressBar = (ProgressBar) findViewById(R.id.myreceiver_progressBar);
        mMyContentProviderProgressBar = (ProgressBar) findViewById(R.id.mycontentprovider_progressBar);
        mMyOutputTextView = (TextView) findViewById(R.id.OutputTextView);
        mMyActivityBtn = (Button) findViewById(R.id.ActivityBtn);
        mMyViewBtn = (Button) findViewById(R.id.MyViewBtn);
        mMyViewGroupBtn = (Button) findViewById(R.id.MyViewGroupBtn);
        mMySurfaceViewBtn = (Button) findViewById(R.id.MySurfaceViewBtn);

        mMyListViewBtn = (Button) findViewById(R.id.ListViewBtn);
        mMyListView3DBtn = (Button) findViewById(R.id.ListView3DBtn);
        mMyJavaActivityBtn = (Button) findViewById(R.id.MyJavaActivityBtn);
        mMyAnimationActivityBtn = (Button) findViewById(R.id.MyAnimationActivityBtn);

        mHandlerThreadLooperBtn = (Button) findViewById(R.id.HandlerThreadLooperBtn);
        mMyLocalServiceToggleBtn  = (ToggleButton) findViewById(R.id.LocalServiceToggleBtn);
        mMyBindServiceToggleBtn  = (ToggleButton) findViewById(R.id.BindServiceToggleBtn);
        mMyIntentServiceToggleBtn= (ToggleButton) findViewById(R.id.IntentServiceToggleBtn);
        mMyAsyncTaskToggleBtn= (ToggleButton) findViewById(R.id.AsyncTaskToggleBtn);
        mMyAlarmManagerToggleBtn= (ToggleButton) findViewById(R.id.AlarmManagerToggleBtn);
        mMyJobSchedulerToggleBtn= (ToggleButton) findViewById(R.id.JobSchedulerToggleBtn);
        mSendMsg_fromUI_to_Thread_Btn = (Button) findViewById(R.id.SendMsg_fromUI_to_Thread_Btn);
        mMyThreadToggleBtn= (ToggleButton) findViewById(R.id.ThreadToggleBtn);
        mMyReceiverBtn = (Button) findViewById(R.id.ReceiverBtn);
        mMyBroadcastBtn = (Button) findViewById(R.id.BroadcastBtn);
        mMyProviderBtn = (Button) findViewById(R.id.ProviderBtn);
        mMygetBindServiceResultBtn = (Button) findViewById(R.id.getBindServiceResultBtn);

        // WAY1: A broadcastReceiver for UI update event.
        register_MainActivityUpdateReceiver();

        // Presenter
        mMyActivityBtn.setOnClickListener(mMyActivityBtnListener);
        mMyListViewBtn.setOnClickListener(mMyListViewBtnListener);
        mMyViewBtn.setOnClickListener(mMyViewBtnListener);
        mMyViewGroupBtn.setOnClickListener(mMyViewGroupBtnListener);
        mMySurfaceViewBtn.setOnClickListener(mMySurfaceViewBtnListener);

        mMyListView3DBtn.setOnClickListener(mMyListView3DBtnListener);
        mMyJavaActivityBtn.setOnClickListener(mMyJavaActivityBtnListener);
        mMyAnimationActivityBtn.setOnClickListener(mMyAnimationActivityBtnListener);
        mHandlerThreadLooperBtn.setOnClickListener(mHandlerThreadLooperBtnListener);
        mMyLocalServiceToggleBtn.setOnClickListener(mMyLocalServiceToggleBtnListener);
        mMyBindServiceToggleBtn.setOnClickListener(mMyBindServiceToggleBtnListener);
        mMyIntentServiceToggleBtn.setOnClickListener(mMyIntentServiceToggleBtnListener);
        mMygetBindServiceResultBtn.setOnClickListener(mMygetBindServiceResultBtnListener);
        mMyAsyncTaskToggleBtn.setOnClickListener(mMyAsyncTaskToggleBtnListener);
        mMyAlarmManagerToggleBtn.setOnClickListener(mMyAlarmManagerToggleBtnListener);
        mMyJobSchedulerToggleBtn.setOnClickListener(mMyJobSchedulerToggleBtnListener);
        mMyThreadToggleBtn.setOnClickListener(mMyThreadToggleBtnListener);
        mSendMsg_fromUI_to_Thread_Btn.setOnClickListener(mSendMsg_fromUI_to_Thread_BtnListener);
        mMyReceiverBtn.setOnClickListener(mMyReceiverBtnListener);
        mMyBroadcastBtn.setOnClickListener(mMyBroadcastBtnListener);
        mMyProviderBtn.setOnClickListener(mMyProviderBtnListener);

        // Show a progress bar running
        // main_update_PB_thread(); // only for demo runOnUiThread() API

        mMyTask= new  MyAsyncTask();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mMainActivityUpdateReceiver);
    }

    // Model
    //Only For Activity -------------------------------------------------------------------------
    private OnClickListener mMyActivityBtnListener =new OnClickListener() {
        @Override
        public void onClick(View v) {
            SMLog.i();
            Intent  intent = new Intent();
            intent.setClass( MainActivity.this, MyActivity.class);
            startActivity(intent);
        }
    };

    private OnClickListener mMyListViewBtnListener =new OnClickListener() {
        @Override
        public void onClick(View v) {
            SMLog.i();
            Intent  intent = new Intent();
            intent.setClass( MainActivity.this, MyListViewActivity.class);
            startActivity(intent);
        }
    };

    private OnClickListener mMyViewBtnListener =new OnClickListener() {
        @Override
        public void onClick(View v) {
            SMLog.i();
            Intent  intent = new Intent();
            intent.setClass( MainActivity.this, MyViewActivity.class);
            startActivity(intent);
        }
    };

    private OnClickListener mMyViewGroupBtnListener =new OnClickListener() {
        @Override
        public void onClick(View v) {
            SMLog.i();
            Intent  intent = new Intent();
            intent.setClass( MainActivity.this, MyViewGroupActivity.class);
            startActivity(intent);
        }
    };
    private OnClickListener mMySurfaceViewBtnListener =new OnClickListener() {
        @Override
        public void onClick(View v) {
            SMLog.i();
            Intent  intent = new Intent();
            intent.setClass( MainActivity.this, MySurfaceViewActivity.class);
            startActivity(intent);
        }
    };

    private OnClickListener mMyListView3DBtnListener =new OnClickListener() {
        @Override
        public void onClick(View v) {
            SMLog.i();
            Intent  intent = new Intent();
            intent.setClass( MainActivity.this, MyListView3DActivity.class);
            startActivity(intent);
        }
    };

    private OnClickListener mMyJavaActivityBtnListener =new OnClickListener() {
        @Override
        public void onClick(View v) {
            SMLog.i();
            Intent  intent = new Intent();
            intent.setClass( MainActivity.this, MyJavaActivity.class);
            startActivity(intent);
        }
    };

    private OnClickListener mMyAnimationActivityBtnListener =new OnClickListener() {
        @Override
        public void onClick(View v) {
            SMLog.i();
            Intent  intent = new Intent();
            intent.setClass( MainActivity.this, AnimationActivity.class);
            startActivity(intent);
        }
    };


    private OnClickListener mHandlerThreadLooperBtnListener =new OnClickListener() {
        @Override
        public void onClick(View v) {
            SMLog.i();
            Intent  intent = new Intent();
            intent.setClass( MainActivity.this, HandlerThreadLooper.class);
            startActivity(intent);
        }
    };


    //Only For Service -------------------------------------------------------------------------
    // LocalService start/stop
    private OnClickListener mMyLocalServiceToggleBtnListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            SMLog.i();
            Intent intent = new Intent();
            intent.setClass( MainActivity.this, MyLocalService.class);
            if(mMyLocalServiceToggleBtn.isChecked()){
                startService(intent);
                Toast.makeText(MainActivity.this, "START service", Toast.LENGTH_SHORT).show();
                mMyOutputTextView.setText("START Service");
            }
            else{
                stopService(intent);
                Toast.makeText(MainActivity.this, "STOP service", Toast.LENGTH_SHORT).show();
                mMyOutputTextView.setText("STOP Service");
            }
        }
    };

    // LocalService bind/unbind
    MyLocalService mMyLocalService;
    boolean mBound = false;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className,IBinder service) {
            SMLog.i();
            MyLocalService.LocalBinder binder = (MyLocalService.LocalBinder) service;
            mMyLocalService = binder.getService();
            mBound = true;
        }
        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            SMLog.i();
            mBound = false;
        }
    };
    private OnClickListener mMyBindServiceToggleBtnListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            boolean ret = false;
            SMLog.i();
            if(mMyBindServiceToggleBtn.isChecked()){
                Intent intent = new Intent(MainActivity.this, MyLocalService.class);
                ret = bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
                Toast.makeText(MainActivity.this, "BIND service", Toast.LENGTH_SHORT).show();
                mMyOutputTextView.setText("bindService = "+ret);
            }
            else{
                unbindService(mConnection);
                mBound = false;
                Toast.makeText(MainActivity.this, "UNBIND service", Toast.LENGTH_SHORT).show();
                mMyOutputTextView.setText("unbindService");
            }
        }
    };
    private OnClickListener mMygetBindServiceResultBtnListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mBound) {
                // Call API at background
                int num = mMyLocalService.getRandomNumber();
                Toast.makeText(MainActivity.this, "number: " + num, Toast.LENGTH_SHORT).show();
                mMyOutputTextView.setText("number: "+ num);
            }
        }
    };


    private OnClickListener mMyIntentServiceToggleBtnListener = new OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(MainActivity.this, MyIntentService.class);
            SMLog.i();
            if(mMyIntentServiceToggleBtn.isChecked()){
                startService(intent);
                Toast.makeText(MainActivity.this, "new IntentService", Toast.LENGTH_SHORT).show();
                mMyOutputTextView.setText("START IntentService");
            }
            else{
                // stopService(intent); // Hawk: IntentService can't be stop, href="http://stackoverflow.com/questions/8709989/how-to-stop-intentservice-in-android"
                Toast.makeText(MainActivity.this, "Can't STOP IntentService", Toast.LENGTH_SHORT).show();
                mMyOutputTextView.setText("Can't STOP IntentService");
            }
        }
    };

    private OnClickListener mMyAsyncTaskToggleBtnListener = new OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(MainActivity.this, MyIntentService.class);
            SMLog.i();
            if(mMyAsyncTaskToggleBtn.isChecked()){
                // Hawk: Update UI in AsyncTask's override APIs.
                mMyTask= new  MyAsyncTask(); // AsyncTask can be executed only once, so new it everytime.
                mMyTask.execute( "Start MyAsyncTask job!" );
            }
            else{
                // Hawk: Update UI in AsyncTask's override APIs.
                if(mMyTask!=null)
                    mMyTask.cancel( true );
            }
        }
    };

    public void scheduleAlarm(boolean enable) {
        AlarmManager mAlarmMgr = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
/*
    // WAY1: BroadcastReceiver : The specific receiver can get an intent if Alarm arrived!
        Intent intent = new Intent(MainActivity.this, MyReceiver.class);
        intent.setAction("sample.hawk.com.mybasicappcomponents.alarmmanager");
        final PendingIntent alarmIntent = PendingIntent.getBroadcast(MainActivity.this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
*/

    // WAY2: Service : The specific service can get an intent if Alarm arrived!
        Intent intent = new Intent(MainActivity.this, MyNotificationService.class);
        final PendingIntent alarmIntent = PendingIntent.getService(MainActivity.this, MY_REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT );
/*
    // WAY3: Activity : The specific Activity can get an intent if Alarm arrived!
        Intent intent = new Intent(MainActivity.this, MyActivity.class);
        final PendingIntent alarmIntent = PendingIntent.getActivity(MainActivity.this, MY_REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT );
*/

        if (enable == true) {
            // Hawk: Min duration forced up to 60000 as of Android 5.1
            mAlarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 10*1000, 60*1000, alarmIntent);
            // mAlarmMgr.set(AlarmManager.RTC_WAKEUP, firstMillis + 10*1000, alarmIntent); // No repeat cast.
        } else {
            if (mAlarmMgr != null) {
                mAlarmMgr.cancel(alarmIntent);
            }
        }
    }
    private OnClickListener mMyAlarmManagerToggleBtnListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            SMLog.i();
            if(mMyAlarmManagerToggleBtn.isChecked()){
                scheduleAlarm(true);
            }
            else{
                scheduleAlarm(false);
            }
        }
    };
    private OnClickListener mMyJobSchedulerToggleBtnListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            SMLog.i();
            if(mMyJobSchedulerToggleBtn.isChecked())
            {
                mMyJobScheduler = (JobScheduler) getSystemService( Context.JOB_SCHEDULER_SERVICE );
                JobInfo.Builder builder = new JobInfo.Builder( 1,
                                                            new ComponentName( getPackageName(),
                                                            MyJobSchedulerService.class.getName() ) );
                builder.setPeriodic( 3000 ); // Run Job every 3sec.
                builder.setRequiresCharging(true);
                builder.setRequiresDeviceIdle(true);
                if( mMyJobScheduler.schedule( builder.build() ) <= 0 ) {
                    SMLog.e("[Hawk]","mMyJobScheduler is failed!!");
                }
            }
            else
            {
                mMyJobScheduler.cancelAll();
            }
        }
    };

    private OnClickListener mMyThreadToggleBtnListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            SMLog.i();
            if(mMyThreadToggleBtn.isChecked())
            {
                mMyThread1 = new MyThread(mContext);
                mMyThread1.start();
                // CAUTION! mMyThread1.mFromUI_handler may not ready immediately, don't call it right now!
            }
            else
            {
                if(mMyThread1!=null)
                    mMyThread1.stopThread();
            }
        }
    };

    private OnClickListener mSendMsg_fromUI_to_Thread_BtnListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Message FromUI_msg;
            if(mMyThread1.mFromUI_handler!=null)
            {
                // run JOB1 first
                FromUI_msg=mMyThread1.mFromUI_handler.obtainMessage();
                FromUI_msg.what=11111111;
                mMyThread1.mFromUI_handler.sendMessage(FromUI_msg);
                // if JOB1 not finish yet, msg will be QUEUE.
                FromUI_msg=mMyThread1.mFromUI_handler.obtainMessage();
                FromUI_msg.what=22222222;
                mMyThread1.mFromUI_handler.sendMessage(FromUI_msg);
            }
            else
            {
                SMLog.e(TAG,"Worker thread's handler is not ready!");
            }
        }
    };
    //Only For BroadcastReceiver -------------------------------------------------------------------------
    private OnClickListener mMyReceiverBtnListener = new OnClickListener(){
        @Override
        public void onClick(View v) {
            SMLog.i();
            // init Receive side.
            IntentFilter intentFilterForReceiver = new IntentFilter("sample.hawk.com.mybasicappcomponents.broadcast1");
            // <!-- Dynamically register MyReceiver  -->
            registerReceiver(mMyReceiver, intentFilterForReceiver);
        }
    };
    private OnClickListener mMyBroadcastBtnListener = new OnClickListener(){
        @Override
        public void onClick(View v) {
            SMLog.i();
            // send a broadcast only for this APP.
            Intent intent = new Intent();
            intent.setAction("sample.hawk.com.mybasicappcomponents.broadcast1");
            sendBroadcast(intent);
        }
    };

    //Only For ContentProvider -------------------------------------------------------------------------
    private OnClickListener mMyProviderBtnListener = new OnClickListener(){
        @Override
        public void onClick(View v) {
            SMLog.i();
            // Hawk: During launching APP, my APP's ContentProvider will be launched by OS.
            ContentValues values = new ContentValues();
            values.put(MyContentProvider.columnConst.NAME,"MyDefault");
            values.put(MyContentProvider.columnConst.BIRTHDAY,"11/11");
            Uri uri = getContentResolver().insert(
                    MyContentProvider.CONTENT_URI, values);
            Toast.makeText(getBaseContext(),
                    "MyContentProvider : " + uri.toString() + " inserted!", Toast.LENGTH_LONG).show();
        }
    };

    public void addDefaultBirthday (View view) {
            SMLog.i();
    }

    public void deleteAllBirthdays (View view) {  // Hawk: Already write android:onClick="showAllBirthdays" in AndroidManifest.xml .
        SMLog.i();
        // delete all the records and the table of the database provider
        String URL = "content://sample.hawk.com.mybasicappcomponents.provider.Birthday/friends";
        Uri friends = Uri.parse(URL);
        int count = getContentResolver().delete(friends, null, null);
        String countNum = "MyContentProvider Num: "+ count +" records are deleted.";
        Toast.makeText(getBaseContext(),countNum, Toast.LENGTH_LONG).show();

    }
    public void addBirthday(View view) {
        SMLog.i();
        // Add a new birthday record
        ContentValues values = new ContentValues();

        values.put(MyContentProvider.columnConst.NAME,
                ((EditText)findViewById(R.id.name)).getText().toString());

        values.put(MyContentProvider.columnConst.BIRTHDAY,
                ((EditText)findViewById(R.id.birthday)).getText().toString());

        Uri uri = getContentResolver().insert(
                MyContentProvider.CONTENT_URI, values);

        Toast.makeText(getBaseContext(),
                "MyContentProvider : " + uri.toString() + " inserted!", Toast.LENGTH_LONG).show();
    }


    public void showAllBirthdays(View view) {
        SMLog.i();
        // Show all the birthdays sorted by friend's name
        String URL = "content://sample.hawk.com.mybasicappcomponents.provider.Birthday/friends";
        Uri friends = Uri.parse(URL);
        Cursor c = getContentResolver().query(friends, null, null, null, "name");
        String result = "MyContentProvider Results:";

        if (!c.moveToFirst()) {
            Toast.makeText(this, result+" no content yet!", Toast.LENGTH_LONG).show();
        }else{
            do{
                result = result + "\n" +
                        "   ID: " + c.getString(c.getColumnIndex(MyContentProvider.columnConst.ID)) +
                        " NAME: " + c.getString(c.getColumnIndex(MyContentProvider.columnConst.NAME)) +
                        " BDAY: " + c.getString(c.getColumnIndex(MyContentProvider.columnConst.BIRTHDAY));
            } while (c.moveToNext());
            Toast.makeText(this, result, Toast.LENGTH_LONG).show();
        }

    }

    private Timer timer = null;
    public void main_update_PB_thread(){
        SMLog.i(TAG,"main_update_PB_thread  ThreadId="+Thread.currentThread().getId()); // UI thread, only Run first time.
        timer = new Timer();
        timer.schedule(new TimerTask() { // schedule(TimerTask task, long delay, long period)
            @Override
            public void run() { // Worker thread, Run every 1sec
                //SMLog.i(TAG,"main_update_PB_thread  run1() ThreadId="+Thread.currentThread().getId());

                // TODO: implement your job here, it will run every second.

                // UPDATE_UI WAY2: You call runOnUiThread in a thread under Activity class.
                runOnUiThread(new Runnable() {
                    public void run() // UI thread, Run every 1sec
                    {
                        //SMLog.i(TAG,"main_update_PB_thread  run2() ThreadId="+Thread.currentThread().getId());
                        if(pa>100)
                            pa=0;
                        mMainActivityProgressBar.setProgress(pa++);
                    }
                });
            }
        }, 1000,1000); // time param at here!
    }

    // AsyncTask can only start by UI thread. ------------------------------------------------------
    // UPDATE_UI WAY3: AsyncTask can update UI easily.
    //param1：向後台任務的執行方法傳遞參數的類型；
    //param2：在後台任務執行過程中，要求主UI thread處理中間狀態，通常是一些UI處理中傳遞的參數類型；
    //param3：return value
    private  MyAsyncTask mMyTask;
    private class MyAsyncTask extends AsyncTask<String/*param1*/,String/*param2*/,String/*param3*/>
    {
        // Update UI before job start.
        protected void onPreExecute() {
            SMLog.i();
            Toast.makeText(MainActivity.this, "AsyncTask start..." + this,Toast.LENGTH_SHORT).show();
        }
        @Override
        protected String/*param3*/ doInBackground(String... params/*param1*/) { // WorkerThread at here!
            SMLog.i();
            String pre = params[0]; // params[0]="Start MyAsyncTask job!"
            // TODO: WORKTHREAD at here for implement your job!
            for (int i = 0; i < 5; i++) {
                publishProgress(pre+i/*param2*/); // Hawk: related with onProgressUpdate()
                SystemClock.sleep(1000);
            }
            return "AsyncTask finish!";
        }
        // Update UI during Job is working.
        protected void onProgressUpdate(String... values/*param2*/ ) {
            SMLog.i();
            mMyOutputTextView.setText(values[0]);
            if(pa>100)
                pa=0;
            mMainActivityProgressBar.setProgress(pa++);
        }

        // Update UI after job done.
        @Override
        protected void onPostExecute(String result/*param3*/) {
            super.onPostExecute(result);
            SMLog.i();
            Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
            mMyOutputTextView.setText(result);
        }
        // Update UI after job cancelled.
        @Override
        protected void onCancelled() {
            SMLog.i();
            mMyOutputTextView.setText("AsyncTask cancelled");
        }
    }

    @Override
    public void MyInterfaceAPI(int i) {
        SMLog.i(TAG, "MyInterfaceAPI ="+i);
    }


}
