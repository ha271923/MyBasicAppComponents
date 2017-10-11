package sample.hawk.com.mybasicappcomponents.designpattern.proxy;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * proxy就是代理人的意思，不需要本人親自去做的事情，就交給代理人去做。代理人是代替忙到無法自己動手的本人去處理工作。
 */

public class ProxyService implements IService{
    RemoteService mRemoteService;

    public ProxyService(){
        mRemoteService = new RemoteService();
    }

    @Override
    public void remoteAPI(String apiName) {
        SMLog.i("In ProxyService, call "+apiName);
        mRemoteService.remoteAPI(apiName);
    }
}
