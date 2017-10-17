package sample.hawk.com.mybasicappcomponents.designpattern.visitor.feature;

import java.util.Iterator;

import sample.hawk.com.mybasicappcomponents.designpattern.visitor.Visitable;
import sample.hawk.com.mybasicappcomponents.designpattern.visitor.data.Directory;
import sample.hawk.com.mybasicappcomponents.designpattern.visitor.data.Entry;
import sample.hawk.com.mybasicappcomponents.designpattern.visitor.data.File;

/**
 * Directory 類別的 getSize 方法是進行取得目錄容量的處理。現在把這個方法改寫成取得容量大小的 SizeVisitor 類別。
 *
 * */

public class SizeVisitor extends Visitable {
    private int size = 0;

    public int getSize() {
        return size;
    }

    @Override
    public void visit(File file) {
        size += file.getSize();
    }

    @Override
    public void visit(Directory directory) {
        Iterator it = directory.iterator();
        while (it.hasNext()) {
            Entry entry = (Entry)it.next();
            entry.accept(this); //accept 呼叫 visit，visit 呼叫 accept，兩個方法互相呼叫對方
        }
    }
}