package sample.hawk.com.mybasicappcomponents.data_structure.Serial_Json_Parcel;

import java.io.Serializable;

/**
 * Created by ha271 on 2017/2/24.
 */

public class MySerializable implements Serializable{
    public Book mBook;
    public MySerializable(){
        mBook = new Book();
    }
    public class Author implements Serializable {
        private int id;
        private String name;
        // setter & getter
        public void setName(String arg) { name = arg; }
        public String getName() { return name; }
        public void setId(int arg) { id = arg; }
        public int getId() { return id; }
    }
    public class Book implements Serializable {
        private String title;
        private Author author;
        private Book(){
            author = new Author();
        }
        // setter & getter
        public void setTitle(String arg){ title = arg; }
        public String getTitle(){ return title; }
        public void setAuthor(Author arg){ author = arg; }
        public Author getAuthor(){ return author; }
    }


}
