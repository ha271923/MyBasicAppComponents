package sample.hawk.com.mybasicappcomponents.data_structure.Serial_Json_Parcel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

import com.google.gson.Gson;

import java.io.Serializable;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.data_structure.Json.MyJsonObj;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/10/3.
 */

public class Serial_Json_Parcel_Activity extends Activity {
    Context mContext;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.serial_json_parcel_activity);
    }

    public void onClick_Buttons(View view){
        ButtonActions(Integer.parseInt(view.getTag().toString()));
    }

    private void ButtonActions(int action){
        switch(action){
            case 70001: // Serializable
                MySerializable myserial = new MySerializable();
                myserial.mBook.setTitle("Java編程思想");
                myserial.mBook.getAuthor().setId(7777);
                myserial.mBook.getAuthor().setName("Hawk Wei");
                intent = new Intent(mContext,Serial_Json_Parcel_Activity2.class);
                intent.putExtra("from","MySerializable");
                intent.putExtra("book",(Serializable)myserial.mBook);
                mContext.startActivity(intent);
                break;

            case 70002: // Json
                MyJson myjson = new MyJson();
                myjson.mBook.setTitle("Java編程思想");
                myjson.mBook.getAuthor().setId(1);
                myjson.mBook.getAuthor().setName("Hawk Wei");
                myjson.mBook.setAuthor(myjson.mBook.getAuthor());
                intent = new Intent(mContext,Serial_Json_Parcel_Activity2.class);
                intent.putExtra("from","MyJson");
                intent.putExtra("book",new Gson().toJson(myjson.mBook));
                mContext.startActivity(intent);
                break;
            case 70003: // Parcel
                MyParcel_Book book = new MyParcel_Book();
                book.setTitle("Java編程思想");
                MyParcel_Author author = new MyParcel_Author();
                author.setId(9999);
                author.setName("Hawk Wei");
                book.setAuthor(author);
                intent = new Intent(mContext,Serial_Json_Parcel_Activity2.class);
                intent.putExtra("from","MyParcel");
                intent.putExtra("book",(Parcelable)book);
                mContext.startActivity(intent);

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

            case 70012: // JSON data
                MyJsonObj myjsonObj = new MyJsonObj();
                myjsonObj.show();
                break;
            default:
                SMLog.e("Not support this action yet");
        }
    }

}
