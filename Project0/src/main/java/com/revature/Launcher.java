package com.revature;

import com.revature.DAOs.AthleteDAO;
import com.revature.DAOs.EventDAO;
import com.revature.models.Athlete;
import com.revature.models.Event;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Launcher {

    public static void main(String[] args) {




        try(Connection conn = ConnectionUtil.getConnection()) {

            System.out.println("Connection successful :)");

        } catch (SQLException e) {
            e.printStackTrace();// this is what tells us our error message
            System.out.println("Connection failed :(");
        }

        // Testing DAO methods

        // get event by id
            EventDAO eDAO = new EventDAO();
            Event event = eDAO.getEventById(22);
            System.out.println(event);

        // update event title and type

        System.out.println(eDAO.updateEventTitleAndType(23, "Heptathlon", "Combined Events"));



        // insert athlete

        AthleteDAO aDAO = new AthleteDAO();
        Athlete a = new Athlete("Hakari", "Kinji", 17);
        System.out.println(aDAO.insertAthlete(a));


        // get all athletes
        ArrayList<Athlete> athletes = aDAO.getAllAthletes();
        for(Athlete athlete : athletes) {
            System.out.println(athlete);
        }



        }
    }

