package com.egcoding.acmapp;

public class CustomListAdapter {

    private String eventname;
    private String eventtime;
    private String eventdescription;
    private String eventLocation;

    public CustomListAdapter(String eventName, String eventTime, String eventDescription, String eventLocation) {
        this.eventname = eventName;
        this.eventtime = eventTime;
        this.eventdescription = eventDescription;
        this.eventLocation = eventLocation;
    }

    public String getTitle() {
        return eventname;
    }

    public String getTime() {
        return eventtime;
    }

    public String getDescription() {
        return eventdescription;
    }

    public String getLocation() {return eventLocation;}
}

