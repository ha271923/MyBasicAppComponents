package sample.hawk.com.mybasicappcomponents.designpattern;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.designpattern.callback.MyCallBack;
import sample.hawk.com.mybasicappcomponents.designpattern.callback.SupportCallBack;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/10/6.
 */

public class MyDesignPatternActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mydesignpattern);

    }

    public void onClick_MyDesignPatternClass(View view){
        MyDesignPattern(Integer.parseInt(view.getTag().toString()));
    }

    public void MyDesignPattern(int pattern_type){
        switch(pattern_type){
            case 0:
                SupportCallBack scb = new SupportCallBack();
                scb.register(new MyCallBack() {
                    @Override
                    public void onCall_API() {
                        SMLog.i("***  api()'s callback was defined by caller. ***");
                    }
                });
                scb.api(); // Call scb's call_api() will callback onCall_API.
                break;

            default:

        }
    }

}
