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




       // auth = FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() { // whenever login button is clicked
            @Override
            public void onClick(View view) {
                String txt_email = email.getText().toString(); // grabbing the inputed email and converting to string variable
                String txt_password = password.getText().toString();
                    //here we are calling the method and passing through two string values.
                loginUser(txt_email,txt_password);
            }
        });

        register = findViewById(R.id.register);

        //FEO Introduced this code open the RegisterActivity, this is an onclicklistener to open the Register Class
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loginUser(String email, String password) {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Loading");
        pd.show();
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
        //String register_url = "http://10.0.2.2:3000/api/v1/register";
        //String login_url = "http://10.0.2.2:3000/api/v1/authenticate";
        //Launch version
        //String register_url = "https://acm-app-backend.herokuapp.com/api/v1/register";
        String login_url = "https://acm-app-backend.herokuapp.com/api/v1/authenticate";
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
                login_url,
                params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {


                    @NonNull final String status = response.getString("success");
                    Log.d("LOGIN", status);
                    if (status.equals("true")) {
                        Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(MainActivity.this, DashActivity.class));
                        finish();
                    } else {
                        @NonNull final String error = response.getString("error");
                        Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
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

/*
    public void dummyActivity(View view) {
        Intent intent = new Intent(MainActivity.this, DashActivity.class);
        startActivity(intent);
    }

 */
}
