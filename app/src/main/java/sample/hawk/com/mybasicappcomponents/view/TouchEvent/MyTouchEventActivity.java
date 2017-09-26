package sample.hawk.com.mybasicappcomponents.view.TouchEvent;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;
/**
 *
 * 	dispatchTouchEvent： 分發TouchEvent。
 *  onInterceptTouchEvent： 攔截TouchEvent。
 *  onTouchEvent： 處理TouchEvent。
 *
 *   一般情況下，我們不該在普通View內重寫dispatchTouchEvent方法，因為它並不執行分發邏輯。
 *   當Touch事件到達View時，我們該做的就是是否在onTouchEvent事件中處理它。
 *
 *   圖層: SmallView in MiddleView in BigView
 *     都不攔截情況下, event是從最下方Root圖層往上送, 即使你按下了某個child view, 事實上還是會從下往上送
 *     所以Touch on SmallView的圖形, event的傳遞路徑為
 *     BigView[disp->inter] ==> MiddleView[disp->inter] ==> SmallView[disp->inter]
 *     BigView[onTouch]     <== MiddleView[onTouch]     <== SmallView[onTouch]      <==
 *
 * */
public class MyTouchEventActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    Dialog Dialog;

    CheckBox bigDispatch, bigInter,bigOntouch;
    CheckBox middleDisatch, middleInter, middleOntouch;
    CheckBox smallDispatch, smallInter, smallOntouch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mytouchevent_activity);

        Dialog = new Dialog(this);
        resetTouchFlags();
        Dialog.setContentView(R.layout.mytouchsetting_dialog_layout);

        findViewById(R.id.touch_settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog.show();
            }
        });
        findViewById(R.id.listview_touch_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyTouchEventActivity.this , MyTouchEventListViewActivity.class);
                startActivity(intent);
            }
        });


        bigInter = (CheckBox) Dialog.findViewById(R.id.big_inter);
        bigDispatch = (CheckBox) Dialog.findViewById(R.id.big_dispatch);
        bigOntouch = (CheckBox) Dialog.findViewById(R.id.big_ontouch);

        middleInter = (CheckBox) Dialog.findViewById(R.id.middle_inter);
        middleOntouch = (CheckBox) Dialog.findViewById(R.id.middle_ontouch);
        middleDisatch = (CheckBox) Dialog.findViewById(R.id.middle_dispatch);


        smallInter = (CheckBox) Dialog.findViewById(R.id.small_inter);
        smallDispatch = (CheckBox) Dialog.findViewById(R.id.small_dispatch);
        smallOntouch = (CheckBox) Dialog.findViewById(R.id.small_ontouch);


        smallInter.setOnCheckedChangeListener(this);
        smallDispatch.setOnCheckedChangeListener(this);
        smallOntouch.setOnCheckedChangeListener(this);

        middleInter.setOnCheckedChangeListener(this);
        middleOntouch.setOnCheckedChangeListener(this);
        middleDisatch.setOnCheckedChangeListener(this);

        bigInter.setOnCheckedChangeListener(this);
        bigDispatch.setOnCheckedChangeListener(this);
        bigOntouch.setOnCheckedChangeListener(this);

    }

    private void resetTouchFlags(){
        TouchSettings.BIGINTERFLAG = false;
        TouchSettings.BIGDISPATCHFLAG = false;
        TouchSettings.BIGTOUFLAG = false;
        TouchSettings.MIDDLEINTERFALG = false;
        TouchSettings.MIDDLEDISPATCHFLAG = false;
        TouchSettings.MIDDLETOUFLAG = false;
        TouchSettings.SMALLINTERFLAG = false;
        TouchSettings.SMALLDISPATCHFLAG = false;
        TouchSettings.SMALLTOUFLAG = false;
    }

/**
 * 1. 在一個ViewGroup中，事件分為dispatchTouchEvent（事件的分發）onInterceptTouchEvent（事件的攔截）的onTouchEvent（事件的處理）。
 * 2. 在View中，事件分為dispatchTouchEvent（事件的分發）的onTouchEvent（事件的處理）。
 *
 * */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if(buttonView == bigInter){
            TouchSettings.BIGINTERFLAG = isChecked;
        }
        else if(buttonView == bigDispatch){
            TouchSettings.BIGDISPATCHFLAG= isChecked;
        }
        else if(buttonView == bigOntouch){
            TouchSettings.BIGTOUFLAG = isChecked;
        }
        else if(buttonView == middleInter){
            TouchSettings.MIDDLEINTERFALG= isChecked;
        }
        else if(buttonView == middleOntouch){
            TouchSettings.MIDDLETOUFLAG = isChecked;
        }
        else if(buttonView == middleDisatch){
            TouchSettings.MIDDLEDISPATCHFLAG =  isChecked;
        }
        else if(buttonView == smallInter){
            TouchSettings.SMALLINTERFLAG = isChecked;
        }
        else if(buttonView == smallOntouch){
            TouchSettings.SMALLTOUFLAG= isChecked;
        }
        else if(buttonView == smallDispatch){
            TouchSettings.SMALLDISPATCHFLAG = isChecked;
        }
        SMLog.i(      "big_iner="+ TouchSettings.BIGINTERFLAG+", big_dis="+ TouchSettings.BIGDISPATCHFLAG+", big_tou="+ TouchSettings.BIGTOUFLAG
                +" \n middle_iner="+ TouchSettings.MIDDLEINTERFALG+", middle_dis="+ TouchSettings.MIDDLEDISPATCHFLAG+", middle_ontou="+ TouchSettings.MIDDLETOUFLAG
                +" \n small_iner="+ TouchSettings.SMALLINTERFLAG+", small_dis="+ TouchSettings.SMALLDISPATCHFLAG+", small_ontou="+ TouchSettings.SMALLTOUFLAG);
    }
}
