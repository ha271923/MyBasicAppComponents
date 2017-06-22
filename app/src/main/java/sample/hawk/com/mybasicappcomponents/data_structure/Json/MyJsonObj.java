package sample.hawk.com.mybasicappcomponents.data_structure.Json;

import org.json.JSONException;
import org.json.JSONObject;

import sample.hawk.com.mybasicappcomponents.data_structure.basic_ForSingleThread.AccessIF;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/2/9.
 */

public class MyJsonObj implements AccessIF {
    // private static final String s_DebugConfig = "{\"id\":\"859536b0-cf15-4b46-943a-6e6238a947c4\",\"desc\":\"Debug Ads (using MoPub SSP test line items)\",\"app\":{\"pkg\":\"com.hawk.sample\",\"name\":\"Hawk Sense\"},\"ads\":[{\"vendor\":{\"id\":\"03cc8b8e-63ad-4696-b2c9-36520090b7d2\",\"name\":\"Debug Mopub\"},\"type\":\"banner\",\"placement\":\"Debug Mopub\",\"client_handler\":\"com.htc.launcher.feeds.ad.htcadadapter.customnative.MoPubSSPCustomEventNative\",\"max\":100,\"positions\":[\"6\",\"16\",\"31\",\"41\",\"51\",\"61\"],\"extras\":[{\"key\":\"ad_unit_id\",\"value\":\"a3f6e8a1f2ed437b95ef9adbf174fb24\"}],\"ad_refresh_time\":30,\"ad_refresh_position\":[\"6\",\"31\"],\"ad_refresh_reload\":1}],\"ts\":1458823555813}";
    private static final String JsonData = "{\"id\":\"859536b0-cf15-4b46-943a-6e6238a947c4\",\"desc\":\"Debug Ads (using MoPub SSP test line items)\",\"app\":{\"pkg\":\"com.hawk.sample\",\"name\":\"Hawk Sense\"},\"ads\":[{\"vendor\":{\"id\":\"03cc8b8e-63ad-4696-b2c9-36520090b7d2\",\"name\":\"Debug Mopub\"},\"type\":\"banner\",\"placement\":\"Debug Mopub\",\"client_handler\":\"com.htc.launcher.feeds.ad.htcadadapter.customnative.MoPubSSPCustomEventNative\",\"max\":100,\"positions\":[\"6\",\"16\",\"31\",\"41\",\"51\",\"61\"],\"extras\":[{\"key\":\"ad_unit_id\",\"value\":\"a3f6e8a1f2ed437b95ef9adbf174fb24\"}]}],\"ts\":1458823555813}";
    private AdConfig mAdConfig;

    public MyJsonObj(){
        mAdConfig = new AdConfig();
        try{
            mAdConfig = mAdConfig.parse(new JSONObject(JsonData));
        }
        catch(JSONException e){
            SMLog.e("ERROR: Parse JSONObject failed!");
        }
    }

    @Override
    public void use_case() {

    }

    @Override
    public void show() {
        if(mAdConfig!=null){
            SMLog.i("mAdConfig.AppName = " + mAdConfig.getApp().getName());

            AdItem[] adItems = mAdConfig.getAdItems();
            for(int i=0; i< adItems.length; i++){
                SMLog.i("adItem["+i+"]=" + adItems[i].getPlacement());
            }
        }
    }

    @Override
    public void show_by_forloop() {

    }

    @Override
    public void show_by_foreach() {

    }

    @Override
    public void show_by_iterator() {

    }


}
