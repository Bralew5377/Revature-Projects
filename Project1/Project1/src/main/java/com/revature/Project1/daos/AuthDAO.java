package com.revature.Project1.daos;


import com.revature.Project1.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthDAO extends JpaRepository<Employee, Integer> {

    Employee findByUsernameAndPassword(String username, String password);
}
