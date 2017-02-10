package sample.hawk.com.mybasicappcomponents.data_structure.Json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/*
Json Data Format:

{
    "id":"859536b0-cf15-4b46-943a-6e6238a947c4",
    "desc":"Debug Test",
    "app":{
        "pkg":"com.hawk.test",
        "name":"Hello World"
        },
    "ads":[
        {
            "vendor":{
                "id":"03cc8b8e-63ad-4696-b2c9-36520090b7d2",
                "name":"Debug AppX"
            },
            "type":"banner",
            "placement":"Debug AppX",
            "client_handler":"com.hawk.test.module.handler",
            "max":100,
            "positions":[
                "6",
                "16",
                "31",
                "41",
                "51",
                "61"
            ],
            "extras":[
                {
                    "key":"ad_unit_id",
                    "value":"a3f6e8a1f2ed437b95ef9adbf174fb24"
                }
            ]
        }
    ],
    "ts":1458823555813
}
*/

public class AdItem {
    private String type;
    private String placement;
    private int max;
    private String [] positions;
    private AdSubItem vendor;
    private String clientHandler;
    private Map<String, String> extra = new HashMap<String, String>();

    public static String TYPE_BANNER = "banner";
    public static String TYPE_VIDEO = "video";
    public static String TYPE_NATIVE = "native";

    public String getClientHandler() {
        return clientHandler;
    }
    public void setClientHandler(String clientHandler) {
        this.clientHandler = clientHandler;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getPlacement() {
        return placement;
    }
    public void setPlacement(String placement) {
        this.placement = placement;
    }
    public int getMax() {
        return max;
    }
    public void setMax(int max) {
        this.max = max;
    }
    public String[] getPositions() {
        return positions;
    }
    public void setPositions(String[] positions) {
        this.positions = positions;
    }

    public AdSubItem getVendor() {
        return vendor;
    }
    public void setVendor(AdSubItem vendor) {
        this.vendor = vendor;
    }
    public String getExtra(String key) {
        if(extra.containsKey(key))
            return extra.get(key);
        else return null;
    }

    public void putExtra(String key, String value) {
        if(key != null && value != null)
            extra.put(key, value);
    }

    public static AdItem parse(JSONObject jo) {
        AdItem ac = new AdItem();
        try {
            if(jo.has("vendor") && !jo.isNull("vendor")) {
                ac.setVendor(AdSubItem.parse(jo.getJSONObject("vendor")));
            }
            if(jo.has("type") && !jo.isNull("type"))
                ac.setType(jo.getString("type"));
            if(jo.has("placement") && !jo.isNull("placement"))
                ac.setPlacement(jo.getString("placement"));
            if(jo.has("client_handler") && !jo.isNull("client_handler"))
                ac.setClientHandler(jo.getString("client_handler"));
            if(jo.has("max") && !jo.isNull("max"))
                ac.setMax(jo.getInt("max"));
            if(jo.has("positions") && !jo.isNull("positions")) {
                JSONArray ar = jo.getJSONArray("positions");
                String [] positions = new String[ar.length()];
                for(int index = 0; index < ar.length(); index ++) {
                    positions[index] = ar.getString(index);
                }
                ac.setPositions(positions);
            }

            if(jo.has("extras") && !jo.isNull("extras")) {
                JSONArray ar = jo.getJSONArray("extras");
                for(int index = 0; index < ar.length(); index ++) {
                    JSONObject joo = ar.getJSONObject(index);
                    ac.putExtra(joo.getString("key"), joo.getString("value"));
                }
            }
            return ac;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }
}
