package com.egcoding.acmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewsLetterActivity extends AppCompatActivity {

    Button firstEdition, secondEdition, thirdEdition, fourthEdition, fifthEdition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_letter);

        firstEdition = findViewById(R.id.firstEditionButton);
        secondEdition = findViewById(R.id.secondEditionButton);
        thirdEdition = findViewById(R.id.thirdEditionButton);
        fourthEdition = findViewById(R.id.fourthEditionButton);
        fifthEdition = findViewById(R.id.firstEditionButton);

    }

    public void buttonFirstEditionOnClick(View V) {
        Intent i = new Intent();
        i.setAction(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://drive.google.com/file/d/1NiTTzRfM325nzjWdWxm2XSsYZto6khJ4/view"));
        if (i.resolveActivity(getPackageManager()) != null) {
            startActivity(i);
        }
    }

    public void buttonSecondEditionOnClick(View V) {
        Intent i = new Intent();
        i.setAction(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://drive.google.com/file/d/1GIZEzW8bpEsNMy3XiHs8ALil2bbqYm5Q/view"));
        if (i.resolveActivity(getPackageManager()) != null) {
            startActivity(i);
        }
    }

    public void buttonThirdEditionOnClick(View V) {
        Intent i = new Intent();
        i.setAction(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://drive.google.com/file/d/1SvwYxvHYPimAaKnwEpibZ9X_M-nYJSrF/view"));
        if (i.resolveActivity(getPackageManager()) != null) {
            startActivity(i);
        }
    }

    public void buttonFourthEditionOnClick(View V) {
        Intent i = new Intent();
        i.setAction(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://drive.google.com/file/d/1g0-wQoGDdRKL8unAjFR0rd3MJK92ibRA/view"));
        if (i.resolveActivity(getPackageManager()) != null) {
            startActivity(i);
        }
    }

    public void buttonFifthEditionOnClick(View V) {
        Intent i = new Intent();
        i.setAction(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://drive.google.com/file/d/1abf7GrcbZ0T1P47jALeX-kqOsiCKdt3g/view"));
        if (i.resolveActivity(getPackageManager()) != null) {
            startActivity(i);
        }
    }
}
