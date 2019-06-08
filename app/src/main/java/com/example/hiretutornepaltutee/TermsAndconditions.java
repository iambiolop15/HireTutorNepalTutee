package com.example.hiretutornepaltutee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class TermsAndconditions extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_andconditions);
        webView=findViewById(R.id.TermsAndConditions);
        webView.loadUrl("file:///android_asset/termsandconditions.html");
    }
}
