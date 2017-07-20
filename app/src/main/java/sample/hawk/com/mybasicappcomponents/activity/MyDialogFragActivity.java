package sample.hawk.com.mybasicappcomponents.activity;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by ha271 on 2017/7/20.
 */

public class MyDialogFragActivity extends Activity {
    public MyDialogFragment mMyDialogFragment;
    public DialogFragment   mDialogFragment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMyDialogFragment = new MyDialogFragment();
        getFragmentManager().beginTransaction().replace(android.R.id.content, mMyDialogFragment).commit();
    }

    public void showMyDialog(int i) {
        mDialogFragment = MyDialogFragmentDialog.newInstance(i);
        mDialogFragment.show(getFragmentManager(), "dialog");
    }
}
