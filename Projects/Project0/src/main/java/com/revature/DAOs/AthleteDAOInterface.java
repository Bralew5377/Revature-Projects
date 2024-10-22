package com.revature.DAOs;

// Just to layout what methods this Class will Implement

import com.revature.models.Athlete;
import com.revature.models.Event;

import java.util.ArrayList;

public interface AthleteDAOInterface {

    //A method to insert a new athlete
    Athlete insertAthlete(Athlete ath);

    // A method to get all Athletes
    ArrayList<Athlete> getAllAthletes();

    //  a method to return an athlete by id
    Athlete getAthleteById(int athlete_id);


    // a method to delete an event by id
    String deleteAthleteById(int athlete_id);


}
