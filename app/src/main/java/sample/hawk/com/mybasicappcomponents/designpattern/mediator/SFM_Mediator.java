package sample.hawk.com.mybasicappcomponents.designpattern.mediator;


import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Work 的分配實作是此Pattern的關鍵, 將引導訊息的流向
 */

public class SFM_Mediator extends Mediator {

    // 中介者處理接收到的訊息
    @Override
    public void Work(String msgType, String msgCon, Colleague colleague)
    {
        SMLog.i(" SFM 接收到 "+colleague.name+"  訊息："+msgCon  +" 指派給 ==>");
        switch (msgType)
        {
            case "AppBug":
                this.appRD.Action(msgCon, colleague);
                break;
            case "DriverBug":
                this.ssdRD.Action(msgCon, colleague);
                break;
            case "Normal":
                if (colleague != this.appRD)
                    this.appRD.Receive(msgCon, colleague);
                else if (colleague != this.ssdRD)
                    this.ssdRD.Receive(msgCon, colleague);
                break;
        }
    }
}
