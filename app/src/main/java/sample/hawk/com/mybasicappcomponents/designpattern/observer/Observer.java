package sample.hawk.com.mybasicappcomponents.designpattern.observer;

/**
 * 觀察者模式：
 * 定義對象間的一對多的依賴關係，當一個對象狀態發生改變時，所有依賴他的對像都得到通知並被自動更新。
 */

public interface Observer {
    public void  update();
}
