package sample.hawk.com.mybasicappcomponents.designpattern.proxy;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * 其實就是中間再加一層, 作為下列1~8其中一項的設計, 另外也為了避免高低速匹配, 例如會有PrinterProxy之類的設計
 * 代理模式(Proxy)有兩個物件，代理物件、真實物件
 * 當我們想要做某件事的時候（ex:買衣服），不直接存取原始物件（直接找工廠），而是透過代理的管道（代理商）來處理。最常用到的大概有：
 *   1. 遠端代理（Remote）：代理遠端程時執行，例如我們可以透過WebService的WSDL定義產生中介檔的函式庫，透過這個函式庫就可以存取WebService。
 *   2. 虛擬代理（Virtual）：將需要秏費大量時間或是複雜的實體，利用代理模式的物件代替。
 *   3. 安全代理（Protect or Access）：控制物件存取時的許可權。
 *   4. 智慧參考（Smart Reference）：提供比原有物件更多的服務。
 *   5．Copy-on-Write代理：虛擬代理的一種。把Clone拖延到只有在用戶端需要時，才真正採取行動。
 *   6．防火牆（Firewall）代理：保護目標，不讓惡意使用者接近。
 *   7．同步化（Synchronization）代理：使幾個使用者能夠同時使用一個物件而沒有衝突。
 *   8．智慧引用（Smart Reference）代理：當一個物件被引用時，提供一些額外的操作，比如將對此物件調用的次數記錄下來等。
 *    
 *   其它還有很多啦，像是快取也算是一種Proxy的應用
 *
 *   https://en.wikipedia.org/wiki/Proxy_pattern
 */

public class Client {
    public void show(){
        String apiName = "TestAPI1";
        ProxyService proxyService = new ProxyService();
        SMLog.i("In Client, call "+apiName);
        proxyService.remoteAPI(apiName);
    }

}
