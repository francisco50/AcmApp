package com.egcoding.acmapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class DashActivity extends AppCompatActivity {
    CardView profile, calender, labs, newsletter, podcast, events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);

        profile = findViewById(R.id.profileCard);
        calender = findViewById(R.id.calenderCard);
        labs = findViewById(R.id.labCard);
        newsletter = findViewById(R.id.newsletterCard);
        podcast = findViewById(R.id.podcastCard);
        events = findViewById(R.id.eventsCard);


        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void buttonProfileOnClick(View V) {
        Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivity(i);

    }

    public void buttonCalenderOnClick(View V) {
        Intent i = new Intent(getApplicationContext(), CalenderActivity.class);
        startActivity(i);

    }

    public void buttonLabsOnClick(View V) {
        Intent i = new Intent(getApplicationContext(), LabsActivity.class);
        startActivity(i);
    }

    public void buttonNewsOnClick(View V) {
        Intent i = new Intent(getApplicationContext(), NewsLetterActivity.class);
        startActivity(i);
    }

    public void buttonPodcastOnClick(View V) {
        Intent i = new Intent(getApplicationContext(), PodcastActivity.class);
        startActivity(i);
    }

    public void buttonEventsOnClick(View V) {
        Intent i = new Intent(getApplicationContext(), EventsActivity.class);
        startActivity(i);
    }
}