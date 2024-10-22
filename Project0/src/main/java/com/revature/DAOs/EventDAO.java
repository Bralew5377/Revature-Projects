package com.revature.DAOs;

import com.revature.models.Athlete;
import com.revature.models.Event;
import com.revature.utils.ConnectionUtil;


import java.sql.*;
import java.util.ArrayList;

//This is the class that will actually interact with the database

public class EventDAO implements EventDOAInterface {


    @Override
    public Event getEventById(int id) {

        // Try to open a Connection to the DB
        try (Connection conn = ConnectionUtil.getConnection()) {

            // A String that represents our SQL query
            //NOTE the "?", which means it's a variable that we need to fill in
            String sql = "SELECT * FROM events WHERE event_id = ?";

            //we need a prepared statement to fill in the variable (id)
            // it takes our SQL string from above
            PreparedStatement ps = conn.prepareStatement(sql);

            //We can now use the id parameter to fill in the query, ps.set() methods ps.setInt( "set which of question marks to fill in", id)
            ps.setInt(1, id);

            //Execute the query, save the results in ResultSet
            ResultSet rs = ps.executeQuery(); // executing the query stored in the PreparedStatement

            //Extract the data from the ResultSet into a Role object
            // if there is a value in the next index of role set
            //we can use rs.get to get the values from the result set
            //rs.getInt("role_id")
            if (rs.next()) {
                // then extract the data into a Java Role Object!  Using hte all args Constructor
                Event event = new Event(
                        rs.getInt("event_id"),
                        rs.getString("event_title"),
                        rs.getString("event_type")
                );
                //return the new event
                return event;

            }


        } catch (SQLException e) {
            e.printStackTrace(); // tells us what went wrong
            System.out.println("Couldn't get event by id");
        }
        //this is just a catch-all. if nother get returned ( bad query, etc ) we return null
        return null;
    }
    @Override
    public String updateEventTitleAndType(int id, String newTitle, String newType) {

        // Try to open a connection to the db
        try (Connection conn = ConnectionUtil.getConnection()) {

            //SQL Statement
            String sql = "UPDATE events SET event_title = ?, event_type = ? WHERE event_id = ?";

            //create a prepared statement to fill in hte variables
            PreparedStatement ps = conn.prepareStatement(sql);

            //ps.set to set the variables values
            ps.setString(1, newTitle);
            ps.setString(2, newType);
            ps.setInt(3, id);
            //execute the update
            ps.executeUpdate();

            //return the new title
            return newTitle;


        } catch (SQLException e) {
            e.printStackTrace(); // tells us what went wrong
            System.out.println("Couldn't update event ");
        }


        //catch all if the update fails, we'll return 0
        return null;

    }
        @Override
        public String deleteEventById(int event_id) {

            try (Connection conn = ConnectionUtil.getConnection()) {
                // SQL Statement
                String sql = "DELETE FROM events WHERE event_id = ?";

                // prepared statement
                PreparedStatement ps = conn.prepareStatement(sql);

                // set parts of the statement to the values we are setting in the sql command
                ps.setInt( 1, event_id);

                // execute the statement
                ps.executeUpdate();

                // return the deleted event
                return null;


            }catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Couldn't delete event");
            }

            // Catch all if the insert fails, we'll return null
            return null;
        }

    @Override
    public Event insertEvent(Event evt) {

            try (Connection conn = ConnectionUtil.getConnection()) {
                // SQL Statement
                String sql = "INSERT INTO events (event_title, event_type) VALUES (?,?)";

                // prepared statement
                PreparedStatement ps = conn.prepareStatement(sql);

                // set parts of the statement to the values we are setting in the sql command
                ps.setString( 1, evt.getEvent_title() );
                ps.setString( 2, evt.getEvent_type());

                // execute the statement
                ps.executeUpdate();

                // return the new Athlete Object
                return evt;


            }catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Couldn't insert event");
            }
        return null;
    }

    @Override
    public ArrayList<Event> getAllEvents() {

            // Try to open a Connection to the DB
            try (Connection conn = ConnectionUtil.getConnection()) {

                // A String that represents our SQL query
                String sql = "SELECT * FROM events";

                // we can use a statment object instead of a preparedStatement because there are no variables
                Statement s = conn.createStatement();

                //Execute the query, save the results in ResultSet
                ResultSet rs = s.executeQuery(sql);

                //we need am Array List to hold our Athletes
                ArrayList<Event> events = new ArrayList<>();

                //loop through the result set with rs.next()
                // rs.next will retirn false when there are no more rows in the result set
                while (rs.next()) {
                    // for every Athlete found, add it to the Array list
                    // we will need to instantiate a new Athlete object for each row in the result set
                    //we can get each piece of Athlete data with rs.get... methods

                    Event e = new Event(
                            rs.getInt("event_id"),
                            rs.getString("event_title"),
                            rs.getString("event_type")
                    );

                    // add the athlete to the array list
                    events.add(e);

                }// end of while loop
                return events;


            }catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Couldn't get all athletes");
            }




            return null;
    }



}

