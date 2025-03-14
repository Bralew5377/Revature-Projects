package com.revature.utils;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//This Class is where we manage and establish our database connection

public class ConnectionUtil {

    //This method will return an object of type Connection,
    //...which we'll use to interact with our database
    public static Connection getConnection() throws SQLException {

        //First we need to register our PostgreSQL driver
        //This process makes the application aware of what flavor of SQL we're using
        try {
            Class.forName("org.postgresql.Driver"); //searching for the postgres driver, which we have as a dependency
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); //This tells in the console us what went wrong
            System.out.println("problem occurred locating driver");
        }


        //Use our database credentials to establish a database connection
        //Hardcoded for now... BAD! we can change this to hide them in the Environment Variables

        //I'm going to put the credentials in Strings, and use those strings in a method that gets connections
        String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=project0";
        String username = "postgres";
        String password = "password";

        //This return statement is what returns our actual database Connection object
        //Note how this getConnection() method has a return type of Connection
        return DriverManager.getConnection(url, username, password);


    }

}