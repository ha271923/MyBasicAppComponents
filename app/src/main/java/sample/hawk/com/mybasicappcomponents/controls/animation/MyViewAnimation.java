package sample.hawk.com.mybasicappcomponents.controls.animation;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import sample.hawk.com.mybasicappcomponents.R;

/**
 * Created by ha271 on 2016/12/2.
 */

public class MyViewAnimation extends View {
    private ImageView[] m_imgv;
    Context m_context;
    public MyViewAnimation(Context context,AnimationActivity.MyViewAnimations anim){
        super(context);
        m_imgv = new ImageView[4];
        m_context = context;
        m_imgv = create_android_robots(context);
        int enum_to_int = anim.ordinal();
        switch(anim){
            case ALPHA:
                //alpha(m_imgv);
                alpha_by_xml(m_imgv[enum_to_int]);
                break;
            case SCALE:
                scale(m_imgv[enum_to_int]);
                break;
            case ROTATE:
                rotate(m_imgv[enum_to_int]);
                break;
            case TRANSLATE:
                translate(m_imgv[enum_to_int]);
                break;
        }
    }

    ImageView[] create_android_robots(Context context){
        m_imgv[0]=(ImageView) ((Activity)context).findViewById(R.id.android_robot0);
        m_imgv[1]=(ImageView) ((Activity)context).findViewById(R.id.android_robot1);
        m_imgv[2]=(ImageView) ((Activity)context).findViewById(R.id.android_robot2);
        m_imgv[3]=(ImageView) ((Activity)context).findViewById(R.id.android_robot3);
        return m_imgv;
    }

    // AlphaAnimation WAY1: control by code
    private void alpha(View view){
        // AlphaAnimation WAY1: control by code
        AlphaAnimation alpha_anim = new AlphaAnimation(1, ( float ) 0.1);
        alpha_anim.setDuration(3000); // 設置動畫持續時間為3 秒
        alpha_anim.setFillAfter( true ); // 設置動畫結束後保持當前的位置（即不返回到動畫開始前的位置）
        alpha_anim.setRepeatCount(10);
        view.startAnimation(alpha_anim);
    }
    // AlphaAnimation WAY2: control by xml
    private void alpha_by_xml(View view){
        Animation alpha_anim2= AnimationUtils.loadAnimation( m_context , R.anim.myalpha_anim); // 加載Xml 文件中的動畫
        view.startAnimation(alpha_anim2);
    }

    public void scale(View view){
        //這4個值,0表示縮小到沒有,1表示正常,比1大就是放大
        float fromX = 0.1f;    //動畫開始時x的縮放尺寸
        float toX = 2.0f;        //動畫結束時x的縮放尺寸
        float fromY = 0.1f;    //動畫開始時y的縮放尺寸
        float toY = 2.0f;        //動畫結束時y的縮放尺寸
        //設置錨點的四個參數
        int pivotXType = Animation.RELATIVE_TO_SELF;    //x相對於物件自己的
        float pivotXValue = 0.5f;    //取值範圍0~1之間,相對的x軸位置,相對於物件就是物件的寬度中間,相對於屏幕就是屏幕中間
        int pivotYType = Animation.RELATIVE_TO_SELF;    //y相對於物件自己的
        float pivotYValue = 0.5f;//取值範圍0~1之間,相對的y軸位置,相對於物件就是物件的寬度中間,相對於屏幕就是屏幕中間
        ScaleAnimation scale = new ScaleAnimation(fromX, toX, fromY, toY, pivotXType, pivotXValue, pivotYType, pivotYValue);
        scale.setDuration(3000);
        scale.setFillAfter(true);    //顯示動畫結束時View的樣子,上面是2.0f倍放大,那麼顯示就會是那麼大
        scale.setRepeatCount(10);
        view.startAnimation(scale);
    }

    public void rotate(View view){
        float fromDegrees = 0;        //動畫開始的角度
        float toDegrees = -360;        //動畫結束時旋轉的角度(可以大於360)正數順時針旋轉,負數逆時針旋轉
        //設置錨點的四個參數
        int pivotXType = Animation.RELATIVE_TO_SELF;    //x相對於物件自己的
        float pivotXValue = 0.5f;    //取值範圍0~1之間,相對的x軸位置,相對於物件就是物件的寬度中間,相對於屏幕就是屏幕中間
        int pivotYType = Animation.RELATIVE_TO_SELF;    //y相對於物件自己的
        float pivotYValue = 0.5f;//取值範圍0~1之間,相對的y軸位置,相對於物件就是物件的寬度中間,相對於屏幕就是屏幕中間
        RotateAnimation rotate = new RotateAnimation(fromDegrees, toDegrees, pivotXType, pivotXValue, pivotYType, pivotYValue);
        rotate.setDuration(3000);
        rotate.setRepeatCount(10);
        view.startAnimation(rotate);
    }

    public void translate(View view){
        int fromXType = Animation.RELATIVE_TO_SELF;    //x軸相對的物件
        float fromXValue = 0f;    //從相對於物件x軸x位置
        int toXType = Animation.RELATIVE_TO_PARENT; //x軸移動相對物
        float toXValue = -1f; //x移動
        int fromYType = Animation.RELATIVE_TO_SELF; //y軸相對的物件
        float fromYValue = 0f;    //從相對於物件y軸y位置
        int toYType    = Animation.RELATIVE_TO_PARENT;    //y軸移動相對物
        float toYValue = 0f;    //y移動
        TranslateAnimation translate = new TranslateAnimation(fromXType, fromXValue, toXType, toXValue, fromYType, fromYValue, toYType, toYValue);
        translate.setDuration(3000);
        translate.setRepeatCount(10);
        view.startAnimation(translate);
    }

}
