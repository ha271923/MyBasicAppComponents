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
import com.facebook.ads.NativeAd;

import java.util.ArrayList;
import java.util.List;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

/*
    FB SDK native AD sample:
    https://developers.facebook.com/docs/audience-network/android-native
*/

public class Facebook_NativeAD_Activity extends AppCompatActivity {
    private NativeAd nativeAd;
    private LinearLayout nativeAdContainer;
    private LinearLayout adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fb_activity_native_ad);
        showNativeAd();
    }

    private void showNativeAd() {
        // String placement_id=  "YOUR_PLACEMENT_ID"; // Original FB sample code
        String placement_id= "834287756621964_1222830741100995"; // HTC AD test placement-1

        nativeAd = new NativeAd(this, placement_id);
        nativeAd.setAdListener(new AdListener() {

            @Override
            public void onError(Ad ad, AdError error) {
                // Ad error callback
                SMLog.i("onError");
            }

            @Override
            public void onAdLoaded(Ad ad) {

                // Add the Ad view into the ad container.
                nativeAdContainer = (LinearLayout) findViewById(R.id.native_ad_container);
                LayoutInflater inflater = LayoutInflater.from(Facebook_NativeAD_Activity.this);
                adView = (LinearLayout) inflater.inflate(R.layout.fb_native_ad_layout, nativeAdContainer, false);
                nativeAdContainer.addView(adView);

                // Create native UI using the ad metadata.
                ImageView nativeAdIcon = (ImageView) adView.findViewById(R.id.native_ad_icon);
                TextView nativeAdTitle = (TextView) adView.findViewById(R.id.native_ad_title);
                MediaView nativeAdMedia = (MediaView) adView.findViewById(R.id.native_ad_media);
/*
                boolean isNativeConfigEnabled = nativeAd.isNativeConfigEnabled();
                Log.i("Hawk","+++ isNativeConfigEnabled="+isNativeConfigEnabled);
                nativeAdMedia.setAutoplay(true);
                nativeAdMedia.setAutoplayOnMobile(true);
                nativeAd.setMediaViewAutoplay(true);
                NativeAdViewAttributes AdViewAttributes = nativeAd.getAdViewAttributes();
                Log.i("Hawk","--- isNativeConfigEnabled="+isNativeConfigEnabled);
*/
                TextView nativeAdSocialContext = (TextView) adView.findViewById(R.id.native_ad_social_context);
                TextView nativeAdBody = (TextView) adView.findViewById(R.id.native_ad_body);
                Button nativeAdCallToAction = (Button) adView.findViewById(R.id.native_ad_call_to_action);

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
                AdChoicesView adChoicesView = new AdChoicesView(Facebook_NativeAD_Activity.this, nativeAd, true);
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
                SMLog.i("onAdClicked");
            }


        });

        // Request an ad
        nativeAd.loadAd();
        //nativeAd.loadAd(NativeAd.MediaCacheFlag.ALL); // enables the MediaView to play videos immediately after nativeAd finishes loading.
    }
}
