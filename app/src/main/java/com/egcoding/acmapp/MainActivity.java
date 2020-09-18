package com.egcoding.acmapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private Button login;
    private Button register;
    Button tempButton;
    // private TextView forgotPassword;

    private FirebaseAuth auth;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.loginmain);
        tempButton = findViewById(R.id.tempButton);
        // forgotPassword = findViewById(R.id.forgotPasswordLink);

        //FEO introduced this code make the register button respond to clicks and open the register activity.


        auth = FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() { // whenever login button is clicked
            @Override
            public void onClick(View view) {
                String txt_email = email.getText().toString(); // grabbing the inputed email and converting to string variable
                String txt_password = password.getText().toString();
                //here we are calling the method and passing through two string values.
                loginUser(txt_email, txt_password);
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

    private void loginUser(final String email, final String password) {
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override

            public void onSuccess(AuthResult authResult) {
                Toast.makeText(MainActivity.this, "You have successfully logged into your account", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, DashActivity.class));
                finish();
            }
        });

    }

    public void buttonTempOnClick(View V) {
        Intent i = new Intent(getApplicationContext(), DashActivity.class);
        startActivity(i);
    }
}