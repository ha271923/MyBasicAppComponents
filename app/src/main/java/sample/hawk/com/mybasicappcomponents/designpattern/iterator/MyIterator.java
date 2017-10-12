package sample.hawk.com.mybasicappcomponents.designpattern.iterator;

/**
 * Iterator Pattern 遍歷容器裡面元素的一種方法。
 * 本身也是反覆的意思，又可以稱為迭代器。
 * 本範例可以把書籍放到書架上，並且依序印出來。
 */

// 如果要掃描整個聚合時，利用_iterator方法即可建立一個實作MyIterator介面的類別物件個體
public interface MyIterator {
    public abstract boolean hasNext(); //有沒有下一個元素
    public abstract Object next(); //下一個元素
}
