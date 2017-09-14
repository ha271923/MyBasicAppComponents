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

public class MyJavaActivity extends Activity{

    static Context m_Context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myjavaactivity);
        m_Context = this;

    }

    /* Test init Java class sequence Result:
10-06 14:32:06.450 23637-23637/sample.hawk.com.mybasicappcomponents I/[Hawk]: MyJavaClass() constructor +++
10-06 14:32:09.903 23637-23637/sample.hawk.com.mybasicappcomponents I/[Hawk]: ParentClass call <cinit> for all static variables
10-06 14:32:09.904 23637-23637/sample.hawk.com.mybasicappcomponents I/[Hawk]: instance ParentClass class static
10-06 14:32:13.860 23637-23637/sample.hawk.com.mybasicappcomponents I/[Hawk]: ChildClass call <cinit> for all static variables
10-06 14:32:13.860 23637-23637/sample.hawk.com.mybasicappcomponents I/[Hawk]: instance ChildClass===== class static
10-06 14:32:13.861 23637-23637/sample.hawk.com.mybasicappcomponents I/[Hawk]: instance ParentClass class
10-06 14:32:13.861 23637-23637/sample.hawk.com.mybasicappcomponents I/[Hawk]: into ParentClass constructor
10-06 14:32:13.862 23637-23637/sample.hawk.com.mybasicappcomponents I/[Hawk]: instance ChildClass===== class
10-06 14:32:13.862 23637-23637/sample.hawk.com.mybasicappcomponents I/[Hawk]: into DynamicObject1 constructor
10-06 14:32:13.863 23637-23637/sample.hawk.com.mybasicappcomponents I/[Hawk]: instance ParentClass class
10-06 14:32:13.863 23637-23637/sample.hawk.com.mybasicappcomponents I/[Hawk]: into ParentClass constructor
10-06 14:32:13.864 23637-23637/sample.hawk.com.mybasicappcomponents I/[Hawk]: instance DynamicObject1 class
10-06 14:32:13.864 23637-23637/sample.hawk.com.mybasicappcomponents I/[Hawk]: into DynamicObject2 constructor
10-06 14:32:13.865 23637-23637/sample.hawk.com.mybasicappcomponents I/[Hawk]: MyJavaClass() constructor ---
10-06 14:32:13.867 23637-23637/sample.hawk.com.mybasicappcomponents I/[Hawk]: call MyJavaClass::method_1()  API

    */
    public void onClick_MyJavaClass(View view){
        SMLog.e("JAVA class: "+((Button)view).getText());
        String Tag = view.getTag().toString();
        int tag = Integer.parseInt(Tag);
        new MyJavaClass(m_Context, tag);
    }




}
