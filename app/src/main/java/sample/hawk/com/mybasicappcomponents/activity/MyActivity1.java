package sample.hawk.com.mybasicappcomponents.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.Serializable;

import sample.hawk.com.mybasicappcomponents.data_structure.Serial_Json_Parcel.MyJson;
import sample.hawk.com.mybasicappcomponents.data_structure.Serial_Json_Parcel.MyParcel_Author;
import sample.hawk.com.mybasicappcomponents.data_structure.Serial_Json_Parcel.MyParcel_Book;
import sample.hawk.com.mybasicappcomponents.data_structure.Serial_Json_Parcel.MySerializable;
import sample.hawk.com.mybasicappcomponents.data_structure.Serial_Json_Parcel.Student;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/1/6.
 */

public class MyActivity1 extends Activity {
    private static final int GET_RESULT=1111111;

    private Activity activity;
    private Intent goOtherActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        goOtherActivity = new Intent(activity, MyActivity2.class);
        LinearLayout myRoot = new LinearLayout(this);
        myRoot.setOrientation(LinearLayout.VERTICAL);

        Button toNextPage = new Button(this);
        toNextPage.setText("toNextPage");
        myRoot.addView(toNextPage);

        Button toNextPageWithResult = new Button(this);
        toNextPageWithResult.setText("toNextPageWithResult");
        myRoot.addView(toNextPageWithResult);

        Button toNextPageWithParcelable = new Button(this);
        toNextPageWithParcelable.setText("toNextPageWithParcelable");
        myRoot.addView(toNextPageWithParcelable);

        Button toNextPageWithParcelable_2 = new Button(this);
        toNextPageWithParcelable_2.setText("toNextPageWithParcelable_2");
        myRoot.addView(toNextPageWithParcelable_2);

        Button toNextPageWithJson = new Button(this);
        toNextPageWithJson.setText("toNextPageWithJson");
        myRoot.addView(toNextPageWithJson);

        Button toNextPageWithSerializable = new Button(this);
        toNextPageWithSerializable.setText("toNextPageWithSerializable");
        myRoot.addView(toNextPageWithSerializable);

        setContentView(myRoot);

        toNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //建立包裹
                Bundle argument = new Bundle();
                argument.putString("KeyNameOne","String Text"); //放入字串，key 取名 KeyNameOne
                argument.putInt("KeyNameTwo",99); //放入整數，key 取名 KeysNameTwo
                argument.putFloat("KeyNameThree",123.456F); //放入浮點數，key 取名 KeyNameTwo

                // putExtra API can normal data directly
                goOtherActivity.putExtra("from","startActivity");
                goOtherActivity.putExtras(argument); //將bundle args放入 Intent 中。
                goOtherActivity.putExtra("ExtraName1", "Extra Text");
                goOtherActivity.putExtra("ExtraName2", 77);
                goOtherActivity.putExtra("ExtraName3", 321.654F);

                activity.startActivity(goOtherActivity);
            }
        });

        toNextPageWithResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //建立包裹
                Bundle argument = new Bundle();
                argument.putString("KeyNameOne","String Text"); //放入字串，key 取名 KeyNameOne
                argument.putInt("KeyNameTwo",99); //放入整數，key 取名 KeysNameTwo
                argument.putFloat("KeyNameThree",123.456F); //放入浮點數，key 取名 KeyNameTwo
                // putExtra API can normal data directly
                goOtherActivity.putExtra("from","startActivityForResult");
                goOtherActivity.putExtras(argument); //將bundle args放入 Intent 中。
                goOtherActivity.putExtra("ExtraName1", "Extra Text");
                goOtherActivity.putExtra("ExtraName2", 77);
                goOtherActivity.putExtra("ExtraName3", 321.654F);

                activity.startActivityForResult(goOtherActivity,GET_RESULT); // return at onActivityResult callback

                SMLog.i("called startActivityForResult()"); // this line is NOT blocked.
            }
        });

        toNextPageWithParcelable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyParcel_Book book = new MyParcel_Book();
                book.setTitle("Java編程思想");
                MyParcel_Author author = new MyParcel_Author();
                author.setId(9999);
                author.setName("Hawk Wei");
                book.setAuthor(author);
                // putExtra API can Pass Parcel data directly
                goOtherActivity.putExtra("from","startActivity_with_MyParcel");
                goOtherActivity.putExtra("book",(Parcelable)book);
                startActivity(goOtherActivity);
            }
        });

        toNextPageWithParcelable_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student s = new Student();
                s.number = 99;
                s.name = "hawk_wei";
                s.sex = 1;
                s.age= 23;
                // putExtra API can Pass Parcel data directly
                goOtherActivity.putExtra("from","startActivity_with_MyParcel_2");
                goOtherActivity.putExtra("student", s);
                startActivity(goOtherActivity);
            }
        });

        toNextPageWithJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyJson json = new MyJson();
                MyJson.Author author = new MyJson().new Author();
                author.setName("hawk_wei");
                author.setId(23);
                json.mBook.setAuthor(author);
                json.mBook.setTitle("JsonDataBook");
                //建立包裹, 因為不支援JSON, 所以需要轉換
                Bundle argument = new Bundle();
                argument.putString("json.mBook.Author.name", "hawk_wei");
                argument.putInt("json.mBook.Author.id", 23);
                argument.putString("json.mBook.Title", "JsonBook");
                // WARNING: putExtra API can NOT pass JSON data directly, convert JSON to native format.
                goOtherActivity.putExtra("from","startActivity_with_JSON");
                goOtherActivity.putExtras(argument); //將bundle args放入 Intent 中。
                startActivity(goOtherActivity);
            }
        });

        toNextPageWithSerializable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MySerializable myserial = new MySerializable();
                myserial.mBook.setTitle("Java編程思想");
                myserial.mBook.getAuthor().setId(7777);
                myserial.mBook.getAuthor().setName("Hawk Wei");
                goOtherActivity.putExtra("from","startActivity_with_Serial");
                goOtherActivity.putExtra("book",(Serializable)myserial.mBook);
                startActivity(goOtherActivity);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode){
            case GET_RESULT:
                Toast.makeText(this, data.getExtras().getString("SecondActivity_Result"), Toast.LENGTH_SHORT).show();
                SMLog.i("return SecondActivity_Result = "+data.getExtras().getString("SecondActivity_Result"));
                break;
        }
    }
}
