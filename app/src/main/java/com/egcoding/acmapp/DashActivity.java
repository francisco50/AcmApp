package com.egcoding.acmapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class DashActivity extends AppCompatActivity {
    CardView profile, calendar, labs, newsletter, podcast, events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);

        profile = findViewById(R.id.profileCard);
        calendar = findViewById(R.id.calenderCard);
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

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //time limite to request new events is 1 minute
                //can be change using DataHolder.setEventRequestMinTime(miliseconds)
                if (DataHolder.canUpdateEventData()) {
                    DataHolder.setStartRequestTime();
                    getCalendarData();
                } else {
                    Log.d("GET EVENT CALENDAR",
                            String.format("You need to wait %d miliseconds before getting new events", DataHolder.getEventRequestMinTime()));
                    Intent intent = new Intent(DashActivity.this, CalendarActivity.class);
                    startActivity(intent);
                }

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



    private void getCalendarData() {



        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET,
                CustomAPI.getCalendarUrl(),null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            @NonNull final String status = response.getString("success");
                            Log.d("Event Data", response.toString());
                            if (status == "true") {
                                @NonNull final JSONArray jsonArray = (JSONArray) response.get("data");
                                DataHolder.clearEvents();
                                for (int index = 0; index < jsonArray.length(); ++index) {
                                    JSONObject eventObject = jsonArray.getJSONObject(index);
                                    Log.d("Event name", eventObject.getString("name"));
                                    Event newEvent = new Event();
                                    newEvent.setData(eventObject);
                                    DataHolder.setEvent(newEvent);
                                }
                                DataHolder.printData();
                                Toast.makeText(DashActivity.this, "Get all events for calendar sucessfully", Toast.LENGTH_SHORT).show();

                                //launch CalendarActivity
                                Intent intent = new Intent(DashActivity.this, CalendarActivity.class);
                                startActivity(intent);
                            } else {
                                @NonNull final String error = response.getString("error");
                                Toast.makeText(DashActivity.this, error, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Log.e("REGISTER", "Could not parse response " + response.toString());
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("REGISTER", error.getMessage());
                Toast.makeText(DashActivity.this, "Volley error", Toast.LENGTH_SHORT).show();
            }
        });
        //make request by adding it to RequestQueue
        Volley.newRequestQueue(DashActivity.this).add(jsonRequest);
    }//---end function---






}