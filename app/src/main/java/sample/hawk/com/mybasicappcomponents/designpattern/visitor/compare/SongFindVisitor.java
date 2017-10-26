package sample.hawk.com.mybasicappcomponents.designpattern.visitor.compare;

import java.util.Iterator;
import java.util.Vector;

public class SongFindVisitor extends Visitable {
    private String filetype;
    private Vector found = new Vector();

    public SongFindVisitor(String filetype) { // 設定時在副檔名前加上.，如".txt"
        this.filetype = filetype;
    }

    public Iterator getFoundSongs() { // 取得找到的檔案
        return found.iterator();
    }

    @Override
    public void visit(Song song) { // 訪問檔案時即呼叫
        if (song.getName().endsWith(filetype)) {
            found.add(song);
        }
    }

    @Override
    public void visit(Playlist playlist) { // 訪問目錄時即呼叫
        Iterator it = playlist.iterator();
        while (it.hasNext()) {
            Entry entry = (Entry)it.next();
            entry.accept(this); //accept 呼叫 visit，visit 呼叫 accept，兩個方法互相呼叫對方
        }
    }
}