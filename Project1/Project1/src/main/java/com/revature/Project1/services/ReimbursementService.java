package com.revature.Project1.services;

import com.revature.Project1.daos.EmployeeDAO;
import com.revature.Project1.daos.ReimbursementDAO;
import com.revature.Project1.models.DTOs.IncomingReimbursementDTO;
import com.revature.Project1.models.Employee;
import com.revature.Project1.models.Reimbursement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReimbursementService {

    private ReimbursementDAO reimbursementDAO;
    ;
    private EmployeeDAO employeeDAO;

    @Autowired
    public ReimbursementService(ReimbursementDAO reimbursementDAO, EmployeeDAO employeeDAO) {
        this.reimbursementDAO = reimbursementDAO;
        this.employeeDAO = employeeDAO;
    }

    public Reimbursement createReimbursement(IncomingReimbursementDTO reimbursementDTO) {

        Reimbursement newReimbursement = new Reimbursement(0, reimbursementDTO.getDescription(), reimbursementDTO.getAmount(), "PENDING", null);

        Optional<Employee> e = employeeDAO.findById(reimbursementDTO.getUserId());
        if (e.isEmpty()) {
            throw new IllegalArgumentException("No employee found with id: " + reimbursementDTO.getUserId());
        } else {
            newReimbursement.setEmployee(e.get());
            return reimbursementDAO.save(newReimbursement);
        }
    }

    public List<Reimbursement> getAllReimbursements() {

        return reimbursementDAO.findAll();
    }

    public List<Reimbursement> getReimbursementsByUserId(int userId) {

        //TODO: error handling - incoming id, make sure its E,ployee

        return reimbursementDAO.findByEmployeeUserId(userId);
    }

    public Reimbursement updateReimbursementStatus(int ReimbId, String newStatus) {
        // TODO: Error handling - Make sure role is valid , make sure role is not empty and either Admin or Employee

        Reimbursement r = reimbursementDAO.findByReimbId(ReimbId);
                if(r == null){
                    throw new IllegalArgumentException("No reimbursement found with id: " + ReimbId);
                }else {
                    r.setStatus(newStatus);
                }
        reimbursementDAO.save(r);

        return r;
    }

    public List<Reimbursement> getAllPendingReimbursements() {
        List<Reimbursement> allReimbursements = reimbursementDAO.findAll();
        List<Reimbursement> pendingReimbursements = new ArrayList<>();
        for (Reimbursement reimbursement : allReimbursements) {
            if (reimbursement.getStatus().equals("PENDING")) {
                pendingReimbursements.add(reimbursement);
            }
        }
        return pendingReimbursements;
    }

    public Reimbursement getReimbursementById(int reimbId) {
        return reimbursementDAO.findByReimbId(reimbId);
    }

}