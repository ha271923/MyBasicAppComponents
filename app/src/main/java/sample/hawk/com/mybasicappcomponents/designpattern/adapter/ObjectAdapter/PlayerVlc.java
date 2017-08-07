package sample.hawk.com.mybasicappcomponents.designpattern.adapter.ObjectAdapter;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/8/7.
 */

public class PlayerVlc implements IPlayerMediaL2 {

    @Override
    public void playVlc(String fileName) {
        String subString = fileName.substring(fileName.length() - 3);
        if(subString.equalsIgnoreCase("vlc"))
            SMLog.i("Playing VLC file: " + fileName);
        else
            SMLog.i(fileName+ " is NOT support by this player");
    }

    @Override
    public void playMp4(String fileName) {
        SMLog.i(fileName+ " is NOT support by this player");
    }
}
