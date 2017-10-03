package sample.hawk.com.mybasicappcomponents.controls.animation;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.WorkerThread;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

import static sample.hawk.com.mybasicappcomponents.utils.Util.isUiThread;

/**
 * Created by ha271 on 2017/1/10.
 */

public class MyViewGroupAnimActivity extends Activity{
    LinearLayout m_rootLayout;
    LinearLayout m_layer2Layout;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.myviewgroupanimactivity);
        m_rootLayout = (LinearLayout) findViewById(R.id.rootlayout);
        m_layer2Layout = (LinearLayout) findViewById(R.id.Layer2layout);
        getLayoutInflater().inflate(R.layout.specific_feedgridview_mopub_native_ads_two_column, m_layer2Layout);
        scheduleAnimation();
    }


    protected void scheduleAnimation() {
        Timer timer = new Timer();
        long delayTime = 1 * 1000;
        long period = 5 * 1000;
        timer.schedule(new TimeTask(), delayTime, period);
    }

    public class TimeTask extends TimerTask {

        @WorkerThread
        @Override
        public void run() { // For Timer
            SMLog.i("isUiThread="+isUiThread()+"  Timer: job at " + new Date());

            runOnUiThread(new Runnable() {
                @Override
                public void run() { // For BackgroundTask
                    Animation LR_slide_anim = AnimationUtils.loadAnimation(mContext, R.anim.l_to_r_anim);
                    m_layer2Layout.startAnimation(LR_slide_anim);
                }
            });
        }
    }

}
