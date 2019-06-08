package com.example.hiretutornepaltutee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class Privacy extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);
        webView=findViewById(R.id.privacyPolicy);
        webView.loadUrl("file:///android_asset/privacypolicy.html");
    }
}
