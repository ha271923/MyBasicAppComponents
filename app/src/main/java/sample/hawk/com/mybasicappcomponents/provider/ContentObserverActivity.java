package sample.hawk.com.mybasicappcomponents.provider;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.widget.EditText;
import android.widget.TextView;

import sample.hawk.com.mybasicappcomponents.R;

public class ContentObserverActivity extends Activity {
    private  TextView tvAirplane;
    private  TextView tvWiFi_status;
    private  EditText etSmsoutbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.mycontentobserveractivity);
        tvAirplane = (TextView) findViewById(R.id.tvAirplane);
        tvWiFi_status = (TextView) findViewById(R.id.textView);
        etSmsoutbox = (EditText) findViewById(R.id.editText);

        airplaneCO =  new  AirplaneContentObserver(this,mHandler);
        WiFiObserver = new WiFiContentObserver(this,mHandler);
        SmSObserver = new SMSContentObserver(this,mHandler);
        registerContentObservers() ;
    }
    private void  registerContentObservers() {

        Uri airplaneUri = Settings.System.getUriFor(android.provider.Settings.Global.AIRPLANE_MODE_ON);
        getContentResolver().registerContentObserver(airplaneUri,false,airplaneCO);

        // 通過調用getUriFor 方法獲得 system表裡的"WiFi"所在行的Uri
        Uri WifiUri = Settings.System.getUriFor(android.provider.Settings.Global.WIFI_ON);
        getContentResolver().registerContentObserver(WifiUri,false,WiFiObserver); // Hawk: it's failed to work.
        // ”表“內容觀察者 ，通過測試我發現只能監聽此Uri -----> content://sms
        // 監聽不到其他的Uri 比如說 content://sms/outbox
        Uri smsUri = Uri.parse( "content://sms" );
        getContentResolver().registerContentObserver(smsUri,true,SmSObserver);
    }
    public static final int MSG_AIRPLANE =  1 ;
    public static final int MSG_WIFI_CHANGED =  2;
    public static final int MSG_OUTBOXCONTENT =  3;
    private  AirplaneContentObserver airplaneCO;
    private WiFiContentObserver WiFiObserver;
    private SMSContentObserver SmSObserver;

    private Handler mHandler =  new  Handler() {
        public void  handleMessage(Message msg) {
            System.out.println( "---mHanlder----" );
            switch  (msg.what) {
                case  MSG_AIRPLANE:
                    int  isAirplaneOpen = (Integer) msg.obj;
                    if  (isAirplaneOpen !=  0 )
                        tvAirplane.setText( "飛行模式已打開" );
                    else if  (isAirplaneOpen ==  0 )
                        tvAirplane.setText( "飛行模式已關閉" );
                    break ;
                case MSG_WIFI_CHANGED:
                    int  isWiFiOn = (Integer) msg.obj;
                    if  (isWiFiOn !=  0 )
                        tvWiFi_status.setText( "WiFi ON" );
                    else if  (isWiFiOn ==  0 )
                        tvWiFi_status.setText( "WiFi OFF" );
                    break ;
                case  MSG_OUTBOXCONTENT:
                    String outbox = (String) msg.obj;
                    etSmsoutbox.setText(outbox);
                    break ;
                default :
                    break ;
            }
        }
    };

    @Override
    protected void onDestroy() {
        UnregisterContentObservers();
        super.onDestroy();
    }

    void UnregisterContentObservers(){
        getContentResolver().unregisterContentObserver(airplaneCO);
        getContentResolver().unregisterContentObserver(WiFiObserver);
        getContentResolver().unregisterContentObserver(SmSObserver);
    }

}
