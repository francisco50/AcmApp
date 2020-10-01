package com.egcoding.acmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
public class JSBridge {
    private Activity activity;

    public JSBridge(Activity activity) {
        this.activity = activity;
    }

    @JavascriptInterface
    public void startRegister() {

        Intent intent = new Intent(this.activity, DashActivity.class);
        activity.startActivity(intent);
    }

}
