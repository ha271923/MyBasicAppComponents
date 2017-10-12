package sample.hawk.com.mybasicappcomponents.designpattern.iterator;

/**
 * Iterator Pattern 遍歷容器裡面元素的一種方法。
 * 本身也是反覆的意思，又可以稱為迭代器。
 * 本範例可以把書籍放到書架上，並且依序印出來。
 */

// Book類別。
public class Book {
    private String name = "";
    public Book(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
