package sample.hawk.com.mybasicappcomponents.designpattern.iterator;

/**
 * C++
 *  C++ implements iterators with the semantics of pointers in that language.
 *
 * Java
 *  As of Java 5, objects implementing the Iterable interface, which returns an Iterator from its only
 *  method, can be traversed using Java's foreach loop syntax. The Collection interface from the Java
 *  collections framework extends Iterable.
 *
 *
 * Iterator Pattern 遍歷容器裡面元素的一種方法。
 * 本身也是反覆的意思，又可以稱為迭代器。
 * 本範例可以把書籍放到書架上，並且依序印出來。
 */

// Aggregate(聚集) Interface，實作此Interface的類別就變成類似陣列（多個數字或變數的集合)。
public interface IAggregate {
    public abstract IMyIterator _iterator(); //一個可對應聚合的_iterator
}
