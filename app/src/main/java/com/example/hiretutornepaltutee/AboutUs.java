package com.example.hiretutornepaltutee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class AboutUs extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        webView=findViewById(R.id.AboutUs);
        webView.loadUrl("file:///android_asset/aboutus.html");
    }
}
