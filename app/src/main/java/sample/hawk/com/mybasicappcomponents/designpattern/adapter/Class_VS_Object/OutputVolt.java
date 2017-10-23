package sample.hawk.com.mybasicappcomponents.designpattern.adapter.Class_VS_Object;


/**
 *
 * Class Adapter (使用繼承技術)
 *  Client: 與符合ITarget interface的object合作。
 *  ITarget: 是期待得到的Output接口類型
 *  Adapter: 本pattern的核心，負責把Input接口轉換成Output接口
 *  Adaptee: 現有待適配的接口類型
 *
 *  Client
 */

public class OutputVolt {

    // Class Adapter
    int ClassAdapterOUT(Input input) {
        int ret;
        ISocketTarget classAdapter = new SocketClassAdapter(); // ITarget
        ret = OutputFromAdapter(classAdapter, input);
        return ret;
    }

    // Object Adapter
    int ObjectAdapterOUT(Input input) {
        int ret;
        ISocketTarget objectAdapter = new SocketObjectAdapter(); // ITarget
        ret = OutputFromAdapter(objectAdapter, input);
        return ret;
    }

    // (1) Client --> ITarget
    private static int OutputFromAdapter(ISocketTarget sockAdapter, Input input) {
        return sockAdapter.getVolt(input);
    }
}