package com.egcoding.acmapp;

import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;

public class DataHolder {
    private static ArrayList<Event> events = new ArrayList<Event>();
    private static Long eventRequestMinTime = Long.valueOf(60000);//miliseconds, delay sending new request in 1 minutes
    private static Long startRequestTime = Long.valueOf(0);

    static Boolean canUpdateEventData() {
        Long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - startRequestTime > eventRequestMinTime)
            return true;
        return false;
    }

    static Long getEventRequestMinTime() {
        return eventRequestMinTime;
    }

    static void setEventRequestMinTimeT(Long time) {
        eventRequestMinTime = time;
    }

    static void setStartRequestTime() {
        startRequestTime = Calendar.getInstance().getTimeInMillis();
    }

    static void clearEvents() {
        events.clear();
    }

    static ArrayList<Event> getEvents() {
        return events;
    }

    static void setEvent(Event event) {
        events.add(event);
    }

    static void printData() {
        for (Event event: events) {
            Log.d("eventName", event.name);
        }


    }
}
