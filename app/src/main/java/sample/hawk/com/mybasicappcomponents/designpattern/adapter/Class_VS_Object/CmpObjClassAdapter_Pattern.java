package sample.hawk.com.mybasicappcomponents.designpattern.adapter.Class_VS_Object;

import sample.hawk.com.mybasicappcomponents.IDemo;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Adapter == Wrapper
 *
 *  Class Adapter (使用繼承技術)
 * Object Adapter (使用組合技術) 純 Class Adapter在Java中適用的場合較少, 大多是用Object Adapter
 *
 *  Client: 與符合ITarget interface的object合作。
 *  ITarget: 是期待得到的Output接口類型
 *  Adapter: 本pattern的核心，負責把Input接口轉換成Output接口
 *  Adaptee: 現有待適配的接口類型
 *
 * 優點
 *  可以讓任何兩個沒有關聯的類一起運行。
 *  增加了類的透明性和復用性。
 *  靈活性和擴展性都非常好。
 * 缺點
 *  過多的使用適配器，會讓系統非常零亂。
 *  由於JAVA 至多繼承一個類，所以至多只能適配一個適配者類，而且目標類必須是抽像類。
 *
 *
 *
 *          |-- InputFire
 *  Input --               --> SocketXXXAdapter --> [ Convert ] --> ISocketTarget --> OutputVolt
 *          \-- InputWater
 */

public class CmpObjClassAdapter_Pattern implements IDemo {
    @Override
    public void demo() {
        OutputVolt outputVolt = new OutputVolt();

        InputFire fire = new InputFire();
        fire.mFire = 8888;
        SMLog.i(" ClassOUT: Fire-->Power output="+ outputVolt.ClassAdapterOUT(fire));
        SMLog.i("ObjectOUT: Fire-->Power output="+ outputVolt.ObjectAdapterOUT(fire));

        InputWater water = new InputWater();
        water.mWater = 123456789;
        SMLog.i(" ClassOUT: Water-->Power output="+ outputVolt.ClassAdapterOUT(water));
        SMLog.i("ObjectOUT: Water-->Power output="+ outputVolt.ObjectAdapterOUT(water));

    }
}
