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

        labs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashActivity.this, LabsActivity.class);
                startActivity(intent);
            }
        });

        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashActivity.this, CalenderActivity.class);
                startActivity(intent);
            }
        });

        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashActivity.this, EventsActivity.class);
                startActivity(intent);
            }
        });


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashActivity.this, ProfileActivity.class);
                startActivity(intent);

            }
        });

        newsletter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashActivity.this, NewsLetterActivity.class);
                startActivity(intent);
            }
        });


        podcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashActivity.this, PodcastActivity.class);
                startActivity(intent);
            }
        });




    }










}