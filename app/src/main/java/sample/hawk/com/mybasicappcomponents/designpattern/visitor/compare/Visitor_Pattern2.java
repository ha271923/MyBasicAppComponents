package sample.hawk.com.mybasicappcomponents.designpattern.visitor.compare;

import java.util.Iterator;

import sample.hawk.com.mybasicappcomponents.IDemo;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * For Compare Composite, Iterator, Visitor patterns.
 *
 * 比較如下:
 *  Behavior\ Iterator pattern: 提供類指標介面給外部操作 (描述的行為是IAggregate聚合介面, IMyIterator物件存取介面)
 *    隱藏聚合物件的實作細節，僅提供外界一個可存取聚合物件內部元素的介面，
 *    程式只要透過公開 Iterator 介面，即可巡訪聚合物件的各個元素。
 *
 *  Behavior\ Visitor Pattern: 參訪既存資料物件的物件
 *    利用遞迴實作好讓既存資料物件可以Accept外部新Visitor物件存取資料, 透過自創Visitor物件而非資料物件本身API來取得資料
 *
 *  Structure\ Composite Pattern: 組合多種類型物件 (描述物件組織架構, 利用 IPlayable介面來連接不同物件)
 *    將物件組織成樹狀結構，並且讓外界以一致性的方式對待個別類別物件和組合類別物件。
 *
 *
 */

public class Visitor_Pattern2 implements IDemo {
    @Override
    public void demo() {
        SMLog.i("1. Creating entries in Playlist... =============");
        Playlist playlist = new Playlist("SongPlayList1");
        playlist.add(new Song("Song1.flc"));
        playlist.add(new Song("Song2.mp3"));
        playlist.add(new Song("Song3.wav"));
        playlist.add(new Song("Song4.mp3"));
        playlist.add(new Song("Song5.flc"));
        playlist.add(new Song("Song6.mp3"));
        playlist.accept(new ListVisitor());

        // 搜尋檔案
        SMLog.i("2. Find out all .flc files ==================");
        SongFindVisitor sfv = new SongFindVisitor(".flc");
        playlist.accept(sfv);
        SMLog.i("FLC songs are:");
        Iterator it = sfv.getFoundSongs();
        while (it.hasNext()) {
            Song song = (Song)it.next();
            SMLog.i("" + song);
        }

        // An AcceptorVector support to add dir and file both in his owned vector data.
        SMLog.i("5. Acceptor Vector ==================");
        AcceptorVector vec = new AcceptorVector(); // vec is NOT dir0
        vec.add(playlist);
        vec.add(new Song("Song7.wav"));
        vec.accept(new ListVisitor());
    }
}
