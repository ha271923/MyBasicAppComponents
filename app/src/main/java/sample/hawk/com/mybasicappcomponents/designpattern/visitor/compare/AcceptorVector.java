package sample.hawk.com.mybasicappcomponents.designpattern.visitor.compare;

import java.util.Iterator;
import java.util.Vector;

/**
 * Created by ha271 on 2017/10/26.
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