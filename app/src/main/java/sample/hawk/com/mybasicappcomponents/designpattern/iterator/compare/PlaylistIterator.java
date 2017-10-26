package sample.hawk.com.mybasicappcomponents.designpattern.iterator.compare;

import sample.hawk.com.mybasicappcomponents.designpattern.iterator.IMyIterator;

/**
 * Created by ha271 on 2017/10/26.
 */

public class PlaylistIterator implements IMyIterator {
    private Playlist mPlaylist;
    private int index;
    public PlaylistIterator(Playlist playlist) {
        this.mPlaylist = playlist;
        this.index = 0;
    }

    public boolean hasNext() {
        if (index < mPlaylist.getLength()) {
            return true;
        } else {
            return false;
        }
    }
    public Object next() {
        Song song = mPlaylist.getSongAt(index);
        index++;
        return song;
    }
}