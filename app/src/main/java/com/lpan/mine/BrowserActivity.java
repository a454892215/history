package com.lpan.mine;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import com.lpan.R;
import com.lpan.base.BaseActivity;

import commom.utils.LogUtil;
import commom.utils.ToastUtil;

public class BrowserActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_browser);

        final WebView wv_mine_browser = (WebView) findViewById(R.id.wv_mine_browser);
        WebSettings webSettings = wv_mine_browser.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        wv_mine_browser.addJavascriptInterface(new InJavaScriptLocalObj(), "java_obj");
        wv_mine_browser.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);   //在当前的webview中跳转到新的url
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                view.loadUrl("javascript:function removeAd(){document.alert('hello');}removeAd();");
            }
        });

        final EditText et_mine_edit = (EditText) findViewById(R.id.et_mine_edit);
        View bt_mine_enter =  findViewById(R.id.bt_mine_enter);
        bt_mine_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = et_mine_edit.getText().toString();
                if(!text.startsWith("http")){
                    text = "http:"+text;
                    et_mine_edit.setText(text);
                }
                LogUtil.d(text);
                wv_mine_browser.loadUrl(text);
            }
        });
        wv_mine_browser.loadUrl("http://www.baidu.com/");
    }

    final class InJavaScriptLocalObj {
        @JavascriptInterface
        public void getSource(String html) {
            LogUtil.d(html);
            ToastUtil.makeLong("hhh");
        }
    }
}
