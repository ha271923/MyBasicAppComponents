package sample.hawk.com.mybasicappcomponents.misc;

import android.app.Activity;
import android.os.Bundle;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.FileUtils;

/**
 * Created by ha271 on 2017/3/7.
 */

public class FilePathActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filepathactivity);
        FileUtils fu = new FileUtils(this);

    }
}
