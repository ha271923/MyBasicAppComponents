package sample.hawk.com.mybasicappcomponents.designpattern.iterator;

import java.util.Iterator;

import sample.hawk.com.mybasicappcomponents.IDemo;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/10/13.
 */

public class Iterator_Pattern implements IDemo {
    @Override
    public void demo() {
        BookShelf bookShelf = new BookShelf(4);
        bookShelf.appendBook(new Book("Around the World in 80 Days"));
        bookShelf.appendBook(new Book("Bible"));
        bookShelf.appendBook(new Book("Cinderella"));
        bookShelf.appendBook(new Book("Daddy-Long-Legs"));
        //我們只有用到Iterator的方法，實際上BookShelf內部怎麼實作的我們不管。
        //如果今天BookShelf把陣列改成vector，下面的程式碼還是不會變動。
        // use my custom iterator
        MyIterator _iterator = bookShelf._iterator();
        while (_iterator.hasNext()) {
            Book book = (Book)_iterator.next();
            SMLog.i("MyIterator -> [ " + book.getName()+" ]");
        }
        // use java iterator
        Iterator iterator = bookShelf.iterator();
        while (iterator.hasNext()) {
            Book book = (Book)iterator.next();
            SMLog.i("Iterator -> [ " + book.getName()+" ]");
        }
    }
}
