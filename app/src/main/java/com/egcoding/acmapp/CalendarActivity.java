package com.egcoding.acmapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class CalendarActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private CalendarView mCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        mCalendarView = findViewById(R.id.calendarView);
        //DEBUG: This button will be removed
        Button getEventButton = findViewById(R.id.buttonGetEvent);

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String date = (i1 + 1) + "/" + i2 + "/" + i;
                Log.d(TAG, "onSelectedDateChange: mm/dd/yyyy: " + date);

                Intent intent = new Intent(CalendarActivity.this, CalendarEventInfoActivity.class);
                intent.putExtra("date", date);
                CalendarActivity.this.startActivity(intent);
            }
        });
        //DEBUG: This will be removed
        getEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCalendarData();
            }}
        );

    }

    //DEBUG: This will be removed
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
                                Toast.makeText(CalendarActivity.this, "Get all events for calendar sucessfully", Toast.LENGTH_SHORT).show();
                            } else {
                                @NonNull final String error = response.getString("error");
                                Toast.makeText(CalendarActivity.this, error, Toast.LENGTH_SHORT).show();
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
                Toast.makeText(CalendarActivity.this, "Volley error", Toast.LENGTH_SHORT).show();
            }
        });
        //make request by adding it to RequestQueue
        Volley.newRequestQueue(CalendarActivity.this).add(jsonRequest);
    }//---end function---


}