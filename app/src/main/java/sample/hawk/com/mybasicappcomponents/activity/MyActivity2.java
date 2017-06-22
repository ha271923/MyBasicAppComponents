package sample.hawk.com.mybasicappcomponents.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import sample.hawk.com.mybasicappcomponents.data_structure.Serial_Json_Parcel.MyParcel_Book;
import sample.hawk.com.mybasicappcomponents.data_structure.Serial_Json_Parcel.MySerializable;
import sample.hawk.com.mybasicappcomponents.data_structure.Serial_Json_Parcel.Student;

/**
 * Created by ha271 on 2017/1/6.
 *
 * IPS Performance Compare:
 *   Parcel > Serializable > Json
 *
 *   Parcel is Android only for IPC easily.
 *   Serializable is Java classic for normal serialize data.
 *   Json was developed for SQL, DB, Web.
 */

public class MyActivity2 extends Activity {

    String mFrom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView showText = new TextView(this);

        mFrom = getIntent().getStringExtra("from");

        if(mFrom.equals("startActivity")||mFrom.equals("startActivityForResult")) {
            Bundle argument = getIntent().getExtras(); //從 Intent 取出包裹
            String valueStr = argument.getString("KeyNameOne"); //透過 key 取出字串
            int valueInt = argument.getInt("KeyNameTwo"); //透過 key 取出整數
            float valueFloat = argument.getFloat("KeyNameThree"); //透過 key 取出浮點數

            String ext1 = (String) argument.get("ExtraName1");
            int ext2 = (Integer) argument.get("ExtraName2");
            Float ext3 = (Float) argument.get("ExtraName3");

            showText.setText(
                    "   ----- Bundle -----" +
                    "\n String = " + valueStr +
                    "\n Integer= " + valueInt +
                    "\n Float  = " + valueFloat +
                    "\n ----- Extra -----" +
                    "\n ExtString = " + ext1 +
                    "\n ExtInteger = " + ext2 +
                    "\n ExtFloat = " + ext3
            );
        }
        else if(mFrom.equals("startActivity_with_MyParcel")) {
            MyParcel_Book book = getIntent().getParcelableExtra("book");
            showText.setText(
                    "   ----- Book by Parcel -----" +
                    "\n Title = " + book.getTitle() +
                    "\n Author= " + book.getAuthor().getName()
            );
        } else if(mFrom.equals("startActivity_with_MyParcel_2")) {
            Student s = getIntent().getParcelableExtra("student");
            showText.setText(
                    "   ----- Student by Parcel -----" +
                    "\n name = " + s.name +
                    // Java int to String(整數轉字串) 3 招!!
                    "\n number= " + Integer.toString(s.number) +
                    "\n sex = " + String.valueOf(s.sex) +
                    "\n age= " + new String(""+s.age)
            );
        } else if(mFrom.equals("startActivity_with_JSON")) {
            Bundle argument = getIntent().getExtras(); //從 Intent 取出包裹
            String name = argument.getString("json.mBook.Author.name");
            int id = argument.getInt("json.mBook.Author.id");
            String title = argument.getString("json.mBook.Title");
            showText.setText(
                    "   ----- Book by Json->Bundle -----" +
                    "\n name = " + name +
                    "\n id= " + id +
                    "\n title  = " + title
            );
        } else if(mFrom.equals("startActivity_with_Serial")) {
            MySerializable.Book book = (MySerializable.Book)getIntent().getSerializableExtra("book");
            showText.setText(
                    "   ----- Book by Serializable -----" +
                    "\n title = " + book.getTitle() +
                    "\n name= " + book.getAuthor().getName()
            );
        }
        setContentView(showText);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // You also can return the result in OnClickListener() by a button
        // for startActivityForResult() API called in the previous Activity
        if(mFrom.equals("startActivityForResult")) {
            Intent intent=new Intent();
            Bundle bundle=new Bundle();
            bundle.putString("SecondActivity_Result", "BBBBBBBBBBBBBBBBBBBBBBB");
            intent.putExtras(bundle);
            setResult(RESULT_OK,intent);
        }
    }
}
