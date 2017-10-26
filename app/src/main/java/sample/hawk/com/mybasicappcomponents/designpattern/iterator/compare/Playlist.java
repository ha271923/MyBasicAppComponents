package sample.hawk.com.mybasicappcomponents.designpattern.iterator.compare;

import sample.hawk.com.mybasicappcomponents.designpattern.iterator.IAggregate;
import sample.hawk.com.mybasicappcomponents.designpattern.iterator.IMyIterator;

/**
 * container
 */

public class Playlist implements IAggregate {

    private Song[] songs; // Hawk: 最終還是以有序空間儲存
    private int last = 0;
    public Playlist(int maxsize) {
        this.songs = new Song[maxsize];
    }
    public Song getSongAt(int index) {
        return songs[index];
    }
    public void add(Song song) {
        this.songs[last] = song;
        last++;
    }

    public void play() {
        for(Song s : songs) {
            s.play();
        }
    }

    public int getLength() {
        return last;
    }
    @Override
    public IMyIterator _iterator() { // My custom iterator
        return new PlaylistIterator(this);
    }
}