package sample.hawk.com.mybasicappcomponents.view.SurfaceView;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

import static sample.hawk.com.mybasicappcomponents.utils.Util.isUiThread;

/**
 * Created by ha271 on 2016/11/10.
 */

// 繪製Thread
public class MySurfaceViewDrawThread extends Thread
{
    private SurfaceHolder holder;
    private MySurfaceView surfaceView;
    private boolean run;
    public int mX = 100, mY =100;

    public MySurfaceViewDrawThread(SurfaceHolder holder, MySurfaceView surfaceView)
    {
        this.holder = holder;
        this.surfaceView = surfaceView;
        run = true;
    }

    public void updateTouchPosition(){
        // force type convert will lose the data accuracy.
        mX = (int)this.surfaceView.mX;
        mY = (int)this.surfaceView.mY;
    }

    @Override
    public void run() // draw in non-UiThread
    {
        SMLog.i("tId="+Thread.currentThread()  + "  UiThread="+isUiThread());

        int counter = 0;
        Canvas canvas = null;
        while(run) // 具體繪製工作
        {
            try
            {
                canvas= holder.lockCanvas(); // 獲取Canvas對象，並鎖定之
                canvas.drawColor(Color.YELLOW); // 設定Canvas對象的背景顏色
                Paint p = new Paint(); // 創建畫筆
                int color = Color.RED|(counter*100);
                p.setColor(color); // 設置畫筆顏色, 漸變色彩
                p.setTextSize(60); // 設置文字大小
                Rect rect = new Rect(mX,mY,mX+380,mY+330); // 創建一個Rect對象rect
                canvas.drawRect(rect,p); // 在canvas上繪製rect
                // 在canvas上顯示時間
                canvas.drawText("Interval = " + (counter++) + " seconds.", 0, 60, p);
                Thread.sleep(1000); // this limit the view refresh, so it can't response in time.
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                if (canvas != null)
                {
                    holder.unlockCanvasAndPost(canvas); // 解除鎖定，並提交修改內容
                }
            }
        }
    }

    public boolean isRun()
    {
        return run;
    }

    public void setRun(boolean  run)    {
        this.run = run;
    }
}