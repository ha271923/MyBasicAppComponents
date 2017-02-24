package sample.hawk.com.mybasicappcomponents.data_structure.Serial_Json_Parcel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ha271 on 2017/2/24.
 */

public class MyParcel_Author implements Parcelable {
    private int id;
    private String name;
    // setter & getter
    public void setName(String arg) { this.name = arg; }
    public String getName() { return this.name; }
    public void setId(int arg) { this.id = arg; }
    public int getId() { return this.id; }

    @Override
    public int  describeContents(){
        return 0 ;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(this.name);
        dest.writeInt(this.id);
    }
    public static final Creator<MyParcel_Author> CREATOR = new Creator<MyParcel_Author>(){
        @Override
        public MyParcel_Author createFromParcel(Parcel source){
            // 必須按成員變量聲明的順序讀取數據，不然會出現獲取數據出錯
            MyParcel_Author author = new MyParcel_Author();
            author.setName(source.readString());
            author.setId(source.readInt());
            return author ;
        }
        @Override
        public MyParcel_Author[] newArray(int size){
            return new MyParcel_Author[size];
        }
    };
}
