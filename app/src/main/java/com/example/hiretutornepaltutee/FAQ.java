package com.example.hiretutornepaltutee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class FAQ extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        webView=findViewById(R.id.Faq);
        webView.loadUrl("file:///android_asset/tuteefaq.html");
    }
}
