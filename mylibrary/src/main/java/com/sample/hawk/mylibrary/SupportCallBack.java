package com.sample.hawk.mylibrary;

/**
 * Created by ha271 on 2017/1/6.
 */

public class SupportCallBack {

    private MyCallBack callback;

    public void  register(MyCallBack callback){
        this.callback = callback;
    }

    public void  api(){
        SMLog.i("........  api  .........");
        callback.onCall_API();
    }
}
