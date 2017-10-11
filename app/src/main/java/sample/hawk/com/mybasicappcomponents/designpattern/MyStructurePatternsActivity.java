package sample.hawk.com.mybasicappcomponents.designpattern;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.designpattern.adapter.ClassAdapter.cAdapterMedia;
import sample.hawk.com.mybasicappcomponents.designpattern.adapter.ClassAdapter.cPlayerAudio;
import sample.hawk.com.mybasicappcomponents.designpattern.adapter.ObjectAdapter.AdapterMedia;
import sample.hawk.com.mybasicappcomponents.designpattern.adapter.ObjectAdapter.PlayerAudio;
import sample.hawk.com.mybasicappcomponents.designpattern.adapter.ObjectAdapter.PlayerMp4;
import sample.hawk.com.mybasicappcomponents.designpattern.adapter.ObjectAdapter.PlayerVlc;
import sample.hawk.com.mybasicappcomponents.designpattern.proxy.Client;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/10/6.
 */

public class MyStructurePatternsActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mydesignpatterns_structure);

    }

    public void onClick_MyStructurePatternsClass(View view){
        MyDesignPattern(Integer.parseInt(view.getTag().toString()));
    }

    private void MyDesignPattern(int pattern_type){
        switch(pattern_type){
            case 10: // Object Adapter
                PlayerAudio audioPlayer = new PlayerAudio();
                audioPlayer.play("mp3", "beyond the horizon.mp3");
                audioPlayer.play("mp4", "alone.mp4");
                audioPlayer.play("vlc", "far far away.vlc");
                audioPlayer.play("avi", "mind me.avi");

                AdapterMedia adapterMedia = new AdapterMedia("vlc");
                adapterMedia.play("vlc", "far far away.vlc");

                PlayerMp4 mp4Player = new PlayerMp4();
                mp4Player.playMp4("alone.mp4");
                mp4Player.playVlc("far far away.vlc");

                PlayerVlc vlcPlayer = new PlayerVlc();
                vlcPlayer.playMp4("alone.mp4");
                vlcPlayer.playVlc("far far away.vlc");

                break;
            case 11: // Class Adapter
                cPlayerAudio audioPlayer2 = new cPlayerAudio();
                audioPlayer2.play("mp3", "beyond the horizon.mp3");
                audioPlayer2.play("mp4", "alone.mp4");
                audioPlayer2.play("vlc", "far far away.vlc");
                audioPlayer2.play("avi", "mind me.avi");

                cAdapterMedia adapterMedia2 = new cAdapterMedia("vlc");
                adapterMedia2.play("vlc", "far far away.vlc");

                break;
            case 20: // Bridge

                break;
            case 30: // Composite

                break;
            case 40: // Decorator

                break;
            case 50: // Facade

                break;
            case 60: // Flyweight

                break;
            case 70: // Proxy
                Client client = new Client();
                client.show();
                break;
            default:
                SMLog.e("Not support this pattern yet");
        }
    }

}
