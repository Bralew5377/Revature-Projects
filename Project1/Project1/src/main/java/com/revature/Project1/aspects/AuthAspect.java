package com.revature.Project1.aspects;


import com.revature.Project1.controllers.AuthController;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect //this makes a class an aspect - a class that can trigger functionality at any point in our code
@Component
public class AuthAspect {


    //An Advice is the functionality that an aspect can trigger

    //An advice that checks if the user is logged in before they can call UserController methods
    //EXCEPT for the registerUser method - anyone should be able to register
    @Before("execution(* com.revature.Project1.controllers.EmployeeController.*(..)) " +
            "&& !execution(* com.revature.Project1.controllers.EmployeeController.registerEmployee(..))")
    public void checkLogin() {

        if(AuthController.session == null){
            throw new IllegalArgumentException("You must be logged in to do this!");
        }
        /*The Exception will not get handled appropriately...
        because this is checked before any controller method runs
        (thus the handler in the controller wont catch it )

        we could do a global exception handler with @ControllerAdvice
        well handle errors on the front in the exact same way. so on biggie.
         */
    }

    //An Advie that checks for admin privileges before calling methods with @AdminOnly
    @Before("@annotation(com.revature.Project1.aspects.ManagerOnly)")
    public void checkAdmin() {

        //if the logged in user does not have a role.equals("Manager") throw an exception
        if(!AuthController.session.getAttribute("role").equals("Manager")) {
            throw new IllegalArgumentException("You must be a Manager to do this!");
        }
    }
}
