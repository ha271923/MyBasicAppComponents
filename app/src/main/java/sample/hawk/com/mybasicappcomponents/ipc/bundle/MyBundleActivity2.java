package sample.hawk.com.mybasicappcomponents.ipc.bundle;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import sample.hawk.com.mybasicappcomponents.R;

public class MyBundleActivity2 extends Activity{
    String mFrom;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mybundle_activity2);

        mFrom = getIntent().getStringExtra("from");

        // KEY: receive & parser data in bundle
        //取的intent中的bundle物件
        Bundle bundle2 =this.getIntent().getExtras();
        String sex = bundle2.getString("sex");
        double height = bundle2.getDouble("height");

        // processing
        String weight = tempUtils.calcWeight(sex,height);

        // output
        TextView tv1 = (TextView)findViewById(R.id.TextView11);
        String sexText="";
        if(sex.equals("Man"))
            sexText="男性";
        else
            sexText="女性";

        tv1.setText("您是一位"+sexText+"\n您的身高是"+height+"cm\n您的標準體重是"+weight+"kg");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mFrom.equals("MyBundleActivity")) {
            // You also can return the result in OnClickListener() by a button
            // for startActivityForResult() API called in the previous Activity
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("MyBundleActivity_Result", "BBBBBBBBBBBBBBBBBBBBBBB");
            intent.putExtras(bundle);
            setResult(RESULT_OK, intent);
        }
    }

}