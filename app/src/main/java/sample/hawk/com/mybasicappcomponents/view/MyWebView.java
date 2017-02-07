package sample.hawk.com.mybasicappcomponents.view;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.File;
import java.io.InputStream;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;
import sample.hawk.com.mybasicappcomponents.utils.StorageHelper;

/**
 * Created by ha271 on 2017/2/6.
 */

public class MyWebView extends Activity{
    WebView mWebView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mywebview);

        mWebView = (WebView)findViewById(R.id.webview);
        // ConfigWebViewSettings(mWebView,"sample.hawk.com.mybasicappcomponents");
        mWebView.setWebViewClient(mWebViewClient);
        mWebView.setWebChromeClient(mWebChromeClient);
//        mWebView.loadUrl("http://tw.yahoo.com"); // (A) online web site
//        mWebView.loadUrl("file:///android_asset/simple/index.html"); // (B) offline web site from Asset

        // (C) load offline web site from Asset\zipfile
        ZippedAssetToWeb("tips_help.zip");
        String webpath = "file://" + getFilesDir().toString() + "/unzipped/"+"tips_help/faq/index.html";
        mWebView.loadUrl(webpath);
    }

    WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    };

    WebChromeClient mWebChromeClient = new WebChromeClient() {

        @Override
        public void onReceivedTitle(WebView view, String title) {
            if ((title != null) && (title.trim().length() != 0)) {
                setTitle(title);
            }
        }
    };

    // Convert Asset\zipped file --> Path\zipped file -> unzipped files -> load offline web site
    boolean ZippedAssetToWeb(String assetName){
        SMLog.i("getFilesDir().toString()="+getFilesDir().toString());
        String assetFile = assetName;
        String zipFile = getFilesDir().toString()+"/"+assetFile;
        AssetManager am = getAssets();
        try {
            StorageHelper sh = new StorageHelper(getFilesDir().toString());
            InputStream inputStream = am.open(assetFile);
            File file = sh.createFileFromInputStream(inputStream, zipFile);
            sh.unzip(zipFile, "/unzipped/"); // take long time
            return true;
        }
        catch (Exception e){
            SMLog.e("Error:"+e);
            return false;
        }
    }

    // 你可以取得這 WebView 物件的 WebSettings， 來設定這個物件的一些屬性，像是是否允許執行 JavaScript、是否允許 HTML5 的 local storage 等等～
    void ConfigWebViewSettings(WebView wv, String packagename){
        String packageName = packagename;
        WebSettings settings = wv.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setDatabasePath("/data/data/"+packageName+"/databases");
        settings.setDomStorageEnabled(true);
    }
}
