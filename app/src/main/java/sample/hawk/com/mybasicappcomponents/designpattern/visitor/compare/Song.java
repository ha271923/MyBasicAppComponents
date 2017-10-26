package sample.hawk.com.mybasicappcomponents.designpattern.visitor.compare;

/**
 * content
 */

public class Song extends Entry {
    private String mSongName;
    Song(String songName) {
        this.mSongName = songName;
    }

    @Override
    public String getName() {
        return mSongName;
    }

    @Override
    public void accept(Visitable v) {
        v.visit(this);
    }
}