package sample.hawk.com.mybasicappcomponents.cache.SQL_Image;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import sample.hawk.com.mybasicappcomponents.R;

/**
 *
 *  Picture sample at http://cloudinary.com/documentation/fetch_remote_images
 * */
public class SQL_Image_Activity extends AppCompatActivity {

    ImageView imageView;
    ImageLinksCountDatabase imageLinkCountDatabase;
    //String mUrl = "http://newsfunstyle.com/wp-content/uploads/2014/12/Jello-Jigglers.jpg";
    String mUrl = "http://res.cloudinary.com/demo/image/fetch/http://upload.wikimedia.org/wikipedia/commons/0/0c/Scarlett_Johansson_Césars_2014.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sql_image_activity);
        imageView = (ImageView) findViewById(R.id.imageView);

        new ImageLoaderLibrary().load(mUrl, imageView);

        imageLinkCountDatabase = new ImageLinksCountDatabase(this);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sql_image_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.select_image1:
                mUrl = "http://res.cloudinary.com/demo/image/fetch/https%3A%2F%2Fencrypted-tbn2.gstatic.com%2Fimages%3Fq%3Dtbn%3AANd9GcSVmwds9qedbHZ__uc1E40a-s70KtNiiLVKiaKymGszYdIDtzJfzw";
                return true;
            case R.id.select_image2:
                mUrl = "http://res.cloudinary.com/demo/image/upload/remote_media/commons/2/29/Marcelo_Facini.jpg";
                return true;
            case R.id.select_image3:
                mUrl = "http://res.cloudinary.com/demo/image/fetch/http://upload.wikimedia.org/wikipedia/commons/0/0c/Scarlett_Johansson_Césars_2014.jpg";;
                return true;
            case R.id.select_image4:
                mUrl = "";
                return true;
            default:
                return false;
        }
    }

    public void onSubmit(View view) {
        TextView etURL = (TextView) findViewById(R.id.etURL);
        String loadURL;
        String url = etURL.getText().toString();
        if(!TextUtils.isEmpty(url)) {
            loadURL = url;
        }
        else{
            loadURL = mUrl;
        }
        ImageLoaderLibrary.load(loadURL, imageView);
        ImageLinkCount row = imageLinkCountDatabase.getRecord(loadURL);
        Toast.makeText(this, String.valueOf(row.getTimesSeen()), Toast.LENGTH_SHORT).show();

    }
}
