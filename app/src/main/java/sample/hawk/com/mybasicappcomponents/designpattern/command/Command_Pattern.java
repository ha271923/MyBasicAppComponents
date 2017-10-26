package sample.hawk.com.mybasicappcomponents.designpattern.command;

import sample.hawk.com.mybasicappcomponents.IDemo;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Command Pattern == Event Pattern
 *   一旦發生事件(例如按下滑鼠左鍵，或按下右鍵)，則先將該事件變成物件個體，按照發生順序排入queue中。
 *   接著，再依序處理所有排列等候的事件。
 *
 *
 *  Q: 想像團隊在開發影像編輯軟體，您負責的是開發影像處理API，A同事告訴您，他想要有A指令，B同事告
 *     訴您，他想要有B指令，C同事告訴您，他想要有C指令... 每次你都要改Command.java, 沒完沒了
 *  A: 你想將每個command的設計能夠獨立, 讓A,B,C同事, 他們自己設計所需cmd
 *
 *
 * 優點:
 *   1.降低系統的耦合度。
 *   2.新的命令可以很容易地加入到系統中。
 *   3.可以比較容易地設計一個命令隊列和組合命令(結合Composite Pattern)。
 *   4.可以方便地實現對請求的Undo和Redo。
 * 缺點:
 *   1. 使用命令模式可能會導致某些系統有過多的具體命令類。因為針對每一個命令都需要設計一個具體命令類，因
 *      此某些系統可能需要大量具體命令類，這將影響命令模式的使用。
 *   2. 簡單命令(執行時不須cmd queue, 未來沒有連續技需求)使用 switch-case ,或是public Method 就足夠了
 *
 *   Pattern UML:
 *                 [-  1. 透過 Command  Design Pattern, 多了組合變化的彈性  -]
 *    Client ----> Invoker -->Command(I/F)::execute --> ConcreteCommand --> CMD Receiver
 *           --------- 2. 不透過, 直接呼叫 Receiver 進行處理, 較生硬 - ------->
 *
 *   Ex1:
 *    APP ----> XXX Framework --> Driver
 *
 *   Ex2:
 *    DrawAPI --> Graphic Framework(避免lag, 需要queue cmd) --> Display Driver/Service
 *
 */


/** client */
public class Command_Pattern implements IDemo {
    @Override
    public void demo() {
        String cmd = "ON"; // input command at here!
        final DeviceSwitch myDeviceSwitch = new DeviceSwitch(); // invoker
        final DeviceLight lamp = new DeviceLight(); // receiver
        final ICommand switchUp = new CommandFlipUp(lamp); // ON cmd obj
        final ICommand switchDown = new CommandFlipDown(lamp); // OFF cmd obj

        // 透過 Command Pattern進行操作, PS: 此處switch-case只是為了示範, 並非本pattern的一部分
        SMLog.i("A. Call with Command Pattern -------");
        switch(cmd) {
            case "ON":
                myDeviceSwitch.Execute(switchUp);
                break;
            case "OFF":
                myDeviceSwitch.Execute(switchDown);
                break;
            default:
                SMLog.i("Unknown command!");
        }
        // 不透過 Command Pattern進行操作
        SMLog.i("B. Call without Design Pattern ----------");
        DeviceLight deviceLight = new DeviceLight();
        deviceLight.turnOn();
        deviceLight.turnOff();

    }
}