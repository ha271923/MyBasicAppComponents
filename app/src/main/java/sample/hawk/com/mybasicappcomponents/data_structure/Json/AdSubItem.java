package sample.hawk.com.mybasicappcomponents.data_structure.Json;

import org.json.JSONObject;

/*
 "vendor": {
     "id": "0ec4fccd-2d5a-403f-b690-412f3d746212",
     "name": "vendor-name-2"
 },

*/

public class AdSubItem {
    private String id;
    private String name;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public static AdSubItem parse(JSONObject jo) {
        AdSubItem ac = new AdSubItem();

        try {
            if(jo.has("id") && !jo.isNull("id"))
                ac.setId(jo.getString("id"));
            if(jo.has("name") && !jo.isNull("name"))
                ac.setName(jo.getString("name"));

            return ac;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }


}

