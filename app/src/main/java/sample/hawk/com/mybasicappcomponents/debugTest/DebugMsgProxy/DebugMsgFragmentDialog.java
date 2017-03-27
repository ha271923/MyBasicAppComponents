package sample.hawk.com.mybasicappcomponents.debugTest.DebugMsgProxy;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import sample.hawk.com.mybasicappcomponents.R;

import static android.app.Activity.RESULT_OK;

/**
 * Created by ha271 on 2017/3/23.
 */

public class DebugMsgFragmentDialog extends DialogFragment
{
    AlertDialog.Builder builder;
    DebugMsgActivity debugMsgActivity;
    DebugMsgFragment debugMsgFragment;

    //    public SharedPreferences mSharedPref;
    public Dialog dialog;

    public static DebugMsgFragmentDialog newInstance(int dialog_id) { // for pass id var, we create this API instead of the constructor.
        DebugMsgFragmentDialog frag = new DebugMsgFragmentDialog();
        Bundle args = new Bundle();
        args.putInt("dialog_id", dialog_id);
        // args.putString("selectedPrefKey", selectedPrefKey);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1:
                    startActivityForResult(intent, 2);
                    break;
                default:
            }
        }
        super.onActivityResult(requestCode, resultCode, intent);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        Dialog ret=null;
        int dialog_id = getArguments().getInt("dialog_id");
        String selectedPrefKey = getArguments().getString("selectedPrefKey");
        ListView listView;
        debugMsgActivity = (DebugMsgActivity)getActivity();
        debugMsgFragment = debugMsgActivity.mDebugMsgFragment;

        switch (dialog_id) {
            case 0: // Hawk: add this dialog for alert.
                builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("APP can't work without this permission. Please enable it.")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Permission is disabled.")
                        .setCancelable(false)
                        .setPositiveButton(("Enable it!"), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        debugMsgActivity.getDrawPermission(debugMsgActivity);
                                    }
                                }
                        )
                        .setNegativeButton(("Leave APP"), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        getActivity().finish();
                                    }
                                }
                        );
                ret = builder.create();
                break;
            case 3: // Transparent notification icon\CheckBox=V
                builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Only on Android 4.1+ devices, it is possible to hide the icon in notification pull-down, by unchecking \\\"show notification\\\" for swipe home button in Application Manager. Otherwise, the icon is required to keep swipes working.")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Notice:")
                        .setPositiveButton("I understand", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                dialogInterface.dismiss();
                            }
                        });
                ret = builder.create();
                break;

            case 7: // Advanced settings\Detection area height\<Sliding bar> dialog
                View view;
                view = View.inflate(getActivity(), R.layout.detect_area_height, null);
                SeekBar seekBar = (SeekBar) view.findViewById(R.id.detect_area_seekbar);
                TextView textView = (TextView) view.findViewById(R.id.tvSeekBar);
                DetectArea detectArea = new DetectArea();
                // Hawk: remark for build PASS
                // detectArea.DetectAreaHeightProgressToDP((SettingsActivity)activity.mSharedPref.getInt("prefDetectAreaHeight", 2), (Context) activity);
                detectArea.DetectAreaHeightProgressToDP((PreferenceManager.getDefaultSharedPreferences(getActivity()).getInt("prefDetectAreaHeight", 2)), (Context) debugMsgActivity);
                seekBar.setProgress(detectArea.DetectAreaProgress);
                textView.setText(detectArea.strAreaHeight);
                seekBar.setOnSeekBarChangeListener(new DetectAreaSeekBar_Listener(debugMsgActivity, detectArea, textView));
                builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Higher height blocks more space. Lower height lowers sensitivity and needs firm and slow swipes.")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Notice:").setView(view)
                        .setPositiveButton("Okay!",
                                new DetectAreaConfirmBtnClick_Listener(debugMsgActivity, detectArea));
                ret = builder.create();
                break;

            case 8: // Advanced settings\Detection area transparent\<Sliding bar> dialog
                View view8;
                view8 = View.inflate(getActivity(), R.layout.detect_area_transparent, null);
                SeekBar seekBar8 = (SeekBar) view8.findViewById(R.id.transparent_detect_area_seekbar);
                TextView textView8 = (TextView) view8.findViewById(R.id.tvSeekBar);
                DetectArea detectArea8 = new DetectArea();
                detectArea8.DetectAreaTransparent = (PreferenceManager.getDefaultSharedPreferences(getActivity()).getInt("prefDetectAreaTransparent", 2));
                seekBar8.setProgress(detectArea8.DetectAreaTransparent);
                textView8.setText(detectArea8.DetectAreaTransparent+" %");
                seekBar8.setOnSeekBarChangeListener(new DetectAreaTransparentSeekBar_Listener(debugMsgActivity, detectArea8, textView8));
                builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("R.string.dialog_areatransparent_notice")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("R.string.transparent_dialog_title")
                        .setView(view8).setPositiveButton("Okay!",
                                new DetectAreaTransparentBtnClick_Listener(debugMsgActivity, detectArea8));
                ret = builder.create();
                break;

        }
        return ret;

    }

    public  class DetectAreaSeekBar_Listener implements SeekBar.OnSeekBarChangeListener {
        private DebugMsgActivity mDebugMsgActivity;
        private final DetectArea detectArea;
        private final TextView tvBarHeight;

        public DetectAreaSeekBar_Listener(DebugMsgActivity settingsActivity, DetectArea detectArea, TextView textView) {
            this.mDebugMsgActivity = settingsActivity;
            this.detectArea = detectArea;
            this.tvBarHeight = textView;
        }

        public final void onProgressChanged(SeekBar seekBar, int progress, boolean z) {
            this.detectArea.DetectAreaHeightProgressToDP(progress, this.mDebugMsgActivity);
            this.tvBarHeight.setText(this.detectArea.strAreaHeight);
        }

        public final void onStartTrackingTouch(SeekBar seekBar) {
        }

        public final void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

    public  class DetectAreaConfirmBtnClick_Listener implements DialogInterface.OnClickListener {
        private DebugMsgActivity debugMsgActivity;
        private DebugMsgFragment debugMsgFragment;
        private final DetectArea detectArea;

        public DetectAreaConfirmBtnClick_Listener(DebugMsgActivity debugMsgActivity, DetectArea detectArea) {
            this.debugMsgActivity = debugMsgActivity;
            this.debugMsgFragment = debugMsgActivity.mDebugMsgFragment;
            this.detectArea = detectArea;
        }

        public final void onClick(DialogInterface dialogInterface, int i) {
            SharedPreferences.Editor edit = this.debugMsgFragment.mSharedPref.edit();
            edit.putInt("prefDetectAreaHeight", this.detectArea.DetectAreaProgress);
            edit.commit();
        }
    }

    public  class DetectAreaTransparentBtnClick_Listener implements DialogInterface.OnClickListener {
        private DebugMsgActivity debugMsgActivity;
        private DebugMsgFragment debugMsgFragment;
        private final DetectArea detectArea;

        public DetectAreaTransparentBtnClick_Listener(DebugMsgActivity debugMsgActivity, DetectArea detectArea) {
            this.debugMsgActivity = debugMsgActivity;
            this.debugMsgFragment = debugMsgActivity.mDebugMsgFragment;
            this.detectArea = detectArea;
        }

        public final void onClick(DialogInterface dialogInterface, int i) {
            SharedPreferences.Editor edit = this.debugMsgFragment.mSharedPref.edit();
            edit.putInt("prefDetectAreaTransparent", this.detectArea.DetectAreaTransparent);
            edit.commit();
        }
    }

    public  class DetectAreaTransparentSeekBar_Listener implements SeekBar.OnSeekBarChangeListener {
        private final DetectArea detectArea;
        private final TextView tvBarValue;

        public DetectAreaTransparentSeekBar_Listener(DebugMsgActivity debugMsgActivity, DetectArea detectArea, TextView textView) {
            this.tvBarValue = textView;
            this.detectArea = detectArea;
        }

        public final void onProgressChanged(SeekBar seekBar, int progress, boolean z) {
            this.tvBarValue.setText(String.valueOf(progress));
            detectArea.DetectAreaTransparent = progress;
        }

        public final void onStartTrackingTouch(SeekBar seekBar) {
        }

        public final void onStopTrackingTouch(SeekBar seekBar) {
        }

    }

}

