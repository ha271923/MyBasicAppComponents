package sample.hawk.com.mybasicappcomponents.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import sample.hawk.com.mybasicappcomponents.utils.ImageUtils;

import static sample.hawk.com.mybasicappcomponents.MyApplication.mApplication;

public class MyView2 extends View {

    MyPolygonShape myShape;
    float ratioRadius;
    int numberOfPoint = 3;	//default

    //corresponding to UI element
    TextView textLayerInfo;

    public MyView2(Context context) {
        super(context);
        initMyView();
    }

    public MyView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        initMyView();
    }

    public MyView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initMyView();
    }

    public void initMyView(){
        myShape = new MyPolygonShape();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        long starting = System.nanoTime();

        int w = getWidth();
        int h = getHeight();

        if((w==0) || (h==0)){
            return;
        }

        float x = (float)w/2.0f;
        float y = (float)h/2.0f;
        float radius;
        if(w > h){
            radius = h * ratioRadius;
        }else{
            radius = w * ratioRadius;
        }

        myShape.setPolygon(x, y, radius, numberOfPoint);
        canvas.drawPath(myShape.getPath(), myShape.getPaint());

        long end = System.nanoTime();

        String info =
                "App.isHardwareAccelerated() = " + ImageUtils.isHardwareAccelerated(mApplication) + "\n"
                + "Window.isHardwareAccelerated() = " + ImageUtils.isHardwareAccelerated(GraphicAcceleratorActivity2.mWind) + "\n"
                + "myView2.isHardwareAccelerated() = " + this.isHardwareAccelerated() + "\n"
                + "canvas.isHardwareAccelerated() = " + canvas.isHardwareAccelerated() + "\n"
                + "processing time (reference only) : " + String.valueOf(end - starting) + " (ns)";
        textLayerInfo.setText(info);

    }

    public void setShapeRadiusRatio(float ratio){
        ratioRadius = ratio;
    }

    public void setNumberOfPoint(int pt){
        numberOfPoint = pt;
    }

    public void passElements(TextView textLayerInfo){
        this.textLayerInfo = textLayerInfo;
    }

}