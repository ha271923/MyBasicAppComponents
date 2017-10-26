package sample.hawk.com.mybasicappcomponents.designpattern.visitor.compare;

import java.util.Iterator;

//add,iterator等方法只有在 Directory 類別有效，因此預設是丟出例外
public abstract class Entry implements IAcceptor {
    public abstract String getName(); // 取得檔名

    public Entry add(Entry entry) throws SongTreatmentException { // 新增進入點
        throw new SongTreatmentException();
    }

    public Iterator iterator() throws SongTreatmentException { // 產生Iterator
        throw new SongTreatmentException();
    }

    public String toString() {                                          // 印出字串
        return getName();
    }
}