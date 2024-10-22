package com.revature.models;

// This models each individual athlete in the database
public class Athlete {

    private int athlete_id;

    private String first_name;

    private String last_name;

    // have the event in the athlete object so we dont have to access events just to see details on this athletes event
    private Event event;

    // Useful when inserting athletes into the database - we can skip making the event object when inserting new athletes
    private int event_id_fk;

    // Boilerplate code
    // no args all args and getters and setters and toString


    public Athlete() {
    }

    public Athlete(int athlete_id, String first_name, String last_name, Event event) {
        this.athlete_id = athlete_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.event = event;
    }

    // all args minus id no id since its serial, the database will make it for us


    public Athlete(String first_name, String last_name, int event_id_fk) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.event_id_fk = event_id_fk;
    }


    // getters and setters


    public int getAthlete_id() {
        return athlete_id;
    }

    public void setAthlete_id(int athlete_id) {
        this.athlete_id = athlete_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public int getEvent_id_fk() {
        return event_id_fk;
    }

    public void setEvent_id_fk(int event_id_fk) {
        this.event_id_fk = event_id_fk;
    }

    // toString



    @Override
    public String toString() {
        return "Athlete{" +
                "athlete_id=" + athlete_id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", event=" + event +
                ", event_id_fk=" + event_id_fk +
                '}';
    }
}
