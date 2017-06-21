package sample.hawk.com.mybasicappcomponents.view.ListView;

/**
 * Created by ha271 on 2016/8/8.
 */

public class MyListRowDataItem {
    private int type;
    private int photoId;
    private String name;
    private String time;

    public MyListRowDataItem(int type, int photoId, String name, String time) {
        this.type = type;
        this.name = name;
        this.time = time;
        this.photoId = photoId;
    }
    // 想要傳入ListView的資料有:頻道名稱 電影名稱 電影時間
    // 希望可以很明顯看到每個list item長的並不是很相同,所以額外加了一個type的參數
    // 所以參數有 type , title(電影or電影台名稱),time(電影時間)
    //   type 0 = 這個item是電影台
    //   type 1 = 這個item是電影 + 顏色是白色
    //   type 2 = 這個item是電影 + 顏色是黃色
    public int    getType(){ return type; }
    public void   setType(int type){ this.type = type; }
    public int getPhotoId(){return photoId;}
    public void   setPhotoId(int photoId){ this.photoId = photoId; }
    public String getName(){ return name; }
    public void   setName(String name){ this.name = name; }
    public String getTime(){ return time; }
    public void setTime(String time){ this.time = time; }
}
