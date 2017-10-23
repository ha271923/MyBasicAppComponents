package sample.hawk.com.mybasicappcomponents.designpattern.adapter.Class_VS_Object;

/**
 * Object Adapter (使用組合技術)
 *  Client: 與符合ITarget interface的object合作。
 *  ITarget: 是期待得到的Output接口類型
 *  Adapter: 本pattern的核心，負責把Input接口轉換成Output接口
 *  Adaptee: 現有待適配的接口類型
 *
 *  Adapter: 在 ClassAdapter是繼承, 而 ObjectAdapter是組合
 */

public class SocketObjectAdapter implements ISocketTarget {

    // Using Composition for adapter pattern
    private Socket sock = new Socket(); // IMPORTANT!! 這裡使用了Base class物件來進行Volt轉換

    @Override
    public int getVolt(Input input) {
        return sock.getVolt(input); // 透過存取Base class的物件, 與Base class有物件參考關希
    }

}
