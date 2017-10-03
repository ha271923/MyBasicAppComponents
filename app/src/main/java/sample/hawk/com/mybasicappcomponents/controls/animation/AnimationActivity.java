package sample.hawk.com.mybasicappcomponents.controls.animation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import sample.hawk.com.mybasicappcomponents.R;

/**
 * Created by ha271 on 2016/10/27.
 */

public class AnimationActivity extends Activity {

    public enum MyViewAnimations{
        ALPHA, SCALE, ROTATE, TRANSLATE
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myanimation_activity);
    }

    public void onClick_CurrentActivity(View view){
        String Tag = view.getTag().toString();

        if(Tag.equals("sample.hawk.com.mybasicappcomponents.controls.animation.ColorAnimView")){
            RelativeLayout container = (RelativeLayout) findViewById(R.id.mainLayout);
            container.addView(new ColorAnimView(this));
        }

        if(Tag.equals("sample.hawk.com.mybasicappcomponents.controls.animation.MyViewAnimation")){
            RelativeLayout container = (RelativeLayout) findViewById(R.id.mainLayout);
            container.addView(new MyViewAnimation(this,MyViewAnimations.ALPHA));
            container.addView(new MyViewAnimation(this,MyViewAnimations.SCALE));
            container.addView(new MyViewAnimation(this,MyViewAnimations.ROTATE));
            container.addView(new MyViewAnimation(this,MyViewAnimations.TRANSLATE)); // Hawk: ONLY this animation will be executed whatever new many animations.
        }

    }

    public void onClick_NewActivity(View view){
        String Tag = view.getTag().toString();
        Intent intent = new Intent();
        Class<?> cls;
        try {
            cls = Class.forName(Tag);
            intent.setClass( AnimationActivity.this, cls );
            Bundle bundle=new Bundle();
            bundle.putString("FromActivity", Tag);
            intent.putExtras(bundle);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        startActivity(intent);
    }

    public void onClick_Play(View view){

    }

}
