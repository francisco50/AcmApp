package com.egcoding.acmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CalenderActivity extends AppCompatActivity {

    CustomCalendarView customCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        customCalendarView = (CustomCalendarView)findViewById(R.id.custom_calendar_view);
    }
}