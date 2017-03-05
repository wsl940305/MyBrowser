package com.qf.wsl.mybrowser;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText urlInput_search;
    private Button button_search;
    private WebView webView_show;
    private String thekey_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        button_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView_show.getSettings().setJavaScriptEnabled(true);
                webView_show.getSettings().setDomStorageEnabled(true);
                thekey_search = urlInput_search.getText().toString();
                String str01 = "www.";
                String str02 = ".com";
                String str03 = ".cn";
                if (thekey_search.contains(str01) && thekey_search.contains(str02) == false && thekey_search.contains(str03) == false) {
                    webView_show.loadUrl("http://" + thekey_search + ".com");
                    webView_show.setWebViewClient(new HelloWeb());
                } else if (thekey_search.contains(str02) && thekey_search.contains(str01) == false) {
                    webView_show.loadUrl("http://www." + thekey_search);
                    webView_show.setWebViewClient(new HelloWeb());
                } else if (thekey_search.contains(str03) && thekey_search.contains(str01) == false) {
                    webView_show.loadUrl("http://www." + thekey_search);
                    webView_show.setWebViewClient(new HelloWeb());
                } else if (thekey_search.contains(str01) && thekey_search.contains(str02)) {
                    webView_show.loadUrl("http://" + thekey_search);
                    webView_show.setWebViewClient(new HelloWeb());
                } else if (thekey_search.contains(str01) && thekey_search.contains(str03)) {
                    webView_show.loadUrl("http://" + thekey_search);
                    webView_show.setWebViewClient(new HelloWeb());
                } else {
                    webView_show.loadUrl("http://www.baidu.com/s?wd=\n" + thekey_search + "&rsv_bp=0&ch=&tn=baidu&bar=&rsv_spt=3&ie=utf-\n"
                            + "8&rsv_sug3=3&rsv_sug=0&rsv_sug4=95&rsv_sug1=1&inputT=1001");
                    webView_show.setWebViewClient(new HelloWeb());
                }
            }
        });
    }

    private void initView() {
        urlInput_search = (EditText) findViewById(R.id.edittext_serch);
        button_search = (Button) findViewById(R.id.but_go);
        webView_show = (WebView) findViewById(R.id.webview_show);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView_show.canGoBack()) {
            webView_show.goBack();
            return true;
        }
        return false;
    }

    private class HelloWeb extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}