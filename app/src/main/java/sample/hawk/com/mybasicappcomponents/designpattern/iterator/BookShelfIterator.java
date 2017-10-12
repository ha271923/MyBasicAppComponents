package sample.hawk.com.mybasicappcomponents.designpattern.iterator;

import java.util.Iterator;

/**
 * Iterator Pattern 遍歷容器裡面元素的一種方法。
 * 本身也是反覆的意思，又可以稱為迭代器。
 * 本範例可以把書籍放到書架上，並且依序印出來。
 */

// BookShelfIterator實作MyIterator Interface。
public class BookShelfIterator implements Iterator {
    private BookShelf bookShelf;
    private int index;
    public BookShelfIterator(BookShelf bookShelf) {
        this.bookShelf = bookShelf;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        if (index < bookShelf.getLength()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Object next() {
        Book book = bookShelf.getBookAt(index);
        index++;
        return book;
    }

    // Removes the last object returned by {@code next} from the collection.
    @Override
    public void remove() {
        // not implement yet.
    }
}
