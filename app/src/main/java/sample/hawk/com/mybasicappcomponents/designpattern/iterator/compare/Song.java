package sample.hawk.com.mybasicappcomponents.designpattern.iterator.compare;

/**
 * content
 */

public class Song {
    private String mSongName;
    Song(String songName) {
        this.mSongName = songName;
    }
    public String play() {
        // SMLog.i("播放 " + mSongName);
        return mSongName;
    }
}