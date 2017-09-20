package sample.hawk.com.mybasicappcomponents.graphic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.io.FileNotFoundException;

import sample.hawk.com.mybasicappcomponents.graphic.utils.DrawableCropped;
import sample.hawk.com.mybasicappcomponents.graphic.utils.DrawableUtils;
import sample.hawk.com.mybasicappcomponents.graphic.utils.DrawableUtils2;
import sample.hawk.com.mybasicappcomponents.graphic.utils.ImageUtils;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;
import sample.hawk.com.mybasicappcomponents.utils.Util;


// public class MyDrawableActivity extends Activity implements View.OnTouchListener, View.OnDragListener {
public class MyDrawableActivity2 extends Activity implements Spinner.OnItemSelectedListener {
    Context mContext;
    static DisplayMetrics mDisplayMetrics;
    ImageView mImageView_org;
    Drawable mDrawable_org;
    Uri source;
    Bitmap mBitmap_org;
    Drawable drawable_out = null;
    final int GET_IMAGE = 1;

    private enum EnumItems {
        Item0,
        BitmapDrawable_wallpaper,
        BitmapDrawable_statusbar,
        scaleDrawable,
        changeDrawableColor,
        DrawableCropped,
        cropDrawable
    }
    private static EnumItems mEnumItem = EnumItems.Item0;

    private static final String[] mlistItems = Util.enumToString(MyDrawableActivity2.EnumItems.class);

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        mEnumItem = EnumItems.values()[position]; // intToEnum
        ItemActions(mEnumItem);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout myRoot = new LinearLayout(this);
        myRoot.setOrientation(LinearLayout.VERTICAL);

        mContext = this;
        mDisplayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        int status_bar_height = ImageUtils.getStatusBarHeight(this);
        SMLog.i("StatusBarHeight="+ status_bar_height );

        final int spinner_height = 200;
        Spinner spinner = new Spinner(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MyDrawableActivity2.this, android.R.layout.simple_spinner_item, mlistItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setMinimumHeight(spinner_height);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        myRoot.addView(spinner);

        mImageView_org = new ImageView(this);
        mImageView_org.setBackgroundColor(Color.RED);
        mImageView_org.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        int imgViewHeight = mDisplayMetrics.heightPixels - status_bar_height - spinner_height;
        SMLog.i("imgViewHeight="+ imgViewHeight );
        mImageView_org.setMinimumHeight(imgViewHeight);
        myRoot.addView(mImageView_org);

        setContentView(myRoot);

        ImageUtils.getImageFromAlbum(this, GET_IMAGE);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case GET_IMAGE:
                    source = data.getData();
                    try {
                        mBitmap_org = BitmapFactory
                                .decodeStream(getContentResolver().openInputStream( source));
                        mDrawable_org= new BitmapDrawable(mContext.getResources(), mBitmap_org);

                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    private void ItemActions(EnumItems item){
        long start_time = SystemClock.uptimeMillis();
        int nStatusBarHeight = 100;

        switch(item) {
            case Item0:
                break;
            case BitmapDrawable_wallpaper:
                DrawableUtils2.setContext(mContext);
                Bitmap bitmap2 = DrawableUtils2.getBackgroundBitmap(null);
                Bitmap mBackgroundBitmap = Bitmap.createBitmap(bitmap2, 0, nStatusBarHeight, bitmap2.getWidth(), bitmap2.getHeight() - nStatusBarHeight);
                drawable_out = new BitmapDrawable(getResources(), mBackgroundBitmap);
                break;
            case BitmapDrawable_statusbar:
                DrawableUtils2.setContext(mContext);
                Bitmap bitmap1 = DrawableUtils2.getBackgroundBitmap(null);
                Bitmap mStatusBarBackgroundBitmap = Bitmap.createBitmap(bitmap1, 0, 0, bitmap1.getWidth(), nStatusBarHeight);
                drawable_out = new BitmapDrawable(getResources(), mStatusBarBackgroundBitmap);
                break;
            case scaleDrawable:
                DrawableUtils.scaleDrawable(mDrawable_org, mImageView_org.getWidth()/2, mImageView_org.getHeight()/2); // setBounds
                drawable_out = mDrawable_org;
                break;

            case changeDrawableColor:
                drawable_out = DrawableUtils.changeDrawableColor(mDrawable_org, 0x7657321);
                break;

            case DrawableCropped:
                DrawableCropped drawableCropped = new DrawableCropped(mBitmap_org);
                drawable_out = drawableCropped;
                break;

            case cropDrawable:
                // drawable_out = DrawableUtils.cropDrawable(mBitmap_org, 300, 300);
                drawable_out = DrawableUtils.cropDrawable(mDrawable_org, 300, 300);
                break;
        }
        long end_time = SystemClock.uptimeMillis();
        SMLog.i("Effect     TimeCost = " + (end_time - start_time));
        // mImageView_org.setImageDrawable(drawable_out);
        mImageView_org.setBackground(drawable_out);
    }



}
