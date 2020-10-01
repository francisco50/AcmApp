package com.egcoding.acmapp;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

//Reference: https://stackoverflow.com/questions/16207094/android-webview-not-loading-url
public class PaymentActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        WebView paymentWebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = paymentWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        paymentWebView.addJavascriptInterface(new JSBridge(this), "JSBridge");
        //paymentWebView.loadUrl("https://www.google.com/");
        String paymentPage = "file:///android_asset/paypal.html";
        paymentWebView.loadUrl(paymentPage);
    }
    //TODO: navigate to DashActivity when user pay successfully
    //startActivity(new Intent(RegisterActivity.this, DashActivity.class));
}
