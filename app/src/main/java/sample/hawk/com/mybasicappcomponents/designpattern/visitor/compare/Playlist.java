package sample.hawk.com.mybasicappcomponents.designpattern.visitor.compare;

import java.util.Iterator;
import java.util.Vector;

/**
 * container
 */

public class Playlist extends Entry {
    private String mPlaylistName;
    private Vector songs = new Vector();

    public Playlist(String name) {
        this.mPlaylistName = name;
    }

    @Override
    public String getName() {
        return mPlaylistName;
    }

    @Override
    public Entry add(Entry entry) {
        songs.add(entry);
        return this;
    }

    @Override
    public void accept(Visitable v) {
        v.visit(this); // Recursive call: PlayList::accept()->ListVisitor::visit()->Song::accept()::ListVisitor::visit()
    }

    @Override
    public Iterator iterator() {
        return songs.iterator();
    }

}