package sample.hawk.com.mybasicappcomponents.designpattern.iterator.compare;

import sample.hawk.com.mybasicappcomponents.IDemo;
import sample.hawk.com.mybasicappcomponents.designpattern.iterator.IMyIterator;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * For Compare Composite, Iterator, Visitor patterns.
 *
 * Iterator: 可利用外部共用性類指標介面Iterator, 從外部存取內部物件
 *   1. Song物件
 *   2. Playlist<裡面用data structure儲存輸入物件> --> IAggregate(聚合I/F)
 *   3. PlaylistIterator --> IMyIterator(存取I/F)
 *   4. playlist.add(new Song())
 *   5. 外部loop結合通用Iterator存取物件
 *
 * 比較如下:
 *  Behavior\ Iterator pattern: (描述的行為是IAggregate聚合介面, IMyIterator物件存取介面)
 *    隱藏聚合物件的實作細節，僅提供外界一個可存取聚合物件內部元素的介面，
 *    程式只要透過公開 Iterator 介面，即可巡訪聚合物件的各個元素。
 *
 *  Behavior\ Visitor Pattern:
 *    利用遞迴實作好讓既存資料物件可以Accept外部新Visitor物件存取資料, 透過自創Visitor物件而非資料物件本身API來取得資料
 *
 *  Structure\ Composite Pattern: (描述物件組織架構, 利用 IPlayable介面來連接不同物件)
 *    將物件組織成樹狀結構，並且讓外界以一致性的方式對待個別類別物件和組合類別物件。
 *
 *
 */

public class Iterator_Pattern2 implements IDemo {
    @Override
    public void demo() {
        Playlist playlist = new Playlist(6);
        playlist.add(new Song("Just the way you are.flc"));
        playlist.add(new Song("Adele - Hello.mp3"));
        playlist.add(new Song("Taylors - Red.wav"));
        playlist.add(new Song("蝸牛.mp3"));
        playlist.add(new Song("刀馬旦.flc"));
        playlist.add(new Song("聖誕快樂歌.mp3"));

        IMyIterator _iterator = playlist._iterator();
        while (_iterator.hasNext()) {
            Song song = (Song)_iterator.next();
            SMLog.i("MyIterator -> [ " + song.play()+" ]");
        }
    }
}
