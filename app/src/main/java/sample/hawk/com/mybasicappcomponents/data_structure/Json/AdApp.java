package sample.hawk.com.mybasicappcomponents.data_structure.Json;


import org.json.JSONObject;

public class AdApp {
	private String pkg;
	private String name;
	
	public String getPkg() {
		return pkg;
	}
	public void setPkg(String pkg) {
		this.pkg = pkg;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public static AdApp parse(JSONObject jo) {
		AdApp ac = new AdApp();
		
		try {
			if(jo.has("pkg") && !jo.isNull("pkg"))
				ac.setPkg(jo.getString("pkg"));
			if(jo.has("name") && !jo.isNull("name"))
				ac.setName(jo.getString("name"));
			
			return ac;
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}
}
