package sample.hawk.com.mybasicappcomponents.debugTest.Exceptions;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/4/5.
 */

public class JavaExceptions {

    public enum Exceptions{
        GenericException,
        ConcurrentModificationException
    }

    public void Test(Exceptions param){
        switch(param){
            case ConcurrentModificationException:
                ConcurrentModificationException cme = new ConcurrentModificationException();
                cme.Demo();
                break;
            default:
                SMLog.i("Not support ID:"+param);
        }
    }



}
