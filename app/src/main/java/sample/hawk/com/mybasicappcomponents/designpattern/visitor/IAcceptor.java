package sample.hawk.com.mybasicappcomponents.designpattern.visitor;

/**
 * Acceptor內需要定義一個accept method,這method只是都固定用來
 * 把自己當作參數，呼叫啟動visitor的巡訪動作
 *
 * 實作Visitor Pattern會用到一個比較複雜的遞迴(Double dispatch)
 * 一般遞迴是自己呼叫自己，在這裡面是Acceptor與Visitor的彼此間呼叫遞迴
 *
 */

public interface IAcceptor {
    public abstract void accept(Visitable v);
}
