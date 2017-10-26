package sample.hawk.com.mybasicappcomponents.designpattern.command;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/** The Receiver class == 底層最終運作命令接收者, 驅動程式Driver??  */
public class DeviceLight {

    public void turnOn() {
        SMLog.i("Light == ON");
    }

    public void turnOff() {
        SMLog.i("Light == OFF");
    }
}