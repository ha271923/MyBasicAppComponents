package sample.hawk.com.mybasicappcomponents.designpattern.vistor.feature;

import java.util.Vector;
import java.util.Iterator;

import sample.hawk.com.mybasicappcomponents.designpattern.vistor.IAcceptor;
import sample.hawk.com.mybasicappcomponents.designpattern.vistor.Visitable;

/**
 * 在 java.util.Vector 建立一個具有 Acceptor 介面功能的 AcceptorVector 類別。讓它能對 AcceptorVector 的物
 * 件個體 add Directory 和 File 的物件個體，而且也能 accept ListVisitor 的物件個體。
 * AcceptorVector 類別是 java.util.Vector 的子類別，被定義要實作 Acceptor。add 方法是繼承自 Vector，不需要另外定義。
 */
public class AcceptorVector extends Vector implements IAcceptor {

    @Override
    public void accept(Visitable v) {
        Iterator it = iterator();
        while (it.hasNext()) {
            IAcceptor a = (IAcceptor)it.next();
            a.accept(v);
        }
    }
}