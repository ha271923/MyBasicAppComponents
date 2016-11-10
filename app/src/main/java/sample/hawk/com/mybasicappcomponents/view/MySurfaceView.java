package sample.hawk.com.mybasicappcomponents.view;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by ha271 on 2016/11/10.
 */

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback{
    private  SurfaceHolder holder;
    private MyDrawThread mMyDrawThread;

    public   MySurfaceView(Context context)
    {
        super (context);
        holder = getHolder(); // 通過SurfaceView獲得SurfaceHolder對象
        holder.addCallback( this ); // 為holder添加回調結構SurfaceHolder.Callback
        // 創建一個繪製Thread，將holder對像作為參數傳入，這樣在繪製Thread中就可以獲得holder
        // 對象，進而在繪製Thread中可以通過holder對象獲得Canvas對象，並在Canvas上進行繪製
        mMyDrawThread =  new  MyDrawThread(holder);
    }

    // 實現SurfaceHolder.Callback接口中的三個方法，都是在主Thread中調用，而不是在繪製Thread中調用的
    @Override
    public void   surfaceChanged(SurfaceHolder holder,  int  format,  int  width,  int  height)
    {
    }

    @Override
    public void   surfaceCreated(SurfaceHolder holder)
    {
        // 啟動Thread。當這個方法調用時，說明Surface已經有效了
        mMyDrawThread.setRun( true );
        mMyDrawThread.start();
    }

    @Override
    public void  surfaceDestroyed(SurfaceHolder holder)
    {
        // 結束Thread。當這個方法調用時，說明Surface即將要被銷毀了
        mMyDrawThread.setRun( false );
    }
}
