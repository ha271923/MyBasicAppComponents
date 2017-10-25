package sample.hawk.com.mybasicappcomponents.designpattern.composite;

import sample.hawk.com.mybasicappcomponents.IDemo;

/**
 * Composite Pattern -- 對容器和內容一視同仁
 * 數個物件之間，呈現一種樹狀結構。容器底下可能是內容，也可能是更小一號的容器，之間可以組合。
 *
 * EX:
 *   歌曲清單(Playlist)想成是容器，歌曲(Song)想成是內容。
 *
 * EX:
 *   目錄(Folder)想成是容器，檔案(File)想成是內容，容器底下可能是內容。
 *
 * EX:
 *   一個線上購物商城，有A、B兩品牌，都有手機、筆電兩種商品。要產生商品選單。
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
