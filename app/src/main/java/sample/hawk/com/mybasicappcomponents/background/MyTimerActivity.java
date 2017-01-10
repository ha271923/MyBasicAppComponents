package sample.hawk.com.mybasicappcomponents.background;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.WorkerThread;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

import static sample.hawk.com.mybasicappcomponents.utils.Util.isUiThread;

/**
 * 在主程式中，Timer會使用schedule方法來安排想要執行的工作和執行的時間區隔，而需要被執行的工作(類別)只需
 * 要去繼承TimerTask類別，同時覆寫run方法即可。
 * TimerTask是想要被執行的工作類別，繼承TimerTask類別，同時將想要被執行的工作寫在run方法中：
 */

public class MyTimerActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timer timer = new Timer();
        long delayTime = 1 * 1000;
        long period = 5 * 1000;
        timer.schedule(new TimeTask(), delayTime, period);
    }

    public class TimeTask extends TimerTask {

        @WorkerThread
        @Override
        public void run() {
            SMLog.i("isUiThread="+isUiThread()+"  Timer: job at " + new Date());
        }
    }
}
