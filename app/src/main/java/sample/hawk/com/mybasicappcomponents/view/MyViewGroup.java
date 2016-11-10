package sample.hawk.com.mybasicappcomponents.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/11/9.
 */

public class MyViewGroup extends ViewGroup {

    private static final String TAG = "CustomImgContainer";

    public MyViewGroup(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }

    public MyViewGroup(Context context)
    {
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    /**
     * 计算所有ChildView的宽度和高度 然后根据ChildView的计算结果，设置自己的宽和高
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        /**
         * 获得此ViewGroup上级容器为其推荐的宽和高，以及计算模式
         */
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        SMLog.e(TAG, (heightMode == MeasureSpec.UNSPECIFIED) + "," + sizeHeight
                + "," + getLayoutParams().height);

        // 计算出所有的childView的宽和高
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        /**
         * 记录如果是wrap_content是设置的宽和高
         */
        int width = 0;
        int height = 0;

        int cCount = getChildCount();

        int cWidth = 0;
        int cHeight = 0;
        MarginLayoutParams cParams = null;

        int lHeight = 0; // 用于计算左边两个childView的高度
        int rHeight = 0; // 用于计算右边两个childView的高度，最终高度取二者之间大值
        int tWidth = 0; // 用于计算上边两个childView的宽度
        int bWidth = 0; // 用于计算下面两个childiew的宽度，最终宽度取二者之间大值

         // 根据childView计算的出的宽和高，以及设置的margin计算容器的宽和高，主要用于容器是warp_content时
        for (int i = 0; i < cCount; i++)
        {
            View childView = getChildAt(i);
            cWidth = childView.getMeasuredWidth();
            cHeight = childView.getMeasuredHeight();
            cParams = (MarginLayoutParams) childView.getLayoutParams();

            // 上面两个childView
            if (i == 0 || i == 1)
                tWidth += cWidth + cParams.leftMargin + cParams.rightMargin;

            if (i == 2 || i == 3)
                bWidth += cWidth + cParams.leftMargin + cParams.rightMargin;

            if (i == 0 || i == 2)
                lHeight += cHeight + cParams.topMargin + cParams.bottomMargin;

            if (i == 1 || i == 3)
                rHeight += cHeight + cParams.topMargin + cParams.bottomMargin;
        }

        width = Math.max(tWidth, bWidth);
        height = Math.max(lHeight, rHeight);

        // 如果是wrap_content设置为我们计算的值, 否则：直接设置为父容器计算的值
        setMeasuredDimension((widthMode == MeasureSpec.EXACTLY) ? sizeWidth
                : width, (heightMode == MeasureSpec.EXACTLY) ? sizeHeight
                : height);
    }

    // abstract method in myviewgroup
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
        int cCount = getChildCount();
        int cWidth = 0;
        int cHeight = 0;
        MarginLayoutParams cParams = null;

         // 遍历所有childView根据其宽和高，以及margin进行布局
        for (int i = 0; i < cCount; i++)
        {
            View childView = getChildAt(i);
            cWidth = childView.getMeasuredWidth();
            cHeight = childView.getMeasuredHeight();
            cParams = (MarginLayoutParams) childView.getLayoutParams();

            int cl = 0, ct = 0, cr = 0, cb = 0;

            switch (i)
            {
                case 0:
                    cl = cParams.leftMargin;
                    ct = cParams.topMargin;
                    break;
                case 1:
                    cl = getWidth() - cWidth - cParams.leftMargin
                            - cParams.rightMargin;
                    ct = cParams.topMargin;

                    break;
                case 2:
                    cl = cParams.leftMargin;
                    ct = getHeight() - cHeight - cParams.bottomMargin;
                    break;
                case 3:
                    cl = getWidth() - cWidth - cParams.leftMargin
                            - cParams.rightMargin;
                    ct = getHeight() - cHeight - cParams.bottomMargin;
                    break;

            }
            cr = cl + cWidth;
            cb = cHeight + ct;
            childView.layout(cl, ct, cr, cb);
        }

    }

    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs)
    {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        SMLog.e(TAG, "generateDefaultLayoutParams");
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(
            ViewGroup.LayoutParams p)
    {
        SMLog.e(TAG, "generateLayoutParams p");
        return new MarginLayoutParams(p);
    }
}
