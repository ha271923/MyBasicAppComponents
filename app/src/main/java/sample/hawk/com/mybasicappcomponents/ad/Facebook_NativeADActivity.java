package sample.hawk.com.mybasicappcomponents.ad;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.ads.Ad;
import com.facebook.ads.AdChoicesView;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.MediaView;
import com.facebook.ads.MediaViewListener;
import com.facebook.ads.NativeAd;

import java.util.ArrayList;
import java.util.List;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/*
    FB SDK native AD sample:
    https://developers.facebook.com/docs/audience-network/android-native
*/

public class Facebook_NativeADActivity extends AppCompatActivity {
    private NativeAd nativeAd;
    private LinearLayout nativeAdContainer;
    private LinearLayout adView;

    ImageView nativeAdIcon;
    TextView nativeAdTitle;
    MediaView nativeAdMedia;
    TextView nativeAdSocialContext;
    TextView nativeAdBody;
    Button nativeAdCallToAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fb_activity_native_ad);
        showNativeAd();
    }

    public void onClick_RefreshAD(View view){
        nativeAd.destroy();
        showNativeAd();
    }

    public void onCreateAdView(Ad ad){

        // Add the Ad view into the ad container.
        nativeAdContainer = (LinearLayout) findViewById(R.id.native_ad_container);
        LayoutInflater inflater = LayoutInflater.from(Facebook_NativeADActivity.this);
        adView = (LinearLayout) inflater.inflate(R.layout.fb_native_ad_layout, nativeAdContainer, false);
        nativeAdContainer.addView(adView);

        // Create native UI using the ad metadata.
        nativeAdIcon = (ImageView) adView.findViewById(R.id.native_ad_icon);
        nativeAdTitle = (TextView) adView.findViewById(R.id.native_ad_title);
        nativeAdMedia = (MediaView) adView.findViewById(R.id.native_ad_media);

        nativeAdMedia.setAutoplay(true);
        nativeAdMedia.setAutoplayOnMobile(true);
        nativeAd.setMediaViewAutoplay(true);
        nativeAdMedia.setListener(new MediaViewListener() {
            @Override
            public void onVolumeChange(MediaView mediaView, float v) {
                SMLog.i("onVolumeChange");
            }

            @Override
            public void onPause(MediaView mediaView) {
                SMLog.i("onPause");
            }

            @Override
            public void onPlay(MediaView mediaView) {
                SMLog.i("onPlay");
            }

            @Override
            public void onExitFullscreen(MediaView mediaView) {
                SMLog.i("onExitFullscreen");
            }

            @Override
            public void onEnterFullscreen(MediaView mediaView) {
                SMLog.i("onEnterFullscreen");
            }

            @Override
            public void onComplete(MediaView mediaView) {
                SMLog.i("onComplete");
            }
        });

/*
        boolean isNativeConfigEnabled = nativeAd.isNativeConfigEnabled();
        SMLog.i("+++ isNativeConfigEnabled="+isNativeConfigEnabled);
        NativeAdViewAttributes AdViewAttributes = nativeAd.getAdViewAttributes();
        SMLog.i("--- isNativeConfigEnabled="+isNativeConfigEnabled);
*/
        nativeAdSocialContext = (TextView) adView.findViewById(R.id.native_ad_social_context);
        nativeAdBody = (TextView) adView.findViewById(R.id.native_ad_body);
        nativeAdCallToAction = (Button) adView.findViewById(R.id.native_ad_call_to_action);
    }

    private void showNativeAd() {
        // String placement_id=  "YOUR_PLACEMENT_ID"; // FB sample code
        String placement_id= "834287756621964_1222830741100995"; // Hawk test placement

        nativeAd = new NativeAd(this, placement_id);
        nativeAd.setAdListener(new AdListener() {

            @Override
            public void onError(Ad ad, AdError error) {
                // Ad error callback
            }

            @Override
            public void onAdLoaded(Ad ad) {

                if(nativeAdContainer==null)
                    onCreateAdView(nativeAd);

                // Set the Text.
                nativeAdTitle.setText(nativeAd.getAdTitle());
                nativeAdSocialContext.setText(nativeAd.getAdSocialContext());
                nativeAdBody.setText(nativeAd.getAdBody());
                nativeAdCallToAction.setText(nativeAd.getAdCallToAction());

                // Download and display the ad icon.
                NativeAd.Image adIcon = nativeAd.getAdIcon();
                NativeAd.downloadAndDisplayImage(adIcon, nativeAdIcon);

                // Download and display the cover image.
                nativeAdMedia.setNativeAd(nativeAd);

                // Add the AdChoices icon
                LinearLayout adChoicesContainer = (LinearLayout) findViewById(R.id.ad_choices_container);
                AdChoicesView adChoicesView = new AdChoicesView(Facebook_NativeADActivity.this, nativeAd, true);
                adChoicesContainer.addView(adChoicesView);

                // Register the Title and CTA button to listen for clicks.
                List<View> clickableViews = new ArrayList<>();
                clickableViews.add(nativeAdTitle);
                clickableViews.add(nativeAdCallToAction);
                nativeAd.registerViewForInteraction(nativeAdContainer, clickableViews);
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
            }
        });

        // Request an ad
        //nativeAd.loadAd();
        nativeAd.loadAd(NativeAd.MediaCacheFlag.ALL);
    }
}