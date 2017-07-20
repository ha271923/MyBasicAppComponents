package sample.hawk.com.mybasicappcomponents.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/7/20.
 */

public class MyDialogFragmentDialog extends DialogFragment {
    MyDialogFragActivity mDialogFragActivity;
    MyDialogFragment mDialogFragment;

    public static MyDialogFragmentDialog newInstance(int dialog_id) { // for pass id var, we create this API instead of the constructor.
        MyDialogFragmentDialog frag = new MyDialogFragmentDialog();
        Bundle args = new Bundle();
        args.putInt("dialog_id", dialog_id);
        // args.putString("selectedPrefKey", selectedPrefKey);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        Dialog dialog=null;
        int dialog_id = getArguments().getInt("dialog_id");
        mDialogFragActivity = (MyDialogFragActivity)getActivity();
        mDialogFragment = mDialogFragActivity.mMyDialogFragment;
        SMLog.i("dialog_id="+dialog_id);
        switch (dialog_id) {
            case 1:
                dialog = createNoticeDialog(mDialogFragActivity, "1");
                break;
            case 2:
                dialog = createNoticeDialog(mDialogFragActivity, "2");
                break;
            case 3:
                dialog = createNoticeDialog(mDialogFragActivity, "3");
                break;
            case 4:
                dialog = createNoticeDialog(mDialogFragActivity, "4");
                break;
        }
        return dialog;
    }

    private Dialog createNoticeDialog(final Activity activity, String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Item "+title)
                .setMessage("Notice Message")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setCancelable(false)
                .setPositiveButton("Understand!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SMLog.i( "PositiveButton");
                        Toast.makeText(activity, "you accept!!", Toast.LENGTH_SHORT).show();
                    }
                });
        return builder.create();
    }

}
