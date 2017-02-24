package sample.hawk.com.mybasicappcomponents.data_structure.Serial_Json_Parcel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ha271 on 2017/2/24.
 */

public class MyParcel_Book implements Parcelable {
    private String title ;
    private MyParcel_Author author ;
    // setter & getter
    public void setTitle(String arg){ this.title = arg; }
    public String getTitle(){ return this.title; }
    public void setAuthor(MyParcel_Author arg){ this.author = arg; }
    public MyParcel_Author getAuthor(){ return this.author; }
    public MyParcel_Book(){
        this.author = new MyParcel_Author();
    }
    @Override
    public int  describeContents () {
        return 0 ;
    }

    @Override
    public void writeToParcel (Parcel dest, int flags){
        dest.writeString(this.title);
        dest.writeParcelable(this.author,flags);
    }
    public static final Creator<MyParcel_Book> CREATOR = new Creator<MyParcel_Book>(){
        @Override
        public MyParcel_Book createFromParcel(Parcel source){
            // 必須按成員變量聲明的順序讀取數據，不然會出現獲取數據出錯
            MyParcel_Book book = new MyParcel_Book();
            book.setTitle(source.readString());
            book.setAuthor(source.<MyParcel_Author> readParcelable(MyParcel_Author.class.getClassLoader()));
            return book ;
        }
        @Override
        public MyParcel_Book[] newArray (int size){
            return new MyParcel_Book[0];
        }
    };
}
