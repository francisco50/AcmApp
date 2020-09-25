package com.egcoding.acmapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;
import org.json.JSONObject;


public class RegisterActivity extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private Button register;

    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register); // whenever the register button is clicked, must check if a valid value is inputed in email & password text field
        auth = FirebaseAuth.getInstance();

        // added firebase authentication to the application
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();

                // check if above values are empty
                if(TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)){ // checking if txt_email and txt_password is empty or not
                    Toast.makeText(RegisterActivity.this, "Empty credentials!", Toast.LENGTH_SHORT).show();
                }else if(txt_password.length() <6){ // check if password is less than 6 values
                    Toast.makeText(RegisterActivity.this, "Password too short!", Toast.LENGTH_SHORT).show();
                }else{
                    registerUser(txt_email , txt_password);
                }
            }
        });
    }

    private void registerUser(String email, String password) {
//        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this ,new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if(task.isSuccessful()){
//                    Toast.makeText(RegisterActivity.this, "Registering user successful!", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(RegisterActivity.this, DashActivity.class));
//                    finish();
//                } else{
//                    Toast.makeText(RegisterActivity.this, "Registration failed!", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
        //register with wordpress
        //Development version
        //Use https will cause error
        String register_url = "http://10.0.2.2:3000/api/v1/register";
        //Launch version
        //String register_url = "https://acm-app-backend.herokuapp.com/api/v1/register";
        //prepare data
        final JSONObject params = new JSONObject();
        try {
            params.put("email", email);
            params.put("password", password);
        } catch (JSONException e) {
            Log.e("REGISTER", "PARSE PARAMS ERROR");
            e.printStackTrace();
        }
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST,
                register_url,
                params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    @NonNull final String status = response.getString("success");
                    Log.d("REGISTER", status);
                    if (status == "true") {
                        Toast.makeText(RegisterActivity.this, "User is created successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, DashActivity.class));
                        finish();
                    } else {
                        @NonNull final String error = response.getString("error");
                        Toast.makeText(RegisterActivity.this, error, Toast.LENGTH_SHORT).show();
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
                Toast.makeText(RegisterActivity.this, "Volley error", Toast.LENGTH_SHORT).show();
            }
        });
        //make request by adding it to RequestQueue
        Volley.newRequestQueue(RegisterActivity.this).add(jsonRequest);
    }//---end function---

}//--end class---
