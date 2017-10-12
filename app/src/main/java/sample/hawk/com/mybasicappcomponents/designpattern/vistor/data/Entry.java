package sample.hawk.com.mybasicappcomponents.designpattern.vistor.data;

import java.util.Iterator;

import sample.hawk.com.mybasicappcomponents.designpattern.vistor.IAcceptor;

//add,iterator等方法只有在 Directory 類別有效，因此預設是丟出例外
public abstract class Entry implements IAcceptor {
    public abstract String getName(); // 取得檔名
    public abstract int getSize(); // 取得檔案容量

    public Entry add(Entry entry) throws FileTreatmentException { // 新增進入點
        throw new FileTreatmentException();
    }

    public Iterator iterator() throws FileTreatmentException { // 產生Iterator
        throw new FileTreatmentException();
    }

    public String toString() {                                          // 印出字串
        return getName() + " (" + getSize() + ")";
    }
}