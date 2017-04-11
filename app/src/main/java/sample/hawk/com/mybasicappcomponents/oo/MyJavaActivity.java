package sample.hawk.com.mybasicappcomponents.oo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import java.io.Serializable;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.data_structure.Serial_Json_Parcel.MyJson;
import sample.hawk.com.mybasicappcomponents.data_structure.Serial_Json_Parcel.MyParcel_Author;
import sample.hawk.com.mybasicappcomponents.data_structure.Serial_Json_Parcel.MyParcel_Book;
import sample.hawk.com.mybasicappcomponents.data_structure.Serial_Json_Parcel.MySerializable;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2016/10/6.
 */

public class MyJavaActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myjavaactivity);

    }

    /* Test init Java class sequence Result:
10-06 14:32:06.450 23637-23637/sample.hawk.com.mybasicappcomponents I/[Hawk]: MyJavaClass() constructor +++
10-06 14:32:09.903 23637-23637/sample.hawk.com.mybasicappcomponents I/[Hawk]: ParentClass call <cinit> for all static variables
10-06 14:32:09.904 23637-23637/sample.hawk.com.mybasicappcomponents I/[Hawk]: instance ParentClass class static
10-06 14:32:13.860 23637-23637/sample.hawk.com.mybasicappcomponents I/[Hawk]: ChildClass call <cinit> for all static variables
10-06 14:32:13.860 23637-23637/sample.hawk.com.mybasicappcomponents I/[Hawk]: instance ChildClass===== class static
10-06 14:32:13.861 23637-23637/sample.hawk.com.mybasicappcomponents I/[Hawk]: instance ParentClass class
10-06 14:32:13.861 23637-23637/sample.hawk.com.mybasicappcomponents I/[Hawk]: into ParentClass constructor
10-06 14:32:13.862 23637-23637/sample.hawk.com.mybasicappcomponents I/[Hawk]: instance ChildClass===== class
10-06 14:32:13.862 23637-23637/sample.hawk.com.mybasicappcomponents I/[Hawk]: into DynamicObject1 constructor
10-06 14:32:13.863 23637-23637/sample.hawk.com.mybasicappcomponents I/[Hawk]: instance ParentClass class
10-06 14:32:13.863 23637-23637/sample.hawk.com.mybasicappcomponents I/[Hawk]: into ParentClass constructor
10-06 14:32:13.864 23637-23637/sample.hawk.com.mybasicappcomponents I/[Hawk]: instance DynamicObject1 class
10-06 14:32:13.864 23637-23637/sample.hawk.com.mybasicappcomponents I/[Hawk]: into DynamicObject2 constructor
10-06 14:32:13.865 23637-23637/sample.hawk.com.mybasicappcomponents I/[Hawk]: MyJavaClass() constructor ---
10-06 14:32:13.867 23637-23637/sample.hawk.com.mybasicappcomponents I/[Hawk]: call MyJavaClass::method_1()  API

    */
    public void onClick_MyJavaClass(View view){
        SMLog.e("JAVA class: "+((Button)view).getText());
        String Tag = view.getTag().toString();
        Intent intent;
        int tag = Integer.parseInt(Tag);
        switch(tag){
            case 1:
                MyJavaClass javaTest = new MyJavaClass(tag);
                javaTest.method_1();
                javaTest.cc_instanceof_keyword(new ChildClass("testObj"));
                javaTest.pc_instanceof_keyword(new ParentClass());
                break;
            case 7777: // Serializable
                MySerializable myserial = new MySerializable();
                myserial.mBook.setTitle("Java編程思想");
                myserial.mBook.getAuthor().setId(7777);
                myserial.mBook.getAuthor().setName("Hawk Wei");
                intent = new Intent(this,MyJavaActivity2.class);
                intent.putExtra("from","MySerializable");
                intent.putExtra("book",(Serializable)myserial.mBook);
                startActivity(intent);
                break;
            case 8888: // Json
                MyJson myjson = new MyJson();
                myjson.mBook.setTitle("Java編程思想");
                myjson.mBook.getAuthor().setId(1);
                myjson.mBook.getAuthor().setName("Hawk Wei");
                myjson.mBook.setAuthor(myjson.mBook.getAuthor());
                intent = new Intent(this,MyJavaActivity2.class);
                intent.putExtra("from","MyJson");
                intent.putExtra("book",new Gson().toJson(myjson.mBook));
                startActivity(intent);
                break;
            case 9999: // Parcel
                MyParcel_Book book = new MyParcel_Book();
                book.setTitle("Java編程思想");
                MyParcel_Author author = new MyParcel_Author();
                author.setId(9999);
                author.setName("Hawk Wei");
                book.setAuthor(author);
                intent = new Intent(this,MyJavaActivity2.class);
                intent.putExtra("from","MyParcel");
                intent.putExtra("book",(Parcelable)book);
                startActivity(intent);

                /* // BUG: this inner class parcel type will exception.
                MyParcel myparcel = new MyParcel();
                myparcel.mBook.setTitle("Java編程思想");
                myparcel.mBook.getAuthor().setId(9999);
                myparcel.mBook.getAuthor().setName("Hawk Wei");
                intent = new Intent(this,MyJavaActivity2.class);
                intent.putExtra("from","MyParcel");
                intent.putExtra("book",(Parcelable)myparcel.mBook);
                startActivity(intent);
                */
                break;

            default:
                new MyJavaClass(tag);
                break;
        }
    }




}
