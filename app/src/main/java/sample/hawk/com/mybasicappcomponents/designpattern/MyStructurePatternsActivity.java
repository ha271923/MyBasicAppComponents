package sample.hawk.com.mybasicappcomponents.designpattern;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.designpattern.adapter.ClassAdapter.ClassAdapter_Pattern;
import sample.hawk.com.mybasicappcomponents.designpattern.adapter.Class_VS_Object.CmpObjClassAdapter_Pattern;
import sample.hawk.com.mybasicappcomponents.designpattern.adapter.ObjectAdapter.ObjectAdapter_Pattern;
import sample.hawk.com.mybasicappcomponents.designpattern.bridge.Bridge_Pattern;
import sample.hawk.com.mybasicappcomponents.designpattern.facade.Facade_Pattern;
import sample.hawk.com.mybasicappcomponents.designpattern.flyweight.Flyweight_Pattern;
import sample.hawk.com.mybasicappcomponents.designpattern.proxy.Proxy_Pattern;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Builder Pattern與 Abstract Factory Pattern 的不同：
 *  1) Builder著重在隱藏複雜的建置步驟，最後只傳回一個產品。
 *  2) Abstract Factory則是為了維護一系列產品的關聯，會產出某系列的多項產品。
 *  3) Builder模式中，Client不需要認識各個零件的型態。（只要『吃』產出的餐點）
 *  4) Abstract Factory中，Client認識各項的抽象型別或介面，並能使用它們。
 */

public class MyStructurePatternsActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mydesignpatterns_structure);

    }

    public void onClick_MyStructurePatternsClass(View view){
        MyDesignPattern(Integer.parseInt(view.getTag().toString()));
    }

    private void MyDesignPattern(int pattern_type){
        switch(pattern_type){
            case 10: // Object Adapter
                new ObjectAdapter_Pattern().demo();
                break;
            case 11: // Class Adapter
                new ClassAdapter_Pattern().demo();
                break;
            case 12:
                new CmpObjClassAdapter_Pattern().demo();
                break;
            case 20: // Bridge
                new Bridge_Pattern().demo();
                break;
            case 30: // Composite

                break;
            case 40: // Decorator

                break;
            case 50: // Facade
                new Facade_Pattern().demo();
                break;
            case 60: // Flyweight
                new Flyweight_Pattern().demo();
                break;
            case 70: // Proxy
                new Proxy_Pattern().demo();
                break;
            default:
                SMLog.e("Not support this pattern yet");
        }
    }

}
