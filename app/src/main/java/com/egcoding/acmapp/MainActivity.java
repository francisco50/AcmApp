package com.egcoding.acmapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
    private EditText email; // Will capture email, EditText: A user interface element for entering and modifying text
    private EditText password;// Will capture password
    private Button login;//login button
    private Button register;//register button
   // private TextView forgotPassword;


    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//Initialize activity
        setContentView(R.layout.activity_main);//calling activity_main xml file to define UI

        email=findViewById(R.id.email);//Retrieve widget with name of email on activity_main xml file
        password=findViewById(R.id.password);//Retrieve widget with name of password on activity_main xml file
        login=findViewById(R.id.loginmain);// Retrieve widget with name of loginmain of activity_main xml file
       // forgotPassword = findViewById(R.id.forgotPasswordLink);

        login.setOnClickListener(new View.OnClickListener() { // whenever login button is clicked
            @Override
            public void onClick(View view) {
                String txt_email = email.getText().toString(); // grabbing the inputed email and converting to string variable
                String txt_password = password.getText().toString();
                //here we are calling the method and passing through two string values.
                // check if above values are empty
                if(TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)){ // checking if txt_email and txt_password is empty or not
                    Toast.makeText(MainActivity.this, "Username and password cannot be empty", Toast.LENGTH_SHORT).show();
                } else{
                    loginUser(txt_email,txt_password);
                }
            }
        });

        register = findViewById(R.id.register);

        //Use this to test in local backend
        //CustomAPI.setDevelopmentMode();
        //Use this when publish the app to the store
        CustomAPI.setPublishMode();


        //FEO Introduced this code open the RegisterActivity, this is an onclicklistener to open the Register Class
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);

                //TEST

                startActivity(intent);
            }
        });
    }

    //Dismiss progress dialog before moving to different Activity to avoid leaked memory
    @Override
    protected void onPause() {
        super.onPause();
        if (pd != null)
            pd.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (pd != null)
            pd.dismiss();
    }

    private void showProgressDialog(String msg) {
        if (pd == null)
            pd = new ProgressDialog(this);
        pd.setMessage(msg);
        pd.show();
    }
    private void loginUser(String email, String password) {
        showProgressDialog("Logging In");

        //prepare data
        final JSONObject params = new JSONObject();
        try {
            params.put("email", email);
            params.put("password", password);
        } catch (JSONException e) {
            Log.e("LOGIN", "PARSE PARAMS ERROR");
            e.printStackTrace();
        }
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST,
                CustomAPI.getLoginUrl(),
                params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {


                    @NonNull final String status = response.getString("success");
                    Log.d("LOGIN", status);
                    if (status.equals("true")) {
                        Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, DashActivity.class));
                    } else {
                        @NonNull final String error = response.getString("error");
                        Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
                        if (pd != null)
                            pd.dismiss();
                    }
                } catch (JSONException e) {
                    Log.e("LOGIN", "Could not parse response " + response);
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("LOGIN", error.getMessage());
                Toast.makeText(MainActivity.this, "Volley error", Toast.LENGTH_SHORT).show();
            }
        });
        //make request by adding it to RequestQueue
        Volley.newRequestQueue(MainActivity.this).add(jsonRequest);
    }//---end function---
}
