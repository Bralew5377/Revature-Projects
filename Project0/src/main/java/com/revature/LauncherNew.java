package com.revature;

import com.revature.Controllers.AthleteContoller;
import com.revature.Controllers.AuthController;
import com.revature.Controllers.EventController;
import com.revature.models.Athlete;
import io.javalin.Javalin;

public class LauncherNew {

    public static void main(String[] args) {

        // javalin set up syntax
        var app = Javalin.create().start(7001);

 //log in
        app.before("/athletes", ctx ->{
            if(AuthController.ses == null){
                ctx.status(401);// Unauthorized
                ctx.result("You must log in before doing this!");
                ctx.skipRemainingHandlers();
            }

        });

// very basic callable response
        app.get("/",ctx -> ctx.result("Hello Javalin and Postman"));

// a new controller
        AthleteContoller ac = new AthleteContoller();
        EventController ec = new EventController();
        AuthController ac2 = new AuthController();

        // Requests

        // Athletes

        app.get("/athletes/{id}", ac.getAthleteByIdHandler);
        app.get("/athletes", ac.getAthletesHandler);
        app.post("/athletes", ac.insertAthleteHandler);

        //todo: delete athlete by id

        // Events

        app.get("/events", ec.getAllEventsHandler);
        app.post("/events", ec.insertEventHandler);
        app.get("/events/{id}", ec.getEventByIdHandler);
        app.patch("/events/{id}", ec.updateEventTitleAndTypeHandler);
        app.delete("/events/{id}", ec.deleteEventByIdHandler);

        // Auth
        app.post("/auth", ac2.loginHandler);


















































    }
}
