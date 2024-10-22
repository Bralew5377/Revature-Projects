package com.revature.DAOs;

import com.revature.models.Athlete;
import com.revature.models.Event;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;

public class AthleteDAO implements AthleteDAOInterface {

    @Override
    public Athlete insertAthlete(Athlete ath) {

        try (Connection conn = ConnectionUtil.getConnection()) {
            // SQL Statement
            String sql = "INSERT INTO athletes (first_name, last_name, event_id_fk) VALUES (?,?,?)";

            // prepared statement
            PreparedStatement ps = conn.prepareStatement(sql);

            // set parts of the statement to the values we are setting in the sql command
            ps.setString(1, ath.getFirst_name());
            ps.setString(2, ath.getLast_name());
            ps.setInt(3, ath.getEvent_id_fk());

            // execute the statement
            ps.executeUpdate();

            // return the new Athlete Object
            return ath;


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't insert athlete");
        }

        // Catch all if the insert fails, we'll return null
        return null;
    }

    @Override
    public ArrayList<Athlete> getAllAthletes() {

        // Try to open a Connection to the DB
        try (Connection conn = ConnectionUtil.getConnection()) {

            // A String that represents our SQL query
            String sql = "SELECT * FROM athletes";

            // we can use a statment object instead of a preparedStatement because there are no variables
            Statement s = conn.createStatement();

            //Execute the query, save the results in ResultSet
            ResultSet rs = s.executeQuery(sql);

            //we need am Array List to hold our Athletes
            ArrayList<Athlete> athletes = new ArrayList<>();

            //loop through the result set with rs.next()
            // rs.next will retirn false when there are no more rows in the result set
            while (rs.next()) {
                // for every Athlete found, add it to the Array list
                // we will need to instantiate a new Athlete object for each row in the result set
                //we can get each piece of Athlete data with rs.get... methods

                Athlete a = new Athlete(
                        rs.getInt("athlete_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        null
                );
                // lets use EventDAO.getEventById to get the Event for this Athlete
                EventDAO eDAO = new EventDAO();
                Event event = eDAO.getEventById(rs.getInt("event_id_fk"));

                //get a new event by using the event id fk from the database
                //Now that we have the event, we can set it on the Athlete object
                a.setEvent(event);

                // add the athlete to the array list
                athletes.add(a);

            }// end of while loop
            return athletes;


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't get all athletes");
        }


        // Catch all if the select fails, we'll return null
        return null;
    }

    @Override
    public Athlete getAthleteById(int athlete_id) {

            // Try to open a Connection to the DB
            try (Connection conn = ConnectionUtil.getConnection()) {

                // A String that represents our SQL query
                //NOTE the "?", which means it's a variable that we need to fill in
                String sql = "SELECT * FROM athletes WHERE athlete_id = ?";

                //we need a prepared statement to fill in the variable (id)
                // it takes our SQL string from above
                PreparedStatement ps = conn.prepareStatement(sql);

                //We can now use the id parameter to fill in the query, ps.set() methods ps.setInt( "set which of question marks to fill in", id)
                ps.setInt(1, athlete_id);

                //Execute the query, save the results in ResultSet
                ResultSet rs = ps.executeQuery(); // executing the query stored in the PreparedStatement

                //Extract the data from the ResultSet into a Role object
                // if there is a value in the next index of role set
                //we can use rs.get to get the values from the result set
                //rs.getInt("role_id")
                if (rs.next()) {
                    // then extract the data into a Java Role Object!  Using hte all args Constructor
                    Athlete a = new Athlete(
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getInt("event_id_fk")
                    );
                    //return the new event
                    return a;

                }


            } catch (SQLException e) {
                e.printStackTrace(); // tells us what went wrong
                System.out.println("Couldn't get athlete by id");
            }
            //this is just a catch-all. if nother get returned ( bad query, etc ) we return null
            return null;

    }



    @Override
    public String deleteAthleteById(int athlete_id) {


        return null;
    }





}
