package sample.hawk.com.mybasicappcomponents.designpattern.mediator;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/10/13.
 */

public class APP_RD extends Colleague {

    public APP_RD(String name, Mediator mediator)
    {
        super(name, mediator);
        mediator.appRD = this;
    }

    // APP行動
    public void Action(String msgCon, Colleague colleague)
    {
        SMLog.i(this.name+" 接收到 "+colleague.name+"   訊息："+msgCon  +"   進行APP debugging");
    }
}
