package sample.hawk.com.mybasicappcomponents.graphic;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import sample.hawk.com.mybasicappcomponents.graphic.utils.CanvasUtils;
import sample.hawk.com.mybasicappcomponents.utils.Util;

/**
 * Created by ha271 on 2017/9/18.
 */

public class MyCanvasActivity extends Activity implements Spinner.OnItemSelectedListener, Button.OnClickListener {
    private enum ViewType {
        drawArraw,
        drawCropRect,
        drawLayer,
        drawImage
    }
    private static ViewType mViewType = ViewType.drawArraw;

    private static final String[] mlistItems = Util.enumToString(ViewType.class);
    View mMyDrawCanvasView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout myRoot = new LinearLayout(this);
        myRoot.setOrientation(LinearLayout.VERTICAL);

        Spinner spinner = new Spinner(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MyCanvasActivity.this, android.R.layout.simple_spinner_item, mlistItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setMinimumHeight(200);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        myRoot.addView(spinner);

        Button drawButton = new Button(this);
        drawButton.setText("Draw It!");
        drawButton.setOnClickListener(this);
        myRoot.addView(drawButton);

        mMyDrawCanvasView = new MyDrawCanvasView(this);
        myRoot.addView(mMyDrawCanvasView);

        setContentView(myRoot);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                mViewType = ViewType.drawArraw;
                break;
            case 1:
                mViewType = ViewType.drawCropRect;
                break;
            case 2:
                mViewType = ViewType.drawLayer;
                break;
            case 3:
                mViewType = ViewType.drawImage;
                break;
        }
    }

    @Override
    public void onClick(View v) {
        mMyDrawCanvasView.invalidate();
    }

    private static class MyDrawCanvasView extends View {
        Context mContext;
        private MyDrawCanvasView(Context context, ViewType viewType) {
            super(context);
            setFocusable(true);
            mViewType = viewType;
            mContext = context;
        }

        private MyDrawCanvasView(Context context) {
            this(context, mViewType);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            switch(mViewType) {
                case drawArraw:
                    CanvasUtils.drawArraw(canvas);
                    break;
                case drawCropRect:
                    RectF rectF = new RectF();
                    rectF.top = 300; rectF.left = 200;
                    rectF.right = 900; rectF.bottom = 1500;
                    CanvasUtils.drawCropRect(canvas, rectF);
                    break;
                case drawLayer:
                    CanvasUtils.drawLayer(canvas);
                    break;
                case drawImage:
                    CanvasUtils.drawImage(mContext, canvas);
                    break;
            }
        }



    }

}
