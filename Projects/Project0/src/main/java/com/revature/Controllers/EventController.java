package com.revature.Controllers;

import com.revature.DAOs.EventDAO;
import com.revature.models.Athlete;
import com.revature.models.Event;
import io.javalin.http.Handler;

import java.util.ArrayList;

public class EventController {

    // we need and event DAO to use its methods
    EventDAO eDAO = new EventDAO();

    // this handler will handle GET requests to / events

    public Handler getEventByIdHandler = (ctx) -> {

        // extract the path parameter from the HTPP request URL
        int event_id = Integer.parseInt(ctx.pathParam("id"));

        //INSTANTIATE an event that holds the data from the specific role id
        Event event = eDAO.getEventById(event_id);

        // make sure the event that comes back isnt null if so then send a 404 response
        if (event_id <= 0) {
            ctx.result("ID must be greater than 0");
            ctx.status(400); // bad request
        } else if (event == null) {
            ctx.result("Event ID:" + event_id + " not found");
            ctx.status(404); // not found
        } else {
            ctx.json(event);
            ctx.status(200); // ok
        }

    };

    // handler to update an event
    public Handler updateEventTitleAndTypeHandler = (ctx) -> {
        // extract the path parameter from the HTPP request URL
        int event_id = Integer.parseInt(ctx.pathParam("id"));

        // get the first line of the body
        String newTitle = ctx.body().lines().findFirst().get();
        // get the second line of the body
        String newType = ctx.body().lines().skip(1).findFirst().get();

        // call the updateEventTitleAndType method in the eventDAO

        // user input validation
        if (event_id <= 0) {
            ctx.result("ID must be greater than 0");
            ctx.status(400); // bad request
        } else if (newTitle == null) {
            ctx.result("Event Title cannot be null");
            ctx.status(400); // bad request
        } else if (newType == null) {
            ctx.result("Event Type cannot be null");
            ctx.status(400); // bad request
        } else {
            String result = eDAO.updateEventTitleAndType(event_id, newTitle, newType);
            ctx.result(result);
            ctx.status(200); // ok
        }
    };
    // This handler will handle POST requests to /Events
    public Handler insertEventHandler = (ctx) -> {

        // we have json coming in so we convert it to java
        Event newEvent = ctx.bodyAsClass(Event.class);

        // error handling with .trim()
        if (newEvent.getEvent_title() == null || newEvent.getEvent_title().trim().isEmpty()) {
            ctx.result("First name is required!!");
            ctx.status(400); // bad request
        } else if (newEvent.getEvent_type() == null || newEvent.getEvent_type().trim().isEmpty()) {
            ctx.result("Last name is required!!");
            ctx.status(400); // bad request
        } else { // if the if's didnt trigger then the athlete is good
            Event insertedEvent = eDAO.insertEvent(newEvent);
            ctx.status(201);// created
            ctx.json(insertedEvent);

        }
    };
    // handler to delete an event
    public Handler deleteEventByIdHandler = (ctx) -> {
        // extract the path parameter from the HTPP request URL
        int event_id = Integer.parseInt(ctx.pathParam("id"));

        // user input validation
        if (event_id <= 0) {
            ctx.result("ID must be greater than 0");
            ctx.status(400); // bad request
        } else if (eDAO.getEventById(event_id)== null) {
            ctx.result("Event ID:" + event_id + " not found");
            ctx.status(404); // not found }
        } else {
            String result = eDAO.deleteEventById(event_id);
            ctx.result(result);
            ctx.status(200); // ok
        }

    };

    public Handler getAllEventsHandler = (ctx) -> {


        ArrayList<Event> events = eDAO.getAllEvents();

        // we can .json to convert Java objects to JSON
        ctx.json(events);

        // we can .status to set the status code of the response
        ctx.status(200);//ok

    };



}
