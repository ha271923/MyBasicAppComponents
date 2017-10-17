package sample.hawk.com.mybasicappcomponents.designpattern.visitor;

import java.util.Iterator;

import sample.hawk.com.mybasicappcomponents.IDemo;
import sample.hawk.com.mybasicappcomponents.designpattern.visitor.data.Directory;
import sample.hawk.com.mybasicappcomponents.designpattern.visitor.data.File;
import sample.hawk.com.mybasicappcomponents.designpattern.visitor.data.FileTreatmentException;
import sample.hawk.com.mybasicappcomponents.designpattern.visitor.feature.AcceptorVector;
import sample.hawk.com.mybasicappcomponents.designpattern.visitor.feature.FileFindVisitor;
import sample.hawk.com.mybasicappcomponents.designpattern.visitor.feature.ListVisitor;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 *
 * 實作Visitor Pattern會用到一個比較複雜的遞迴(Double dispatch)
 * 一般遞迴是自己呼叫自己，在這裡面是Acceptor與Visitor的彼此間呼叫遞迴
 *
 * 如果我們需要對一個複雜系統架構(Acceptor)，針對整個系統做一巡訪
 * 並做不同的動作處理，將把這處理器(Visitor)的部份獨立出來成為物件
 * 也就是將資料處理由資料結構中分離出來，就是Visitor Pattern
 *
 * 例如，你有一個檔案架構系統，你希望把整個檔案目錄架構整個列印出來
 * 我們需要有一個ListVisitor是負責來做把檔案列印出來的工作，
 * 只要把這ListVisitor丟到一個複雜架構(Acceptor)內，就會達到這目的
 *
 * 如果我們希望在這檔案架構內，希望可以把所有圖檔都可以產生一個縮圖
 * 這時候，你可以產生另一個thumb_Visitor，用來負責產生縮圖的工作
 * 只要把這thumb_Visitor丟到架構內，就會達到目的
 *
 * 對於一個複雜架構的系統(Acceptor),丟進去不同功能的Visitor
 * 就可以對整個系統做不同功能的事
 *
 *
 * 當一個「物件結構」中的「元素」幾乎不會異動，但這些「元素的行為」(FileFind,List,Size)常會增減，則適合用訪問者模式。
 * 訪問者模式是將「元素的行為」，提取出來，每一種行為(FileFind,List,Size)做成一個 「Visitor(訪問者) 物件」，
 * 每一個 「Visitor(訪問者) 物件」，都能依據不同的「元素」，對應到不同的行為結果。
 */

public class Visitor_Pattern implements IDemo{
    public void demo(){
        try {
            SMLog.i("1. Creating entries in root dir... =============");
            Directory dir0 = new Directory("dir0");
            Directory dir1 = new Directory("dir1");
            Directory dir2 = new Directory("dir2");
            Directory dir3 = new Directory("dir3");
            dir0.add(dir1);
            dir0.add(dir2);
            dir0.add(dir3);
            dir1.add(new File("file1.html", 100));
            dir1.add(new File("file2", 200));
            dir0.accept(new ListVisitor()); //列出當前 dir0

            SMLog.i("2. Creating entries in sub-dirs... =============");
            Directory dir4 = new Directory("dir4");
            Directory dir5 = new Directory("dir5");
            Directory dir6 = new Directory("dir6");
            dir3.add(dir4);
            dir3.add(dir5);
            dir3.add(dir6);
            dir4.add(new File("file3.html", 300));
            dir4.add(new File("file4.java", 400));
            dir5.add(new File("file5.tex", 500));
            dir6.add(new File("file6.doc", 600));
            dir6.add(new File("file7.mail", 700));
            dir0.accept(new ListVisitor()); //列出當前 dir0

            // 搜尋檔案
            SMLog.i("3. Find out all .html files ==================");
            FileFindVisitor ffv = new FileFindVisitor(".html");
            dir0.accept(ffv);
            SMLog.i("HTML files are:");
            Iterator it = ffv.getFoundFiles();
            while (it.hasNext()) {
                File file = (File)it.next();
                SMLog.i("" + file);
            }

            // 目錄大小
            SMLog.i("4. getSize2 ==================");
            SMLog.i("dir0 Dir Size=" +dir0.getDirSize());

            // An AcceptorVector support to add dir and file both in his owned vector data.
            SMLog.i("5. Acceptor Vector ==================");
            AcceptorVector vec = new AcceptorVector(); // vec is NOT dir0
            vec.add(dir1);
            vec.add(dir2);
            vec.add(new File("etc.html", 1234));
            vec.accept(new ListVisitor());

        } catch (FileTreatmentException e) {
            e.printStackTrace();
        }
    }
}
