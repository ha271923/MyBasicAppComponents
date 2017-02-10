package sample.hawk.com.mybasicappcomponents.data_structure.Json;


//{
//	"id": "1f9825ff-3004-47a2-a68a-9e134bc0b5f6",
//	"ts": 139399939292,
//	"desc": "AD 1",
//	"app": {
//		"pkg": "my.test.app",
//		"name": "My Test App"
//	},
//	"ads": [{
//		"vendor": {
//			"id": "2c20e3ad-a225-4ee9-8c8d-19052f2a8fe3",
//			"name": "vendor-name-1"
//		},
//		"type": "banner",
//		"placement": "9e5dcd15-2860-4b5a-8536-1e8e58490966",
//		"max": 10,
//		"position": [
//			"6",
//			"8"
//		],
//		"extra": [{
//			"key": "posid",
//			"value": "1264100"
//		}, {
//			"key": "appid",
//			"value": "1264"
//		}, {
//			"key": "handler",
//			"value": "com.hawk.sample.CMAdCustomEventNative"
//		}]
//	},
//  {
//		"vendor": {
//			"id": "2c20e3ad-a225-4ee9-8c8d-19052f2a8fe3",
//			"name": "vendor-name-1"
//		},
//		"type": "video",
//		"placement": "9b0c5bd9-fd57-4c50-9fa3-e0eaaf4958b0",
//		"max": 15,
//		"position": [
//			"1"
//		],
//		"extra": [{
//			"key": "posid",
//			"value": "1264100"
//		}, {
//			"key": "appid",
//			"value": "1264"
//		}, {
//			"key": "handler",
//			"value": "com.hawk.sample.CMAdCustomEventNative"
//		}]
//	}]
//}

import org.json.JSONArray;
import org.json.JSONObject;

public class AdConfig {
    private String id;
    private String desc;
    private long ts;
    private AdApp app;
    private AdItem [] adItems;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public AdApp getApp() {
        return app;
    }
    public void setApp(AdApp app) {
        this.app = app;
    }

    public long getTs() {
        return ts;
    }
    public void setTs(long ts) {
        this.ts = ts;
    }



    public AdItem[] getAdItems() {
        return adItems;
    }
    public void setAdItems(AdItem[] adItems) {
        this.adItems = adItems;
    }
    public static AdConfig parse(JSONObject jo) {
        AdConfig ac = new AdConfig();
        try {
            if(jo.has("id") && !jo.isNull("id"))
                ac.setId(jo.getString("id"));
            if(jo.has("desc") && !jo.isNull("desc"))
                ac.setDesc(jo.getString("desc"));
            if(jo.has("ts") && !jo.isNull("ts"))
                ac.setTs(jo.getLong("ts"));
            if(jo.has("app") && !jo.isNull("app"))
                ac.setApp(AdApp.parse(jo.getJSONObject("app")));
            if(jo.has("ads") && !jo.isNull("ads")) {
                JSONArray joa = jo.getJSONArray("ads");
                AdItem [] adItems = new AdItem [joa.length()];
                for(int index = 0; index < joa.length(); index++ ) {
                    adItems[index] = AdItem.parse(joa.getJSONObject(index));
                }
                ac.setAdItems(adItems);
            }
            return ac;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }


}
