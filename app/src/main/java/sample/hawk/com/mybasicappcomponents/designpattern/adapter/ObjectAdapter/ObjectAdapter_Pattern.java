package sample.hawk.com.mybasicappcomponents.designpattern.adapter.ObjectAdapter;

import sample.hawk.com.mybasicappcomponents.IDemo;

/**
 *
 * 定義
 *  把一個類的接口變換成客戶端所期待的另一種接口，而使原本兩個不匹配的接口可以一起工作。
 *
 * 組成
 *  Client: 與符合ITarget interface的object合作。
 *  ITarget: 定義Client所用的與應用領域相關之interface。
 *  Adapter: 將Adaptee轉換成ITarget interface。
 *  Adaptee: 需要被轉換的既有interface。
 *
 * 優點
 *  1. 可以讓任何兩個沒有關聯的類一起運行。
 *  2. 增加了類的透明性和復用性。
 *  3. 靈活性和擴展性都非常好。
 *
 * 缺點
 *  1. 過多的使用適配器，會讓系統非常零亂。
 *  2. 由於JAVA 至多繼承一個類，所以至多只能適配一個適配者類，而且目標類必須是抽像類。
 *
 *
 *  Client:  ClassAdapter_Pattern use them
 *  Targer:    PlayerAudio  implemented IPlayerMediaL1(play) and used adapterMedia
 *  ITarget:   IPlayerMediaL1
 *  Adapter:     AdapterMedia implemented IPlayerMediaL1(play) and used PlayerVlc and PlayerMp4
 *  Adaptee:        PlayerVlc implemented IPlayerMediaL2(playVlc,playMp4)
 *  Adaptee:        PlayerMp4 implemented IPlayerMediaL2(playVlc,playMp4)
 *
 *     注意!! IPlayerMediaL2並不是ITarget, 只是一個普通的interface而已
 *
 */

public class ObjectAdapter_Pattern implements IDemo {
    @Override
    public void demo(){
        PlayerAudio audioPlayer = new PlayerAudio();
        audioPlayer.play("mp3", "beyond the horizon.mp3");
        audioPlayer.play("mp4", "alone.mp4");
        audioPlayer.play("vlc", "far far away.vlc");
        audioPlayer.play("avi", "mind me.avi");

        AdapterMedia adapterMedia = new AdapterMedia("vlc");
        adapterMedia.play("vlc", "far far away.vlc");

        PlayerMp4 mp4Player = new PlayerMp4();
        mp4Player.playMp4("alone.mp4");
        mp4Player.playVlc("far far away.vlc");

        PlayerVlc vlcPlayer = new PlayerVlc();
        vlcPlayer.playMp4("alone.mp4");
        vlcPlayer.playVlc("far far away.vlc");
    }
}
