package sample.hawk.com.mybasicappcomponents.designpattern.adapter.ClassAdapter;

import sample.hawk.com.mybasicappcomponents.designpattern.adapter.ObjectAdapter.AdapterMedia;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/8/7.
 */

public class cPlayerAudio extends cPlayerMediaL1 {
    private AdapterMedia adapterMedia;

    @Override
    public void play(String audioType, String fileName) {
        if(audioType.equalsIgnoreCase("mp3")){
            SMLog.i("Playing MP3 file: " + fileName);
        }else if(audioType.equalsIgnoreCase("vlc") || audioType.equalsIgnoreCase("mp4")){
            adapterMedia = new AdapterMedia(audioType);
            adapterMedia.play(audioType, fileName);
        }else{
            SMLog.i(fileName+ " is NOT support by " + audioType + " format file.");
        }
    }
}