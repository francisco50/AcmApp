package com.egcoding.acmapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import static java.util.Map.entry;

public class JSBridge {
    private static Activity activity;
    private static String email = "";
    private static String level_id = "";

    public JSBridge(Activity activity) {
        this.activity = activity;
    }

    public static void goToLoginPage() {
        if (activity != null) {
            Intent intent = new Intent(activity, MainActivity.class);
            activity.startActivity(intent);
            activity.finish();
        }
        return;
    }

    public static void set(String em, String level) {
        email = em;
        level_id = level;
    }

    @JavascriptInterface
    public void startRegister(String membershipFee) {
        //Use this to retrieve the correct membershipLevel based on the membershipFee
        Map<String, String> membershipLevels = new HashMap<String, String>(){{
            put("15", "1");
            put("25", "4");
            put("30", "3");
        }};
        String register_url = "http://10.0.2.2:3000/api/v1/setMembership";
        //Launch version
        //String register_url = "https://acm-app-backend.herokuapp.com/api/v1/register";
        //prepare data
        final String email = JSBridge.email;
        final String level_id = membershipLevels.get(membershipFee);
        final JSONObject params = new JSONObject();
        try {
            params.put("email", email);
            params.put("level_id", level_id);
        } catch (JSONException e) {
            Log.e("SET MEMBERSHIP", "PARSE PARAMS ERROR");
            e.printStackTrace();
        }

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST,
                register_url,
                params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    @NonNull final String status = response.getString("data");
                    Log.d("SET MEMBERSHIP", status);
                    if (status == "true") {

                        //Toast.makeText(RegisterActivity.this, "User is created successfully", Toast.LENGTH_SHORT).show();
                        JSBridge.goToLoginPage();
                    } else {
                        @NonNull final String error = response.getString("error");
                        //Toast.makeText(RegisterActivity.this, error, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Log.e("REGISTER", "Could not parse response " + response);
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("REGISTER", error.getMessage());
                //Toast.makeText(this, "Volley error", Toast.LENGTH_SHORT).show();
            }
        });
        //make request by adding it to RequestQueue
        Volley.newRequestQueue(this.activity).add(jsonRequest);
    }

}
