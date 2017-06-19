package sample.hawk.com.mybasicappcomponents.view.game;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import static sample.hawk.com.mybasicappcomponents.utils.Util.hideSystemUI;


/**
 * Android Game Sample from:
 *  https://github.com/cobr123/androidGameTutor
 * */
public class GameActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        hideSystemUI(true, this);
        setContentView(new GameView(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideSystemUI(false, this);
    }

}