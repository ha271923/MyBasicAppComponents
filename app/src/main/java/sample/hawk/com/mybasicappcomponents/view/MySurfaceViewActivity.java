package sample.hawk.com.mybasicappcomponents.view;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by ha271 on 2016/11/10.
 */

public class MySurfaceViewActivity extends Activity{
    @Override
    public void  onCreate(Bundle savedInstanceState)
    {
        super .onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        setContentView( new  MySurfaceView( this ));
    }
}
