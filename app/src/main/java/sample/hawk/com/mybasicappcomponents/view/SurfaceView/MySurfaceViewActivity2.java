package sample.hawk.com.mybasicappcomponents.view.SurfaceView;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.graphic.utils.DrawableUtils;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/6/14.
 */

public class MySurfaceViewActivity2 extends Activity {

    MySurfaceView_PaintSurface mMyPaintSurface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mysurfaceview_activity2);
        mMyPaintSurface = (MySurfaceView_PaintSurface)findViewById(R.id.paint_surface);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void onClick_MySurfaceViewActivity(View v) {
        int resId = v.getId();
        SMLog.i("resId=" + resId);
        switch (resId) {
            case R.id.getBMP:
                Bitmap bitmapBMP = mMyPaintSurface.getSignatureBitmap();
                break;
            case R.id.getJPG:
                byte[] jpegbyte = mMyPaintSurface.getSignatureJPEG(30);
                break;
            case R.id.SetBitmap:
                Bitmap bitmap = DrawableUtils.drawableToBitmap(this.getResources().getDrawable(R.drawable.android_robot));
                mMyPaintSurface.setSignatureBitmap(bitmap);
                break;
            case R.id.CLEAR:
                mMyPaintSurface.clear();
                break;
        }
    }

}