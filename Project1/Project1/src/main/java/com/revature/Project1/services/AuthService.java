package com.revature.Project1.services;


import com.revature.Project1.controllers.AuthController;
import com.revature.Project1.daos.AuthDAO;
import com.revature.Project1.models.DTOs.LoginDTO;
import com.revature.Project1.models.DTOs.OutgoingEmployeeDTO;
import com.revature.Project1.models.Employee;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private AuthDAO aDAO;

    @Autowired
    public AuthService(AuthDAO aDAO) {
        this.aDAO = aDAO;
    }

    public OutgoingEmployeeDTO login(LoginDTO LDTO, HttpSession session) {

        Employee e =aDAO.findByUsernameAndPassword(LDTO.getUsername(), LDTO.getPassword());
        if (e == null) {
            throw new IllegalArgumentException("Invalid username/password");
        }

        AuthController.session = session;

        AuthController.session.setAttribute("userId", e.getUserId());
        AuthController.session.setAttribute("username", e.getUsername());
        AuthController.session.setAttribute("role", e.getRole());

        return new OutgoingEmployeeDTO(e.getUserId(), e.getFirstName(), e.getLastName(), e.getUsername(), e.getRole());

    }









}
