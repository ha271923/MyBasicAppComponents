package sample.hawk.com.mybasicappcomponents.designpattern.composite;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * content
 */

public class Song implements IPlayable {
    private String mSongName;
    Song(String songName) {
        this.mSongName = songName;
    }
    public void play() {
        SMLog.i("播放 " + mSongName);
    }
}