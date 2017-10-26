package sample.hawk.com.mybasicappcomponents.designpattern.composite;

import sample.hawk.com.mybasicappcomponents.IDemo;

/**
 * For Compare Composite, Iterator, Visitor patterns.
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
 * Composite Pattern -- 對容器和內容一視同仁
 * 數個物件之間，呈現一種樹狀結構。容器底下可能是內容，也可能是更小一號的容器，之間可以組合。
 *
 * Composite 利用共通介面連接兩個物件, 利用內部私有loop輸出結果
 * 1. Leaf --> IComponent(I/F) <-- Composite
 * 2. new Composite <裡面用data structure儲存輸入物件>
 * 3. composite.add(new Leaf());
 *
 * EX: 歌曲清單(Playlist)想成是容器，歌曲(Song)想成是內容。
 *    1. Song --> IPlayable(I/F) <-- Playlist
 *    2. new Playlist()
 *    3. playlist.add(new Song())
 *    4. playlist.play() API自訂內部私有loop存取所有Song物件
 *
 * EX: 目錄(Folder)想成是容器，檔案(File)想成是內容，容器底下可能是內容。
 *
 * EX:一個線上購物商城，有A、B兩品牌，都有手機、筆電兩種商品。要產生商品選單。
 *
 */

public class Composite_Pattern implements IDemo {
    @Override
    public void demo() {
        Playlist playlist1, playlist2, all;

        Song logo = new Song("basara3 OP.mp3");

        playlist1 = new Playlist(); // 清單1
        playlist1.add(new Song("Just the way you are.flc"));
        playlist1.add(new Song("Adele - Hello.mp3"));
        playlist1.add(new Song("Taylors - Red.wav"));

        playlist2 = new Playlist(); // 清單2
        playlist2.add(new Song("蝸牛.mp3"));
        playlist2.add(new Song("刀馬旦.flc"));
        playlist2.add(new Song("聖誕快樂歌.mp3"));

        all = new Playlist(); // 清單3
        all.add(logo);
        all.add(playlist1); // 組合 清單1
        all.add(playlist2); // 組合 清單2

        all.play();
    }
}
