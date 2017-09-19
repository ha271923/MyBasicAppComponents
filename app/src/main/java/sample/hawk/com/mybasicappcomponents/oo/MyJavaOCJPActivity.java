package sample.hawk.com.mybasicappcomponents.oo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/10/6.
 */

public class MyJavaOCJPActivity extends Activity{

    static Context m_Context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myjavaactivity);
        m_Context = this;

    }

    public void onClick_MyJavaOCJPClass(View view){
        SMLog.e("JAVA class: "+((Button)view).getText());
        String Tag = view.getTag().toString();
        int tag = Integer.parseInt(Tag);
        new MyJavaOCJPClass(m_Context, tag);
    }




}
