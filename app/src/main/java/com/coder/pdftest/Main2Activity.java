package com.coder.pdftest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class Main2Activity extends AppCompatActivity {
    private String pdfPath = "https://docs.google.com/viewer?url=http://unec.edu.az/application/uploads/2014/12/pdf-sample.pdf";
    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        webview = (WebView) findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);


    }

    @Override
    protected void onResume() {
        super.onResume();

        webview.loadUrl(pdfPath);
    }
}
