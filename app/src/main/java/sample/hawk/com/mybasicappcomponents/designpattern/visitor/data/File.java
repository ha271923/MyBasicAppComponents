package sample.hawk.com.mybasicappcomponents.designpattern.visitor.data;

import sample.hawk.com.mybasicappcomponents.designpattern.visitor.Visitable;

public class File extends Entry {
    private String name;
    private int size;

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    @Override
    public void accept(Visitable v) {
        v.visit(this); // Recursive call: Directory::visit()->Directory::accept()->ListVisitor::visit()->Entry::accept()->File::accept()->ListVisitor::visit()
    }
}