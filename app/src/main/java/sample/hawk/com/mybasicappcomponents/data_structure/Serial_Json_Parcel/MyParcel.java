package sample.hawk.com.mybasicappcomponents.data_structure.Serial_Json_Parcel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ha271 on 2017/2/24.
 */

public class MyParcel implements Parcelable {
    public Book mBook ;

    public MyParcel(){
        mBook = new Book();
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mBook,flags);
    }
    static public final Creator<MyParcel> CREATOR = new Parcelable.Creator<MyParcel>(){
        @Override
        public MyParcel createFromParcel(Parcel source){
            return new MyParcel();
        }
        @Override
        public MyParcel[] newArray(int size){
            return new MyParcel[0];
        }
    };

    static public class Author implements Parcelable {
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
        public void writeToParcel(Parcel dest,int flags){
            dest.writeString(this.name);
            dest.writeInt(this.id);
        }
        static public final Creator<Author> CREATOR = new Parcelable.Creator<Author>(){
            @Override
            public Author createFromParcel(Parcel source){
                // 必須按成員變量聲明的順序讀取數據，不然會出現獲取數據出錯
                Author author = new Author();
                author.setId(source.readInt());
                author.setName(source.readString());
                return  author ;
            }
            @Override
            public Author[] newArray(int size){
                return new Author[size];
            }
        };
    }

    static public class Book implements Parcelable {
        private String title ;
        private Author author ;
        // setter & getter
        public void setTitle(String arg){ this.title = arg; }
        public String getTitle(){ return this.title; }
        public void setAuthor(Author arg){ this.author = arg; }
        public Author getAuthor(){ return this.author; }
        private Book(){
            this.author = new Author();
        }
        @Override
        public int  describeContents () {
            return 0 ;
        }

        @Override
        public void writeToParcel (Parcel dest,int flags){
            dest.writeString(this.title);
            dest.writeParcelable(this.author,flags);
        }
        static public final Creator<Book> CREATOR = new Creator<Book>(){
            @Override
            public Book createFromParcel(Parcel source){
                // 必須按成員變量聲明的順序讀取數據，不然會出現獲取數據出錯
                Book book = new Book();
                book.setTitle(source.readString());
                book.setAuthor(source.<Author> readParcelable(Author.class.getClassLoader()));
                return book ;
            }
            @Override
            public Book[] newArray (int size){
                return new Book[0];
            }
        };
    }



}
