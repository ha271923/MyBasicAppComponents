package sample.hawk.com.mybasicappcomponents.provider;


import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;

import sample.hawk.com.mybasicappcomponents.utils.SMLog;

import static sample.hawk.com.mybasicappcomponents.provider.ContentObserverActivity.MSG_OUTBOXCONTENT;

/**
 * Permission: android.permission.READ_SMS is required!!
 *
 * It will monitor any SMS sent by you. if no permission will get an java.lang.SecurityException: Permission Denial.
 *
 */


//用來觀察系統里短消息的數據庫變化 ”表“內容觀察者,只要信息數據庫發生變化，都會觸發該ContentObserver 派生類
public class  SMSContentObserver  extends  ContentObserver {
    private static  String TAG =  "SMSContentObserver";
    private  Context mContext;
    private  Handler mHandler;    //更新UI線程

    public  SMSContentObserver(Context context,Handler handler) {
        super (handler);
        mContext = context;
        mHandler = handler;
    }
    /**
     * 當所監聽的Uri發生改變時，就會回調此方法
     *
     * @param selfChange 此值意義不大 一般情況下該回調值false
     */
    @Override
    public void  onChange( boolean  selfChange){
        SMLog.i("-------------the SMS table has changed-------------" );
        //查詢發件箱裡的內容
        Uri outSMSUri = Uri.parse( "content://sms/sent" ) ;
        Cursor c = mContext.getContentResolver().query(outSMSUri,null,null,null,"date desc");
        if (c !=  null ){
            SMLog.i("the number of send is" +c.getCount());
            StringBuilder sb =  new  StringBuilder();
            //循環遍歷
            while (c.moveToNext()){
// sb.append("發件人手機號碼: "+c.getInt(c.getColumnIndex("address")))
// .append("信息內容: "+c.getInt(c.getColumnIndex("body")))
// .append("是否查看: "+c.getInt(c.getColumnIndex("read")))
// .append("發送時間： "+c.getInt(c.getColumnIndex("date")))
// .append("\n");
                sb.append( "發件人手機號碼: " +c.getInt(c.getColumnIndex( "address" )))
                        .append( "信息內容: " +c.getString(c.getColumnIndex( "body" )))
                        .append( "\n" );
            }
            c.close();
            SMLog.i(sb.toString());
            mHandler.obtainMessage(MSG_OUTBOXCONTENT, sb.toString()).sendToTarget();
        }
    }

}