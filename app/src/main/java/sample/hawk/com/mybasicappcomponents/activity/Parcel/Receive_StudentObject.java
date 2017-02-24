package sample.hawk.com.mybasicappcomponents.activity.Parcel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import sample.hawk.com.mybasicappcomponents.R;

/**
 * Created by ha271 on 2017/2/23.
 */

public class Receive_StudentObject extends Activity {
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receive_studentobject_activity);
        Intent i = getIntent();
        Student s = i.getParcelableExtra("key");
        ((TextView)findViewById(R.id.Name)).setText(s.name);
        // Java int to String(整數轉字串) 3 招!!
        ((TextView)findViewById(R.id.Number)).setText(Integer.toString(s.number));
        ((TextView)findViewById(R.id.Sex)).setText(String.valueOf(s.sex));
        ((TextView)findViewById(R.id.Age)).setText(new String(""+s.age));
    }

}