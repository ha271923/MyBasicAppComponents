package sample.hawk.com.mybasicappcomponents.view;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import sample.hawk.com.mybasicappcomponents.R;

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

        mWebView.setWebViewClient(mWebViewClient);
        mWebView.setWebChromeClient(mWebChromeClient);
        //mWebView.loadUrl("http://tw.yahoo.com");
        mWebView.loadUrl("file:///android_asset/simple/index.html");
        //mWebView.loadUrl("file:///android_asset/tips_help/faq/index.html");
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
}
