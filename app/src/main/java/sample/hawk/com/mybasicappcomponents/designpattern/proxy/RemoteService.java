package sample.hawk.com.mybasicappcomponents.designpattern.proxy;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * 利用Proxy提高處理速度, 利用 PrinterProxy 可以把較重的處理(產生物件個體)往後延遲到實際 print 為止。
 */

public class RemoteService implements IService{
    @Override
    public void remoteAPI(String apiName) {
        SMLog.i("In RemoteService, call "+apiName);
    }
}
