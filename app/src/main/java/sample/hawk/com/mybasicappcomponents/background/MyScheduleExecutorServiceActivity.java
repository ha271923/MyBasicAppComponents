package sample.hawk.com.mybasicappcomponents.background;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.WorkerThread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

import static sample.hawk.com.mybasicappcomponents.utils.Util.isUiThread;

/**
 * Timer方法的一個缺點就是它是以Single Thread的方式來處理任務，如果你需要數個執行緒來同時執行不同任務時，Timer就不是這麼適合，
 * 這時候Java提供另外一種類別 - ScheduledExecutorService。
 */

public class MyScheduleExecutorServiceActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            ScheduledExecutorService service = Executors.newScheduledThreadPool(2);
            service.scheduleAtFixedRate(new MyJob("ScheduleExecutorService:: job1"), 1, 10, TimeUnit.SECONDS);
            service.scheduleAtFixedRate(new MyJob("ScheduleExecutorService:: job2"), 1, 10, TimeUnit.SECONDS);
    }

    public class MyJob implements Runnable{

        private String jobName = "";

        MyJob(String name) {
            this.jobName = name;
        }

        @WorkerThread
        @Override
        public void run() {
            SMLog.i("isUiThread="+isUiThread()+"  MyScheduleExecutorServiceActivity: "+jobName);
        }
    }
}
