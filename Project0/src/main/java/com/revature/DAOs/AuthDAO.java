package com.revature.DAOs;

// this DAO will handle log in


import com.revature.models.Athlete;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthDAO {


    public Athlete login(int athlete_id, String first_name) {

        try (Connection conn = ConnectionUtil.getConnection()) {

            //SQL Statement
            String sql = "SELECT * FROM athletes WHERE athlete_id = ? AND first_name = ?";

            // prepared statement
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, athlete_id);
            ps.setString(2, first_name);

            //execute the statement
            ResultSet rs = ps.executeQuery();

            EventDAO eDAO = new EventDAO();

            if (rs.next()) {
                Athlete a = new Athlete(
                        rs.getInt("athlete_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        eDAO.getEventById(rs.getInt("event_id_fk"))
                );

                return a;
            }


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(" Couldn't login user");

            return null;
        }


        return null;
    }


}
