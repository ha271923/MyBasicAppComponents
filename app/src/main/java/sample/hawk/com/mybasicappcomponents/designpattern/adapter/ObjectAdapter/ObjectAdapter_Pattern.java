package sample.hawk.com.mybasicappcomponents.designpattern.adapter.ObjectAdapter;

import sample.hawk.com.mybasicappcomponents.IDemo;

/**
 * Created by ha271 on 2017/10/13.
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
