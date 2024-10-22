package com.revature.models;


// Data Transfer Object to Login

public class LoginDTO {

    // Store username and password for this we will use Athlete id and firstname
    private int athlete_id;
    private String first_name;

    // boilerplate code

    // no args, all agrs, getters and setters , to String


    public LoginDTO() {
    }

    public LoginDTO(int athlete_id, String first_name) {
        this.athlete_id = athlete_id;
        this.first_name = first_name;
    }

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

    @Override
    public String toString() {
        return "LoginDTO{" +
                "athlete_id=" + athlete_id +
                ", first_name='" + first_name + '\'' +
                '}';
    }
}
