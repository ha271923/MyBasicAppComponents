package sample.hawk.com.mybasicappcomponents.designpattern.adapter.ClassAdapter;

import sample.hawk.com.mybasicappcomponents.IDemo;

/**
 * Created by ha271 on 2017/10/13.
 */

public class ClassAdapter_Pattern implements IDemo {
    @Override
    public void demo() {
        cPlayerAudio audioPlayer2 = new cPlayerAudio();
        audioPlayer2.play("mp3", "beyond the horizon.mp3");
        audioPlayer2.play("mp4", "alone.mp4");
        audioPlayer2.play("vlc", "far far away.vlc");
        audioPlayer2.play("avi", "mind me.avi");

        cAdapterMedia adapterMedia2 = new cAdapterMedia("vlc");
        adapterMedia2.play("vlc", "far far away.vlc");
    }
}
