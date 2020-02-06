package sample.hawk.com.mybasicappcomponents.ipc.bundle;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import sample.hawk.com.mybasicappcomponents.R;

public class MyBundleActivity2 extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mybundle_activity2);

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



}