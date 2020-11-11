package com.egcoding.acmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class CalendarEventInfoActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<CustomListAdapter> list;
    EventListAdapter adapterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_event_info);

        listView = findViewById(R.id.eventList);

        //receive the clicked date on the Calendar
        String chosenDate = this.getIntent().getStringExtra("date");
        Log.d("CALENDAR CHOSEN DATE", chosenDate);
        listShow(chosenDate);

        adapterList = new EventListAdapter(this, list);
        if (list.isEmpty())
            Log.d("No event on date: ", chosenDate);
        listView.setAdapter((ListAdapter) adapterList);
    }

    private void listShow(String chosenDate) {
        list = new ArrayList<CustomListAdapter>();
        for (Event event: DataHolder.getEvents()) {
            Log.d("event Date", event.startDate);
            if (chosenDate.equals(event.startDate)) {
                String eventTime = String.format("%s to %s", event.startTime, event.endTime);
                list.add(new CustomListAdapter(event.name, eventTime, event.description, event.location));
            }
        }
    }
}
