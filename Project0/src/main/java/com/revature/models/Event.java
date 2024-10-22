package com.revature.models;

public class Event {

    //Mirror the table in the database
    private int event_id;
    private String event_title;
    private String event_type;

    // Boilerplate code

    // no args constructor
    public Event() {
    }

    // all args constructor
    public Event(int event_id, String event_title, String event_type) {
        this.event_id = event_id;
        this.event_title = event_title;
        this.event_type = event_type;
    }

    // getters and setters


    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public String getEvent_title() {
        return event_title;
    }

    public void setEvent_title(String event_title) {
        this.event_title = event_title;
    }

    public String getEvent_type() {
        return event_type;
    }

    public void setEvent_type(String event_type) {
        this.event_type = event_type;
    }

    // toString
    @Override
    public String toString() {
        return "Event [event_id=" + event_id + ", event_title=" + event_title + ", event_type=" + event_type + "]";
    }
}
