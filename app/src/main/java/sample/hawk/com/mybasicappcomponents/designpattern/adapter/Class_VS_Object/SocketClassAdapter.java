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
 */

//Using inheritance for adapter pattern
public class SocketClassAdapter extends Socket implements ISocketTarget {  // IMPORTANT!! 這裡使用了inheritance

    @Override
    public int getVolt(Input input) {
        return convertVolt(input); // 直接呼叫Base class的Method, 與Base class並無物件參考關希
    }

    protected int convertVolt(Input input) {
        return super.convertVolt(input);
    }

}
