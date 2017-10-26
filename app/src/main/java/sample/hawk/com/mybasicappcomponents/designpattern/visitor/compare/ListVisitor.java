package sample.hawk.com.mybasicappcomponents.designpattern.visitor.compare;

/**
 * Created by ha271 on 2017/10/12.
 */

import java.util.Iterator;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

//accept 呼叫 visit，visit 呼叫 accept，兩個方法互相呼叫對方
public class ListVisitor extends Visitable {
    private String currentPlayList = "";

    public void visit(Song song) {                  // 訪問檔案時即呼叫
        SMLog.i(currentPlayList + "/" + song);
    }

    @Override
    public void visit(Playlist playlist) {   // 訪問目錄時即呼叫
        SMLog.i(currentPlayList + ":" + playlist);
        String savePlayList = currentPlayList;
        currentPlayList = currentPlayList + ":" + playlist.getName();
        Iterator it = playlist.iterator();
        while (it.hasNext()) {
            Entry entry = (Entry)it.next();
            entry.accept(this); //accept 呼叫 visit，visit 呼叫 accept，兩個方法互相呼叫對方
        }
        currentPlayList = savePlayList;
    }
}