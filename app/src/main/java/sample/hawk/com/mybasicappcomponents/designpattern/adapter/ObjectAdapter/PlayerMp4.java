package sample.hawk.com.mybasicappcomponents.designpattern.adapter.ObjectAdapter;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/8/7.
 */

public class PlayerMp4 implements IPlayerMediaL2 {

    @Override
    public void playVlc(String fileName) {
        SMLog.i(fileName+ " is NOT support by this player");
    }

    @Override
    public void playMp4(String fileName) {
        String subString = fileName.substring(fileName.length() - 3);
        if(subString.equalsIgnoreCase("mp4"))
            SMLog.i("Playing MP4 file: " + fileName);
        else
            SMLog.i(fileName+ " is NOT support by this player");
    }
}