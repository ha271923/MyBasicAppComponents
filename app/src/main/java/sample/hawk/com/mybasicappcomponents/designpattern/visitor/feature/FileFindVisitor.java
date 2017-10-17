package sample.hawk.com.mybasicappcomponents.designpattern.visitor.feature;

import java.util.Iterator;
import java.util.Vector;

import sample.hawk.com.mybasicappcomponents.designpattern.visitor.Visitable;
import sample.hawk.com.mybasicappcomponents.designpattern.visitor.data.Directory;
import sample.hawk.com.mybasicappcomponents.designpattern.visitor.data.Entry;
import sample.hawk.com.mybasicappcomponents.designpattern.visitor.data.File;

public class FileFindVisitor extends Visitable {
    private String filetype;
    private Vector found = new Vector();

    public FileFindVisitor(String filetype) { // 設定時在副檔名前加上.，如".txt"
        this.filetype = filetype;
    }

    public Iterator getFoundFiles() { // 取得找到的檔案
        return found.iterator();
    }

    public void visit(File file) { // 訪問檔案時即呼叫
        if (file.getName().endsWith(filetype)) {
            found.add(file);
        }
    }
    @Override
    public void visit(Directory directory) { // 訪問目錄時即呼叫
        Iterator it = directory.iterator();
        while (it.hasNext()) {
            Entry entry = (Entry)it.next();
            entry.accept(this); //accept 呼叫 visit，visit 呼叫 accept，兩個方法互相呼叫對方
        }
    }
}