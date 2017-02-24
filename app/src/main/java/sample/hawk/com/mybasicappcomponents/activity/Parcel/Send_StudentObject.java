package sample.hawk.com.mybasicappcomponents.activity.Parcel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import sample.hawk.com.mybasicappcomponents.R;

/**
 * Created by ha271 on 2017/2/23.
 */

public class Send_StudentObject extends Activity {

    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.send_studentobject_activity);
    }

    public void onClick_SendParcelData(View v){
        Student s = new Student();
        s.number = 99;
        s.name = "hawk_wei";
        s.sex = 1;
        s.age= 23;
        Intent i = new Intent();
        i.putExtra("key", s);
        i.setClass(this, Receive_StudentObject.class);
        startActivity(i);
    }

}
