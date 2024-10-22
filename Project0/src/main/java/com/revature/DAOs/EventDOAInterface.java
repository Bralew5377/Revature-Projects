package com.revature.DAOs;

import com.revature.models.Athlete;
import com.revature.models.Event;

import java.util.ArrayList;

public interface EventDOAInterface {

    // interface to define DAO methods

        //  a method to return an event by id
        Event getEventById(int event_id);


        // a method that updates an event
        String updateEventTitleAndType(int event_id, String event_title, String event_type);

        // a method to delete an event by id
        String deleteEventById(int event_id);

        //A method to insert a new event
        Event insertEvent(Event evt);

        // A method to get all Events
        ArrayList<Event> getAllEvents();


}
