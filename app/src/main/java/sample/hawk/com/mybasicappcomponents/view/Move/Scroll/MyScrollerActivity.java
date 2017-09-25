package sample.hawk.com.mybasicappcomponents.view.Move.Scroll;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import sample.hawk.com.mybasicappcomponents.R;

/**
 * Created by ha271 on 2017/9/21.
 */

public class MyScrollerActivity extends Activity {
    Context mContext;
    LinearLayout mRootLayout;
    Intent  mIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;

        mIntent = new Intent(this, MyScrollerViewActivity.class);

        mRootLayout = new LinearLayout(mContext);
        mRootLayout.setOrientation(LinearLayout.VERTICAL);
        mRootLayout.setBackground(getDrawable(R.drawable.android_robot));

        Button scrollToBtn = new Button(mContext);
        scrollToBtn.setText("ScrollTo");
        mRootLayout.addView(scrollToBtn);
        scrollToBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRootLayout.scrollTo(-60, -100);
            }
        });

        Button scrollByBtn = new Button(mContext);
        scrollByBtn.setText("ScrollBy");
        mRootLayout.addView(scrollByBtn);
        scrollByBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRootLayout.scrollBy(-60, -100);
            }
        });

        Button scrollbarHorizontal = new Button(mContext);
        scrollbarHorizontal.setText("Scrollbar Horizontal");
        mRootLayout.addView(scrollbarHorizontal);
        scrollbarHorizontal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent.putExtra("ScrollDirection","Horizontal");
                startActivity(mIntent);
            }
        });

        Button scrollbarVertical = new Button(mContext);
        scrollbarVertical.setText("Scrollbar Vertical");
        mRootLayout.addView(scrollbarVertical);
        scrollbarVertical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIntent.putExtra("ScrollDirection","Vertical");
                startActivity(mIntent);
            }
        });

        setContentView(mRootLayout);
    }



}
