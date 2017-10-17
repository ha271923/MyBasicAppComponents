package sample.hawk.com.mybasicappcomponents.designpattern.mediator;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/10/13.
 */

abstract public class Colleague {
    protected String name; // 姓名

    protected Mediator mediator; // 中介者

    // 設定姓名、中介者
    public Colleague(String name, Mediator mediator)
    {
        this.name = name;
        this.mediator = mediator;
    }

    // 發送訊息給中介者
    public void Send(String msgType, String msgCon)
    {
        mediator.Work(msgType, msgCon, this);
    }

    // 接收一般訊息
    public void Receive(String msgCon, Colleague colleague)
    {
        SMLog.i(this.name+" 接收到 "+colleague.name+"   訊息："+msgCon);
    }
}
