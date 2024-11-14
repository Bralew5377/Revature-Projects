package com.revature.Project1.services;


import com.revature.Project1.daos.EmployeeDAO;
import com.revature.Project1.daos.ReimbursementDAO;
import com.revature.Project1.models.DTOs.OutgoingEmployeeDTO;
import com.revature.Project1.models.Employee;
import com.revature.Project1.models.Reimbursement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public Employee register(Employee newEmployee){

        //TODO: Check that the username is unique ( get user by username , see of its null)
        //employee e = findByUsername(newEmployee.getUsername());
        //if e is not null throw an exception because username is not unique

        // make sure the username is present in the New Employee ( TODO: password too )
        if(newEmployee.getUsername() == null || newEmployee.getUsername().isBlank()){
            throw new IllegalArgumentException("Username cannot be empty");
           }

        return employeeDAO.save(newEmployee);
    }

    public List<OutgoingEmployeeDTO> getAllEmployees(){

        List<Employee> employees = employeeDAO.findAll();

        List<OutgoingEmployeeDTO> outEmployee = new ArrayList<>();

        for(Employee e : employees){
            outEmployee.add(new OutgoingEmployeeDTO(e.getUserId(), e.getFirstName(), e.getLastName(), e.getUsername(), e.getRole()));
        }

        return outEmployee;


    }


    public Employee getEmployeeByUsername(String username){
        // a little error handling
        if(username == null || username.isBlank()){
            throw new IllegalArgumentException("Please search for a valid username!");
        }

        return employeeDAO.findByUsername(username);
    }

    public Employee updateEmployeeRole(int userId, String newRole){
        // TODO: Error handling - Make sure role is valid , make sure role is not empty and either Admin or Employee

        Employee e = employeeDAO.findById(userId).orElseThrow(() -> new IllegalArgumentException("No employee found with id: " + userId));

        e.setRole(newRole);

        employeeDAO.save(e);

        return e;
    }

    public Employee deleteEmployeeById(int userId){

        //TODO : Error handling - make sure the user id is greater than zero

        Employee employeeToDelete = employeeDAO.findById(userId).orElseThrow(() -> new IllegalArgumentException("No employee found with id: " + userId));

        employeeDAO.deleteById(userId);

        return employeeToDelete;

    }






}
