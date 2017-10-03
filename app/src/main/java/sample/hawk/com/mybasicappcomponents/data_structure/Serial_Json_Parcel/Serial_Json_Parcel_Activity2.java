package sample.hawk.com.mybasicappcomponents.data_structure.Serial_Json_Parcel;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/**
 * Created by ha271 on 2017/2/24.
 */

public class Serial_Json_Parcel_Activity2 extends Activity {
    final String TAG = "MyJavaActivity2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compare_serial_json_parcel_activity);
        TextView tvSource = (TextView)findViewById(R.id.Source);
        TextView tvTitle  = (TextView)findViewById(R.id.Title);
        TextView tvAuthor = (TextView)findViewById(R.id.Author);

        String from = getIntent().getStringExtra("from");
        tvSource.setText(from);
        if(from.equals("MySerializable")){
            MySerializable.Book book = (MySerializable.Book)getIntent().getSerializableExtra("book");
            tvTitle.setText(book.getTitle());
            tvAuthor.setText(book.getAuthor().getName());
        }
        else if(from.equals("MyJson")){
            String bookJson = getIntent().getStringExtra("book");
            MyJson.Book  book = new Gson().fromJson(bookJson,MyJson.Book.class ); // MUST
            tvTitle.setText(book.getTitle());
            tvAuthor.setText(book.getAuthor().getName());
        }
        else if(from.equals("MyParcel")) {
            //MyParcel.Book book = getIntent().getParcelableExtra("book");
            MyParcel_Book book = getIntent().getParcelableExtra("book");
            tvTitle.setText(book.getTitle());
            tvAuthor.setText(book.getAuthor().getName());
        }
        else {
            SMLog.e("Not support!");
        }
    }


}
