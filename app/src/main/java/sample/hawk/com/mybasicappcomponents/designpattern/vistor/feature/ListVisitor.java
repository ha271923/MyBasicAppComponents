package sample.hawk.com.mybasicappcomponents.designpattern.vistor.feature;

/**
 * Created by ha271 on 2017/10/12.
 */

import java.util.Iterator;

import sample.hawk.com.mybasicappcomponents.designpattern.vistor.Visitable;
import sample.hawk.com.mybasicappcomponents.designpattern.vistor.data.Directory;
import sample.hawk.com.mybasicappcomponents.designpattern.vistor.data.Entry;
import sample.hawk.com.mybasicappcomponents.designpattern.vistor.data.File;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

//accept 呼叫 visit，visit 呼叫 accept，兩個方法互相呼叫對方
public class ListVisitor extends Visitable {
    private String currentdir = "";

    public void visit(File file) {                  // 訪問檔案時即呼叫
        SMLog.i(currentdir + "/" + file);
    }

    @Override
    public void visit(Directory directory) {   // 訪問目錄時即呼叫
        SMLog.i(currentdir + "/" + directory);
        String savedir = currentdir;
        currentdir = currentdir + "/" + directory.getName();
        Iterator it = directory.iterator();
        while (it.hasNext()) {
            Entry entry = (Entry)it.next();
            entry.accept(this); //accept 呼叫 visit，visit 呼叫 accept，兩個方法互相呼叫對方
        }
        currentdir = savedir;
    }
}