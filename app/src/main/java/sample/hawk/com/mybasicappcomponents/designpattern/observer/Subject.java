package sample.hawk.com.mybasicappcomponents.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 觀察者模式：
 * 定義對象間的一對多的依賴關係，當一個對象狀態發生改變時，所有依賴他的對像都得到通知並被自動更新。
 */


public class  Subject {

    List<Observer> lists =  new ArrayList<Observer>();

    String title;

    public String getTitle() { // ALT+insert for auto code
        return this.title;
    }

    public void setTitle(String title) { // ALT+insert for auto code
        this.title = title;
    }


    public void  register(Observer observer){
        lists.add(observer);
    }

    public void _notifyAll(){
        for  (Observer observer : lists) {
            observer.update();
        }
    }

    public void  unRegister(Observer observer){
        lists.remove(observer);
    }
}
