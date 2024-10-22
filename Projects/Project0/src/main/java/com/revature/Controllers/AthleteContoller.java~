package com.revature.Controllers;

import com.revature.DAOs.AthleteDAO;
import com.revature.models.Athlete;
import com.revature.models.Event;
import io.javalin.http.Handler;

import java.util.ArrayList;

public class AthleteContoller {

    AthleteDAO aDAO = new AthleteDAO();

    //this handler will handle GET requests to /athletes
    public Handler getAthletesHandler = (ctx) -> {


        ArrayList<Athlete> athletes = aDAO.getAllAthletes();

        // we can .json to convert Java objects to JSON
        ctx.json(athletes);

        // we can .status to set the status code of the response
        ctx.status(200);//ok

    };


    // This handler will handle POST requests to /athletes
    public Handler insertAthleteHandler = (ctx) -> {

        // we have json coming in so we convert it to java
        Athlete newAthlete = ctx.bodyAsClass(Athlete.class);

        // error handling with .trim()
        if (newAthlete.getFirst_name() == null || newAthlete.getFirst_name().trim().isEmpty()) {
            ctx.result("First name is required!!");
            ctx.status(400); // bad request
        } else if (newAthlete.getLast_name() == null || newAthlete.getLast_name().trim().isEmpty()) {
            ctx.result("Last name is required!!");
            ctx.status(400); // bad request
        } else { // if the if's didnt trigger then the athlete is good
            Athlete insertedAthlete = aDAO.insertAthlete(newAthlete);
            ctx.status(201);// created
            ctx.json(insertedAthlete);

        }
    };


    public Handler getAthleteByIdHandler = (ctx) -> {

        // extract the path parameter from the HTPP request URL
        int athlete_id = Integer.parseInt(ctx.pathParam("id"));

        //INSTANTIATE an event that holds the data from the specific role id
       Athlete athlete = aDAO.getAthleteById(athlete_id);

        // make sure the event that comes back isnt null if so then send a 404 response
        if (athlete_id <= 0) {
            ctx.result("ID must be greater than 0");
            ctx.status(400); // bad request
        } else if (athlete == null) {
            ctx.result("Event ID:" + athlete_id + " not found");
            ctx.status(404); // not found
        } else {
            ctx.json(athlete);
            ctx.status(200); // ok
        }

    };


}