package com.egcoding.acmapp;

public class CustomAPI {
    static private final String HEROKU_URL = "https://acm-app-backend.herokuapp.com/api/v1/";
    static private final String LOCAL_HOST_URL = "http://10.0.2.2:3000/api/v1/";
    static private String base_url = LOCAL_HOST_URL;

    static void setDevelopmentMode() {
        base_url = LOCAL_HOST_URL;
    }

    static void setPublishMode() {
        base_url = HEROKU_URL;
    }

    static public String getRegisterUrl() {
        return base_url + "register";
    }

    static public String getCalendarUrl() {
        return base_url + "calendar";
    }

    static public String getLoginUrl() {
        return base_url + "authenticate";
    }

    static public String getSetMembershipUrl() {
        return base_url + "setMembership";
    }
}
