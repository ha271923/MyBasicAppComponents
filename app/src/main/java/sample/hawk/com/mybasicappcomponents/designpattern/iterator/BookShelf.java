package sample.hawk.com.mybasicappcomponents.designpattern.iterator;

import java.util.Iterator;

/**
 * Iterator Pattern 遍歷容器裡面元素的一種方法。
 * 本身也是反覆的意思，又可以稱為迭代器。
 * 本範例可以把書籍放到書架上，並且依序印出來。
 */

// BookShelf類別就是一個聚合(放書)的實體，實作Aggregate Interface。
public class BookShelf implements IAggregate {
    private Book[] books; // Hawk: 最終還是以有序空間儲存
    private int last = 0;
    public BookShelf(int maxsize) {
        this.books = new Book[maxsize];
    }
    public Book getBookAt(int index) {
        return books[index];
    }
    public void appendBook(Book book) {
        this.books[last] = book;
        last++;
    }
    public int getLength() {
        return last;
    }

    public IMyIterator _iterator() { // My custom iterator
        return new BookShelfMyIterator(this);
    }

    public Iterator iterator() { // java iterator
        return new BookShelfIterator(this);
    }
}
