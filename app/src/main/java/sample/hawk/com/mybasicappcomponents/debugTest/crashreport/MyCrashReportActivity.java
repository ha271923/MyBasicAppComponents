package sample.hawk.com.mybasicappcomponents.debugTest.crashreport;

import android.os.Bundle;
import android.view.View;

import sample.hawk.com.mybasicappcomponents.R;

/**
 * Created by ha271 on 2017/2/17.
 */

public class MyCrashReportActivity extends ReportingActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crashreport_activity);
        findViewById(R.id.crash_app).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                throw new IllegalStateException("THIS IS A CRASH!");
            }
        });
    }
}