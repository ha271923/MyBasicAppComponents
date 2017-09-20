package sample.hawk.com.mybasicappcomponents.graphic;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import sample.hawk.com.mybasicappcomponents.R;

/**
 *
 * https://developer.android.com/reference/android/widget/ImageView.ScaleType.html
 *
 *   CENTER – Center the image but doesn’t scale the image
 *   CENTER_CROP – Scale the image uniformly
 *   CENTER_INSIDE – Center the image inside the container, rather than making the edge match exactly
 *   FIT_CENTER – Scale the image from center
 *   FIT_END – Scale the image from the end of the container.
 *   FIT_START – Scale the image from start of the container
 *   FIT_XY – Fill the image from x and y coordinates of the container
 *   MATRIX – Scale using the image matrix when drawing
 *
 */


public class MyScaleTypeActivity2 extends AppCompatActivity implements View.OnClickListener {

    ImageView simpleImageView;
    Button scaleTypeCenter, scaleTypeCenterCrop, scaleTypeCenterInside, scaleTypeFitCenter, scaleTypeFitEnd, scaleTypeFitStart, scaleTypeFitXY, scaleTypeMatrix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myscaletypeactivity2);
        //initiate views
        simpleImageView = (ImageView) findViewById(R.id.simpleImageView);
        scaleTypeCenter = (Button) findViewById(R.id.scaleTypeCenter);
        scaleTypeCenter.setOnClickListener(this);
        scaleTypeCenterCrop = (Button) findViewById(R.id.scaleTypeCenterCrop);
        scaleTypeCenterCrop.setOnClickListener(this);
        scaleTypeCenterInside = (Button) findViewById(R.id.scaleTypeCenterInside);
        scaleTypeCenterInside.setOnClickListener(this);
        scaleTypeFitCenter = (Button) findViewById(R.id.scaleTypeFitCenter);
        scaleTypeFitCenter.setOnClickListener(this);
        scaleTypeFitEnd = (Button) findViewById(R.id.scaleTypeFitEnd);
        scaleTypeFitEnd.setOnClickListener(this);
        scaleTypeFitStart = (Button) findViewById(R.id.scaleTypeFitStart);
        scaleTypeFitStart.setOnClickListener(this);
        scaleTypeFitXY = (Button) findViewById(R.id.scaleTypeFitXY);
        scaleTypeFitXY.setOnClickListener(this);
        scaleTypeMatrix = (Button) findViewById(R.id.scaleTypeMatrix);
        scaleTypeMatrix.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.scaleTypeCenter:
                simpleImageView.setScaleType(ImageView.ScaleType.CENTER);
                Toast.makeText(getApplicationContext(), "ScaleTypeCenter", Toast.LENGTH_SHORT).show();
                break;
            case R.id.scaleTypeCenterCrop:
                simpleImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                Toast.makeText(getApplicationContext(), "ScaleTypeCenterCrop", Toast.LENGTH_SHORT).show();
                break;
            case R.id.scaleTypeCenterInside:
                simpleImageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                Toast.makeText(getApplicationContext(), "ScaleTypeCenterInside", Toast.LENGTH_SHORT).show();
                break;
            case R.id.scaleTypeFitCenter:
                simpleImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                Toast.makeText(getApplicationContext(), "ScaleTypeFitCenter", Toast.LENGTH_SHORT).show();
                break;
            case R.id.scaleTypeFitEnd:
                simpleImageView.setScaleType(ImageView.ScaleType.FIT_END);
                Toast.makeText(getApplicationContext(), "ScaleTypeFitEnd", Toast.LENGTH_SHORT).show();
                break;
            case R.id.scaleTypeFitStart:
                simpleImageView.setScaleType(ImageView.ScaleType.FIT_START);
                Toast.makeText(getApplicationContext(), "ScaleTypeFitStart", Toast.LENGTH_SHORT).show();
                break;
            case R.id.scaleTypeFitXY:
                simpleImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                Toast.makeText(getApplicationContext(), "ScaleTypeFitXY", Toast.LENGTH_SHORT).show();
                break;
            case R.id.scaleTypeMatrix:
                simpleImageView.setScaleType(ImageView.ScaleType.MATRIX);
                Toast.makeText(getApplicationContext(), "ScaleTypeMatrix", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}
