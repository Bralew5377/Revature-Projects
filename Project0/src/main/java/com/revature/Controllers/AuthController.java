package com.revature.Controllers;

import com.revature.DAOs.AuthDAO;
import com.revature.models.Athlete;
import com.revature.models.LoginDTO;
import io.javalin.http.Handler;
import jakarta.servlet.http.HttpSession;

public class AuthController {

    // Instantiate the AuthDAO
    AuthDAO aDAO = new AuthDAO();

    // HTTP Session object to store user info after successful login
    // this object will be initialized upon successful login, letting the user access the app
    public static HttpSession ses;


    public Handler loginHandler = ctx -> {

        //extract the request body as a LoginDTO object
        LoginDTO lDTO = ctx.bodyAsClass(LoginDTO.class);

        // use the loging dto data to send to the DAO and try to log in
        Athlete loggedInAthlete = aDAO.login(lDTO.getAthlete_id(), lDTO.getFirst_name());

        // the DAO will either return an athlete object ( success ) or null (login failed)
        if(loggedInAthlete != null){
            //create a session object
            ses = ctx.req().getSession();

            // store user info with the setAttribute() method
            ses.setAttribute("first_name", loggedInAthlete.getFirst_name());
            ses.setAttribute("last_name", loggedInAthlete.getLast_name());




            // ok.... how do we access the data in the session?
            System.out.println("Employee first name :" + ses.getAttribute("first_name") +" has logged in");



            // send a success message
            ctx.status(200);
            ctx.result("Login Successful! Welcome," + ses.getAttribute("first_name") +" "+ ses.getAttribute("last_name"));

        }else{
            ctx.status(401); // 401 Unauthorized
            ctx.result("Login Failed! Try again.");
        }



    };



}
