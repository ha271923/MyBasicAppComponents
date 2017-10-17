package sample.hawk.com.mybasicappcomponents.designpattern.visitor;

import sample.hawk.com.mybasicappcomponents.designpattern.visitor.data.Directory;
import sample.hawk.com.mybasicappcomponents.designpattern.visitor.data.File;

/**
 * 程式的運作核心是在Visitor的visit function
 *
 * 實作Visitor Pattern會用到一個比較複雜的遞迴(Double dispatch)
 * 一般遞迴是自己呼叫自己，在這裡面是Acceptor與Visitor的彼此間呼叫遞迴
 */

public abstract class Visitable {
    public abstract void visit(File file);
    public abstract void visit(Directory directory);
}