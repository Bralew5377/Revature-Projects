package com.revature.Project1.controllers;

import com.revature.Project1.aspects.ManagerOnly;
import com.revature.Project1.models.DTOs.OutgoingEmployeeDTO;
import com.revature.Project1.models.Employee;
import com.revature.Project1.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@CrossOrigin
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping //post request to /employee
    public ResponseEntity<Employee> registerEmployee(@RequestBody Employee newEmployee) {

        Employee e = employeeService.register(newEmployee);

        return ResponseEntity.status(201).body(e);

    }

    @ManagerOnly
    @GetMapping //get request to /employees
    public ResponseEntity<List<OutgoingEmployeeDTO>> getAllEmployees() {

        List<OutgoingEmployeeDTO> employees = employeeService.getAllEmployees();

        return ResponseEntity.ok(employees);
    }

    @ManagerOnly
    @GetMapping("/{username}") //get request to /employees/{username}
    public ResponseEntity<?> getEmployeeByUsername(@PathVariable String username) {

        if (employeeService.getEmployeeByUsername(username) == null) {
            return ResponseEntity.status(404).body("No employee found with username: " + username);
        }

        return ResponseEntity.ok(employeeService.getEmployeeByUsername(username));
    }


    //Admin Functions=====================================
    @ManagerOnly
    @PatchMapping("/{userId}") //patch request to /employees/{userId}
    public ResponseEntity<Employee> updateEmployeeRole(@PathVariable int userId, @RequestBody String newRole) {

        return ResponseEntity.status(202).body(employeeService.updateEmployeeRole(userId, newRole));
    }
    @ManagerOnly
    @DeleteMapping("/{userId}") //DELETE requests to /users/{userId} will come here
    public ResponseEntity<Employee> deleteEmployeeById(@PathVariable int userId) {

        return ResponseEntity.ok(employeeService.deleteEmployeeById(userId));
    }


    //Exception Handlers==================================

    //Exception Handler for IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e) {
        //Return a 400 (BAD REQUEST) status code with the exception message
        return ResponseEntity.status(400).body(e.getMessage());


    }

}
