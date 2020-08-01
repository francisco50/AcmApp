package com.egcoding.acmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button register; // creating button variable to connect to xml
    private Button login; // creating button variable to connect to xml
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = findViewById(R.id.RegisterButton); // links to xml register through ID
        login = findViewById(R.id.LoginButton); // links to xml login through ID

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class)); // links MainActivity to RegisterActivity
                finish();
            }
        });

        login.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));  // links MainActivity to LoginActivity
                finish();
            }
        }));


    }
}