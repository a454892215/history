package com.lpan.topnew;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.lpan.R;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_activity);
        WebView top_web = (WebView) findViewById(R.id.top_web);
        String url = getIntent().getStringExtra("url");
        top_web.loadUrl(url);
    }
}
