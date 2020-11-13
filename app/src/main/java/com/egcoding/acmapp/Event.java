package com.egcoding.acmapp;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

public class Event {
    public String id;
    public String name;
    public String description;
    public String location;
    public String startDate;
    public String endDate;
    public String startTime;
    public String endTime;

    public void setData(JSONObject data) throws JSONException {
        id = data.getString("id");
        name = data.getString("name");
        description = data.getString("description");
        location = data.getString("location");
        startDate = data.getString("startDate");
        endDate = data.getString("endDate");
        startTime = data.getString("startTime");
        endTime = data.getString("endTime");
    }
}