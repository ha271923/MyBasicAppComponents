package sample.hawk.com.mybasicappcomponents.view;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by ha271 on 2016/11/10.
 */

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener {
    private SurfaceHolder holder;
    private MyDrawThread mMyDrawThread;
    public float mX, mY;

    public MySurfaceView(Context context)
    {
        super(context);
        holder = getHolder(); // 通過SurfaceView獲得SurfaceHolder對象
        holder.addCallback(this); // 為holder添加Callback
        // 創建一個繪製Thread，將holder對像作為參數傳入，這樣在繪製Thread中就可以獲得holder
        // 對象，進而在繪製Thread中可以通過holder對象獲得Canvas對象，並在Canvas上進行繪製
        mMyDrawThread = new MyDrawThread(holder, this);
    }

    /**
     * 實現SurfaceHolder.Callback接口中的三個方法，都是在UiThread中調用，而不是在繪製Thread中調用的
     */
    // 當這個方法被callback時，說明Surface已經有效了
    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        mMyDrawThread.setRun(true);
        mMyDrawThread.start();
    }

    // SurfaceView的大小發生改變或第一次創建時觸發
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {
    }

    // 結束Thread。當這個方法調用時，說明Surface即將要被銷毀了
    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        mMyDrawThread.setRun(false);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                // Getting the X,Y-Coordinate of the touched position
                mX = event.getX();
                mY = event.getY();
                mMyDrawThread.updateTouchPosition();
                break;
        }
        return true;
    }
}
