package sample.hawk.com.mybasicappcomponents.designpattern.callback;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/1/6.
 */

public class CallBack1 {

    private ICallBack1 callback;

    public void  register(ICallBack1 callback){
        this.callback = callback;
    }

    public void  api(){
        SMLog.i("........  api  .........");
        callback.onCall_API();
    }
}
