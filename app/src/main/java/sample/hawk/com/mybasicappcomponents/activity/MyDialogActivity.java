package sample.hawk.com.mybasicappcomponents.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.AppItem;
import sample.hawk.com.mybasicappcomponents.utils.AppItemAdapter;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;
import sample.hawk.com.mybasicappcomponents.utils.Util;

/**
 * Created by ha271 on 2017/7/20.
 */

public class MyDialogActivity extends Activity {
    private Activity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mydialog_activity);
        mActivity = this;
    }


    public void onClick_dialog_buttons(View v){
        switch(v.getId()){
            case R.id.dialog_notice:
                createNoticeDialog(mActivity).show();
                break;
            case R.id.alertdialog_button:
                createAlertDialog(mActivity).show();
                break;
            case R.id.dialog_multisel_button:
                createMultiSelectionDialog(mActivity).show();
                break;
            case R.id.dialog_radio_button: // It's NOT created by AlertDialog
                createRadioButtonsDialog(mActivity).show();
                break;
            case R.id.dialog_seekbar:
                createSeekbarDialog(mActivity).show();
                break;
            case R.id.dialog_list_items:
                createListViewDialog(mActivity).show();
                break;
            case R.id.dialog_list_items2:
                createListViewByIntentDialog(mActivity);
                break;
        }
    }

    private AlertDialog createAlertDialog(Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(MyDialogActivity.this);
        builder.setTitle("Alert Title");
        builder.setMessage("Alert Message");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MyDialogActivity.this, "您按下OK按鈕", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MyDialogActivity.this, "您按下Cancel按鈕", Toast.LENGTH_SHORT).show();
            }
        });
        return builder.create();
    }

    private Dialog createNoticeDialog(final Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Notice title")
                .setMessage("Notice Message")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setCancelable(false)
                .setPositiveButton("Understand!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SMLog.i( "您按下 PositiveButton");
                        Toast.makeText(activity, "you accept!!", Toast.LENGTH_SHORT).show();
                    }
                });
        return builder.create();
    }

    private Dialog createRadioButtonsDialog(Activity activity) {
        // custom dialog
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.mydialog_radiolist);
        List<String> stringList=new ArrayList<>();  // here is list
        for(int i=0;i<5;i++) {
            stringList.add("RadioButton " + (i + 1));
        }
        RadioGroup rg = (RadioGroup) dialog.findViewById(R.id.radio_group);

        for(int i=0;i<stringList.size();i++){
            RadioButton rb=new RadioButton(activity); // dynamically creating RadioButton and adding to RadioGroup.
            rb.setText(stringList.get(i));
            rg.addView(rb);
        }
        return dialog;
    }

    boolean[] isChecked = {false,true,false,false,false,false,false};
    CharSequence[] days = {"星期一","星期二","星期三","星期四","星期五","星期六","星期日"};
    private Dialog createMultiSelectionDialog(final Activity activity) {
        return new AlertDialog.Builder(activity)
                .setTitle("重複日期")
                .setMultiChoiceItems(days, isChecked, onClick_MultiSelectItem)
                .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SMLog.i( "您按下 PositiveButton");
                        String result = "你選擇了:";
                        for(int i=0;i< days.length;i++){
                            if(isChecked[i]==true){
                                result = result+","+days[i];
                            }
                        }
                        //顯示選擇的星期
                        Toast.makeText(activity, result, Toast.LENGTH_SHORT).show();
                    }
                })
                .setNeutralButton("中立", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SMLog.i( "您按下 NeutralButton");
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SMLog.i( "您按下 NegativeButton");
                    }
                })
                .create();
    }
    private DialogInterface.OnMultiChoiceClickListener onClick_MultiSelectItem = new DialogInterface.OnMultiChoiceClickListener(){
        @Override
        public void onClick(DialogInterface dialog, int position, boolean checked) {
            //更新目前的選項checked status
            isChecked[position] = checked;
            SMLog.i( "item["+position+"]=   "+(checked?"勾選":"取消"));
        }
    };


    private Dialog createSeekbarDialog(Activity activity) {
        int deafault_bar_value = 0;
        AlertDialog.Builder builder;
        final View view = View.inflate(activity, R.layout.mydialog_seekbar, null);
        SeekBar seekBar1 = (SeekBar) view.findViewById(R.id.seekbar);
        TextView title3 = (TextView) view.findViewById(R.id.title3);
        seekBar1.setProgress(deafault_bar_value);
        title3.setText(Integer.toString(deafault_bar_value));
        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
           @Override
           public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               TextView textView = (TextView) view.findViewById(R.id.title3);
               //seekBar.setProgress(progress); // Don't need to control it by yourself.
               textView.setText(Integer.toString(progress));
           }

           @Override
           public void onStartTrackingTouch(SeekBar seekBar) {
               Toast.makeText(MyDialogActivity.this, "onStart TrackingTouch", Toast.LENGTH_SHORT).show();
           }

           @Override
           public void onStopTrackingTouch(SeekBar seekBar) {
               Toast.makeText(MyDialogActivity.this, "onStop TrackingTouch", Toast.LENGTH_SHORT).show();
           }
       }
        );
        builder = new AlertDialog.Builder(activity);
        builder.setMessage("Seekbar message")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Seekbar title").setView(view)
                .setPositiveButton("OK!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MyDialogActivity.this, "您按下OK按鈕", Toast.LENGTH_SHORT).show();
                    }
                });
        return builder.create();
    }


    private Dialog createListViewDialog(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("ListView title");
        ListView listView = new ListView(activity);
        ArrayList b = Util.GetAllInstalledAppsList(activity);
        listView.setAdapter(new AppItemAdapter(activity, listView.getId(), (AppItem[]) b.toArray(new AppItem[b.size()])));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SMLog.i( "onItemClick  parent="+parent+"  view="+view+"  position="+position+"  id="+id);
            }
        });
        builder.setView(listView);
        return builder.create();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1111: // STEP2: got by the user choice SHORTCUT for function result(ex: SpeedDial)
                    SMLog.i("STEP2: got by the user choice SHORTCUT for function result(ex: SpeedDial)");
                    SMLog.i(" // STEP3: wait for the user choice SHORTCUT function detail setting. (ex: SpeedDial for whom? )");
                    startActivityForResult(intent, 2222); // STEP3: wait for the user choice SHORTCUT function detail setting. (ex: SpeedDial for whom? )
                    break;
                case 2222: // STEP4: got by the user choice SHORTCUT function detail setting. (ex: SpeedDial to wife)
                    SMLog.i("STEP4: got by the user choice SHORTCUT function detail setting. (ex: SpeedDial to wife)");
                    Intent shortcut =intent.getParcelableExtra("android.intent.extra.shortcut.INTENT");
                    if(shortcut!=null){
                        CharSequence stringExtra = intent.getStringExtra("android.intent.extra.shortcut.NAME");
                        String toUri = shortcut.toUri(0);
                        SMLog.e("toUri="+toUri);
                        // TODO: you can save in preference
                    }
                    else{
                        SMLog.e("intent.getParcelableExtra return NULL");
                    }
                    break;
                default:
            }
        }
        super.onActivityResult(requestCode, resultCode, intent);
    }
    private void createListViewByIntentDialog(Activity activity) {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("android.intent.extra.shortcut.NAME", new ArrayList());
        bundle.putParcelableArrayList("android.intent.extra.shortcut.ICON_RESOURCE", new ArrayList());
        Intent intent = new Intent("android.intent.action.PICK_ACTIVITY");
        intent.putExtra("android.intent.extra.INTENT", new Intent("android.intent.action.CREATE_SHORTCUT"));
        intent.putExtra("android.intent.extra.TITLE", "SELECT SHORTCUT");
        intent.putExtras(bundle);
        SMLog.i("STEP1: wait for the user choice SHORTCUT for function result");
        startActivityForResult(intent, 1111); // STEP1: wait for the user choice SHORTCUT for function result(ex: SpeedDial/Send SMS/PlayMusic List)
    }

}
