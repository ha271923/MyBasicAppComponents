package sample.hawk.com.mybasicappcomponents.designpattern.iterator;

import java.util.Iterator;

import sample.hawk.com.mybasicappcomponents.IDemo;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * 比較如下:
 *  Behavior\ Iterator pattern: 提供類指標介面給外部操作 (描述的行為是IAggregate聚合介面, IMyIterator物件存取介面)
 *    隱藏聚合物件的實作細節，僅提供外界一個可存取聚合物件內部元素的介面，
 *    程式只要透過公開 Iterator 介面，即可巡訪聚合物件的各個元素。
 *
 *  Behavior\ Visitor Pattern: 參訪既存資料物件的物件
 *    利用遞迴實作好讓既存資料物件可以Accept外部新Visitor物件存取資料, 透過自創Visitor物件而非資料物件本身API來取得資料
 *
 *  Structure\ Composite Pattern: 組合多種類型物件 (描述物件組織架構, 利用 IPlayable介面來連接不同物件)
 *    將物件組織成樹狀結構，並且讓外界以一致性的方式對待個別類別物件和組合類別物件。
 *
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
        IMyIterator _iterator = bookShelf._iterator();
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
