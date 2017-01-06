package sample.hawk.com.mybasicappcomponents.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by ha271 on 2017/1/6.
 */

public class FirstActivity extends Activity {

    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;

        Button toNextPage = new Button(this);
        toNextPage.setText("toNextPage");
        setContentView(toNextPage);

        toNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //建立包裹
                Bundle argument = new Bundle();
                argument.putString("KeyNameOne","String Text"); //放入字串，key 取名 KeyNameOne
                argument.putInt("KeyNameTwo",99); //放入整數，key 取名 KeysNameTwo
                argument.putFloat("KeyNameThree",123.456F); //放入浮點數，key 取名 KeyNameTwo

                //建立 Intent 物件，意圖開啟其他 Activity。
                Intent goOtherActivity = new Intent(activity, SecondActivity.class);
                goOtherActivity.putExtras(argument); //將包裹放入 Intent 中。
                activity.startActivity(goOtherActivity);
            }
        });
    }
}
