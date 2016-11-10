package sample.hawk.com.mybasicappcomponents.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;

/**
 * Created by ha271 on 2016/11/10.
 */

// 繪製Thread
public class  MyDrawThread  extends  Thread
{
    private SurfaceHolder holder;
    private boolean  run;

    public  MyDrawThread(SurfaceHolder holder)
    {
        this .holder = holder;
        run =  true ;
    }

    @Override
    public void  run()
    {
        int  counter =  0 ;
        Canvas canvas =  null ;
        while (run) // 具體繪製工作
        {
            try
            {
                canvas= holder.lockCanvas(); // 獲取Canvas對象，並鎖定之
                canvas.drawColor(Color.WHITE); // 設定Canvas對象的背景顏色
                Paint p =  new  Paint(); // 創建畫筆
                p.setColor(Color.RED); // 設置畫筆顏色
                p.setTextSize( 30 ); // 設置文字大小
                Rect rect =  new  Rect( 100 ,  50 ,  380 ,  330 ); // 創建一個Rect對象rect
                canvas.drawRect(rect,p); // 在canvas上繪製rect
                // 在canvas上顯示時間
                canvas.drawText( "Interval = "  + (counter++) +  " seconds." ,  100 ,  410 , p);
                Thread.sleep( 1000 );
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                if (canvas !=  null )
                {
                    holder.unlockCanvasAndPost(canvas); // 解除鎖定，並提交修改內容
                }
            }
        }
    }

    public boolean  isRun()
    {
        return  run;
    }

    public void  setRun( boolean  run)
    {
        this .run = run;
    }
}