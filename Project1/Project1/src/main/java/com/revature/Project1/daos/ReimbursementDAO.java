package com.revature.Project1.daos;

import com.revature.Project1.models.Reimbursement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReimbursementDAO extends JpaRepository<Reimbursement, Integer> {

    public List<Reimbursement> findByEmployeeUserId(int userId);

    public Reimbursement findByReimbId(int reimbId);

     List<Reimbursement> findByStatus(String status);


}
