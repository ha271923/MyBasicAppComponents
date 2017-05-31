package sample.hawk.com.mybasicappcomponents.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/1/6.
 */

public class MyActivity1 extends Activity {
    private static final int GET_RESULT=1111111;

    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        LinearLayout myRoot = new LinearLayout(this);
        myRoot.setOrientation(LinearLayout.VERTICAL);

        Button toNextPage = new Button(this);
        toNextPage.setText("toNextPage");
        myRoot.addView(toNextPage);

        Button toNextPageWithResult = new Button(this);
        toNextPageWithResult.setText("toNextPageWithResult");
        myRoot.addView(toNextPageWithResult);

        setContentView(myRoot);

        toNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //建立包裹
                Bundle argument = new Bundle();
                argument.putString("KeyNameOne","String Text"); //放入字串，key 取名 KeyNameOne
                argument.putInt("KeyNameTwo",99); //放入整數，key 取名 KeysNameTwo
                argument.putFloat("KeyNameThree",123.456F); //放入浮點數，key 取名 KeyNameTwo

                //建立 Intent 物件，意圖開啟其他 Activity。
                Intent goOtherActivity = new Intent(activity, MyActivity2.class);
                goOtherActivity.putExtras(argument); //將包裹放入 Intent 中。

                // Pass data by Extra
                goOtherActivity.putExtra("ExtraName1", "Extra Text");
                goOtherActivity.putExtra("ExtraName2", 77);
                goOtherActivity.putExtra("ExtraName3", 321.654F);

                activity.startActivity(goOtherActivity);
            }
        });
        toNextPageWithResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //建立包裹
                Bundle argument = new Bundle();
                argument.putString("KeyNameOne","String Text"); //放入字串，key 取名 KeyNameOne
                argument.putInt("KeyNameTwo",99); //放入整數，key 取名 KeysNameTwo
                argument.putFloat("KeyNameThree",123.456F); //放入浮點數，key 取名 KeyNameTwo

                //建立 Intent 物件，意圖開啟其他 Activity。
                Intent goOtherActivity = new Intent(activity, MyActivity2.class);
                goOtherActivity.putExtras(argument); //將包裹放入 Intent 中。

                // Pass data by Extra
                goOtherActivity.putExtra("ExtraName1", "Extra Text");
                goOtherActivity.putExtra("ExtraName2", 77);
                goOtherActivity.putExtra("ExtraName3", 321.654F);

                activity.startActivityForResult(goOtherActivity,GET_RESULT);

                SMLog.i("BACK from the started Activity");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode){
            case GET_RESULT:
                Toast.makeText(this, data.getExtras().getString("SecondActivity_Result"), Toast.LENGTH_SHORT).show();
                SMLog.i("SecondActivity_Result = "+data.getExtras().getString("SecondActivity_Result"));
                break;
        }
    }
}
