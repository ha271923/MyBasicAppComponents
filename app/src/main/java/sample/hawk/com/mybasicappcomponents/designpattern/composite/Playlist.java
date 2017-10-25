package sample.hawk.com.mybasicappcomponents.designpattern.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * container
 */

public class Playlist implements Playable {
    private List<Playable> list = new ArrayList<Playable>(); // IMPORTANT! 清單資料結構或其他

    public void add(Playable playable) {
        list.add(playable);
    }
    public void play() {
        for(Playable playable : list) {
            playable.play();
        }
    }
}