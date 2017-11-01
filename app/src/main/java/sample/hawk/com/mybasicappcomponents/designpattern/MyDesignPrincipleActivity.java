package sample.hawk.com.mybasicappcomponents.designpattern;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.designpattern._Principle.P1_SRP;
import sample.hawk.com.mybasicappcomponents.designpattern._Principle.P2_OCP;
import sample.hawk.com.mybasicappcomponents.designpattern._Principle.P3_LSP;
import sample.hawk.com.mybasicappcomponents.designpattern._Principle.P4_ISP;
import sample.hawk.com.mybasicappcomponents.designpattern._Principle.P5_DIP;
import sample.hawk.com.mybasicappcomponents.designpattern._Principle.P6_LoD;
import sample.hawk.com.mybasicappcomponents.designpattern._Principle.P7_CARP;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * 設計物件導向程式的準則:
 *   1.獨立變動
 *   2.對介面寫程式
 *   3.多用合成，少用繼承
 */

public class MyDesignPrincipleActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mydesignprinciple);

    }

    public void onClick_MyDesignPrincipleClass(View view){
        MyPrinciple(Integer.parseInt(view.getTag().toString()));
    }

    private void MyPrinciple(int principle_type){
        switch(principle_type){
            case 10:
                new P1_SRP().demo();
                break;
            case 20:
                new P2_OCP().demo();
                break;
            case 30:
                new P3_LSP().demo();
                break;
            case 40:
                new P4_ISP().demo();
                break;
            case 50:
                new P5_DIP().demo();
                break;
            case 60:
                new P6_LoD().demo();
                break;
            case 70:
                new P7_CARP().demo();
                break;
            default:
                SMLog.e("Not support this principle yet");
        }
    }



}
