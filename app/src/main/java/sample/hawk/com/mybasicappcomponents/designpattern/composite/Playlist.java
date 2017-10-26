package sample.hawk.com.mybasicappcomponents.designpattern.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * container
 */

public class Playlist implements IPlayable {
    private List<IPlayable> list = new ArrayList<IPlayable>(); // IMPORTANT! 清單資料結構或其他

    public void add(IPlayable IPlayable) {
        list.add(IPlayable);
    }
    public void play() {
        for(IPlayable IPlayable : list) {
            IPlayable.play();
        }
    }
}