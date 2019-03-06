package sample.hawk.kotlinlibrary;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class KotlinVSJavaActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kotlinvsjava_activity);
    }
}