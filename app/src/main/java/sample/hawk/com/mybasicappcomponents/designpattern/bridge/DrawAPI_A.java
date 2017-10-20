package sample.hawk.com.mybasicappcomponents.designpattern.bridge;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * ConcreteImplementor (具體的實作者) 參與者1/2
 *   具體實作 Implementor 參與者的介面。例如 StringDisplayImpl 類別。
 * */
public class DrawAPI_A implements IDrawAPI {
    @Override
    public void drawIt() {
        SMLog.i("DrawAPI_A::drawIt");
    }
}
