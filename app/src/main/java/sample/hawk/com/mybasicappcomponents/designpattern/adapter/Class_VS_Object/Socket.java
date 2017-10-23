package sample.hawk.com.mybasicappcomponents.designpattern.adapter.Class_VS_Object;

/**
 *
 * Class Adapter (使用繼承技術)
 *  Client: 與符合ITarget interface的object合作。
 *  ITarget: 是期待得到的Output接口類型
 *  Adapter: 本pattern的核心，負責把Input接口轉換成Output接口
 *  Adaptee: 現有待適配的接口類型
 *
 *  Adapter: 在 ClassAdapter是繼承, 而 ObjectAdapter是組合
 *
 */

public class Socket {

    public int getVolt(Input input){
        return convertVolt(input);
    }

    // IMPORTANT!! Adapter design pattern最重要的就是設計Adaptee與Adapter之間如何轉換的Algorithm, 並輸出ITarget
    protected int convertVolt(Input input) {
        // Test Algorithm
        if(input instanceof InputFire)
            return ((InputFire)input).mFire/10;
        if(input instanceof InputWater)
            return ((InputWater)input).mWater/100;
        return 0;
    }
}
