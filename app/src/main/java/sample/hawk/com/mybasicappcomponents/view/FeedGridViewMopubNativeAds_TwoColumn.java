package sample.hawk.com.mybasicappcomponents.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/12/29.
 */

public class FeedGridViewMopubNativeAds_TwoColumn extends LinearLayout implements OnTouchFeedbackListener {
    private int s_nPadding;
    private Context m_Context;
    private ViewGroup m_Root;
    private ImageView m_MainImageView;
    private LinearLayout m_TextSection;
    private int m_nWidth;
    // private TouchFeedbackHelper mTouchFeedback;
    private Paint m_Paint;

    private TextView m_NativeTitle;
    private TextView m_NativeRating;
    private TextView m_NativeSocialContext;
    private TextView m_NativeSponsor;

    public FeedGridViewMopubNativeAds_TwoColumn(Context context){
        this(context, null);
        SMLog.i("FeedGridViewMopubNativeAds_TwoColumn(context)");
    }

    public FeedGridViewMopubNativeAds_TwoColumn(Context context, AttributeSet atts){ // If support XML, this is constructor.
        this(context, atts, 0);
        SMLog.i("FeedGridViewMopubNativeAds_TwoColumn(context, atts)");
    }

    public FeedGridViewMopubNativeAds_TwoColumn(Context context, AttributeSet attrs, int nDefStyle) {
        super(context, attrs, nDefStyle);
        SMLog.i("FeedGridViewMopubNativeAds_TwoColumn(context, atts, nDefStyle)");
        m_Context = context;

    }

    //////////////////////////// Custom Interface ////////////////////////////////
    @Override
    public void onTouchDownFeedback(){
        SMLog.i("onTouchDownFeedback");
        invalidate();
    }

    @Override
    public void onTouchUpFeedback(){
        SMLog.i("onTouchUpFeedback");
        invalidate();
    }

    ////////////////////////////  View  ////////////////////////////////

    @Override
    public void setOnTouchListener(OnTouchListener l) {
        super.setOnTouchListener(l);
    }

    ////////////////////////////  Layout  ////////////////////////////////

    @Override
    protected void onFinishInflate() {
        SMLog.i("onFinishInflate");
        CardView nativeCardView = (CardView) findViewById(R.id.native_card_view);
        m_Root = nativeCardView;
        nativeCardView.setCardBackgroundColor(getResources().getColor(R.color.gray));

        m_MainImageView = (ImageView) findViewById(R.id.native_main_image);
        m_MainImageView.setImageDrawable(getResources().getDrawable(R.drawable.android_robot));
        m_MainImageView.setBackgroundColor(getResources().getColor(R.color.blue));
        int nImageHeight =600;
        ViewGroup.LayoutParams imageParams = m_MainImageView.getLayoutParams();
        imageParams.height = nImageHeight;

        m_TextSection = (LinearLayout) findViewById(R.id.feed_ads_section_content_text);
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) m_TextSection.getLayoutParams();
        params.topMargin = nImageHeight;

        TextView nativeText = (TextView) findViewById(R.id.native_text);
        nativeText.setText("R.id.native_text");

        TextView callToAction = (TextView) findViewById(R.id.native_call_to_action_text);
        callToAction.setText("R.id.callToAction");

        m_NativeSponsor = (TextView) findViewById(R.id.feed_ads_sponsored_text);
        m_NativeSponsor.setText("R.string.sponsored");

        m_NativeTitle = (TextView) findViewById(R.id.native_title);
        m_NativeTitle.setText("R.string.native_title");

        m_NativeRating = (TextView) findViewById(R.id.native_rating);
        m_NativeRating.setText("R.string.native_rating");

        m_NativeSocialContext = (TextView) findViewById(R.id.native_social_context);

        ViewGroup adsTitleSection = (ViewGroup) findViewById(R.id.feed_ads_title);

        Drawable sponserSourceDrawable = getResources().getDrawable(R.drawable.prism_sponsor_source);
        m_NativeSponsor.setCompoundDrawablesWithIntrinsicBounds(sponserSourceDrawable, null, null, null);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_MOVE:
                SMLog.i("ACTION_MOVE");
                break;
            case MotionEvent.ACTION_DOWN:
                SMLog.i("ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                SMLog.i("ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                SMLog.i("ACTION_CANCEL");
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        SMLog.i("dispatchDraw");
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        SMLog.i("onMeasure");
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        SMLog.i("onLayout");
    }

    @Override
    public boolean onHoverEvent(MotionEvent event) {
        SMLog.i("onHoverEvent");
        return super.onInterceptHoverEvent(event);
    }

}
