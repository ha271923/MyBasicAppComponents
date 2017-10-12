package sample.hawk.com.mybasicappcomponents.designpattern;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.designpattern.Recursive.RecursiveAPIs;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Builder Pattern與 Abstract Factory Pattern 的不同：
 *  1) Builder著重在隱藏複雜的建置步驟，最後只傳回一個產品。
 *  2) Abstract Factory則是為了維護一系列產品的關聯，會產出某系列的多項產品。
 *  3) Builder模式中，Client不需要認識各個零件的型態。（只要『吃』產出的餐點）
 *  4) Abstract Factory中，Client認識各項的抽象型別或介面，並能使用它們。
 */

public class MyRecursiveActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myrecursive_activity);

    }

    public void onClick_MyRecursiveClass(View view){
        MyRecursivePattern(Integer.parseInt(view.getTag().toString()));
    }

    private void MyRecursivePattern(int pattern_type){
        switch(pattern_type){
            case 10: // 9x9 table
                // static recursive
                SMLog.i("static recursive ----");
                RecursiveAPIs.API_table_9x9(9);
                // Object recursive
                RecursiveAPIs recursiveAPIobj= new RecursiveAPIs();
                // One instance
                SMLog.i("One instance recursive ----");
                recursiveAPIobj.OBJ_table_9x9(recursiveAPIobj);
                // Multi instance
                SMLog.i("Multi instance recursive ----");
                recursiveAPIobj.OBJ_table_x9(recursiveAPIobj, 9);

                break;
            default:
                SMLog.e("Not support this pattern yet");
        }
    }

}
