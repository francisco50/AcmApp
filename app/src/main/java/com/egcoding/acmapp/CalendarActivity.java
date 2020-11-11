package com.egcoding.acmapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CalenderActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private CalendarView mCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCalendarView = findViewById(R.id.calendarView);
        Button getEventButton = findViewById(R.id.buttonGetEvent);

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String date = (i1 + 1) + "/" + i2 + "/" + i;
                Log.d(TAG, "onSelectedDateChange: mm/dd/yyyy: " + date);

                Intent intent = new Intent(CalenderActivity.this, CalendarEventInfoActivity.class);
                intent.putExtra("date", date);
                CalenderActivity.this.startActivity(intent);
            }
        });
        getEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCalendarData();
            }}
        );

    }

    private void getCalendarData() {

        String register_url = "http://10.0.2.2:3000/api/v1/calendar";
        //Launch version
        //String register_url = "https://acm-app-backend.herokuapp.com/api/v1/calendar";
        //prepare data

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET,
                register_url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            @NonNull final String status = response.getString("success");
                            Log.d("Event Data", response.toString());
                            if (status == "true") {
                                @NonNull final JSONArray jsonArray = (JSONArray) response.get("data");
                                DataHolder.events.clear();
                                for (int index = 0; index < jsonArray.length(); ++index) {
                                    JSONObject eventObject = jsonArray.getJSONObject(index);
                                    Log.d("Event name", eventObject.getString("name"));
                                    Event newEvent = new Event();
                                    newEvent.setData(eventObject);
                                    DataHolder.events.add(newEvent);
                                }
                                DataHolder.printData();
                                Toast.makeText(MainActivity.this, "Get all events for calendar sucessfully", Toast.LENGTH_SHORT).show();
                            } else {
                                @NonNull final String error = response.getString("error");
                                Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
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
                Toast.makeText(MainActivity.this, "Volley error", Toast.LENGTH_SHORT).show();
            }
        });
        //make request by adding it to RequestQueue
        Volley.newRequestQueue(MainActivity.this).add(jsonRequest);
    }//---end function---

}