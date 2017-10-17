package sample.hawk.com.mybasicappcomponents.designpattern.proxy;

import sample.hawk.com.mybasicappcomponents.IDemo;

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

public class Proxy_Pattern implements IDemo{
    public void demo(){
        Client client = new Client();
        client.show();
    }
}
