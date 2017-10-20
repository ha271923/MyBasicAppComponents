package sample.hawk.com.mybasicappcomponents.designpattern.bridge;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * ConcreteImplementor (具體的實作者) 參與者2/2
 *   具體實作 Implementor 參與者的介面。例如 StringDisplayImpl 類別。
 * */
public class DrawAPI_B implements IDrawAPI {
    @Override
    public void drawIt() {
        SMLog.i("DrawAPI_B::drawIt");
    }
}
