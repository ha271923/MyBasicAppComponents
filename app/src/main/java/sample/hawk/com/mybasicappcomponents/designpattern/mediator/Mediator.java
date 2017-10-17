package sample.hawk.com.mybasicappcomponents.designpattern.mediator;

/**
 * Created by ha271 on 2017/10/13.
 */

abstract public class Mediator {
    protected APP_RD appRD;
    protected SSD_RD ssdRD;
    public abstract void Work(String msgType, String msgCon, Colleague colleague);
}
