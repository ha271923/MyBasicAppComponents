package sample.hawk.com.mybasicappcomponents.designpattern.visitor.data;

import java.util.Iterator;
import java.util.Vector;

import sample.hawk.com.mybasicappcomponents.designpattern.visitor.feature.SizeVisitor;
import sample.hawk.com.mybasicappcomponents.designpattern.visitor.Visitable;

public class Directory extends Entry {
    private String name;
    private Vector dir = new Vector();

    public Directory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        int size = 0;
        Iterator it = dir.iterator();
        while (it.hasNext()) {
            Entry entry = (Entry)it.next();
            size += entry.getSize();
        }
        return size;
    }

    public int getDirSize() { // 取得目錄容量
        SizeVisitor v = new SizeVisitor();
        accept(v);
        return v.getSize();
    }

    @Override
    public Entry add(Entry entry) { // 新增進入點
        dir.add(entry);
        return this;
    }

    @Override
    public Iterator iterator() {
        return dir.iterator();
    }

    @Override
    public void accept(Visitable v) { // 接受訪客
        v.visit(this); // Recursive call: Directory::accept()->ListVisitor::visit()->Directory:accept()->ListVisitor::visit()->File::accept().
    }
}