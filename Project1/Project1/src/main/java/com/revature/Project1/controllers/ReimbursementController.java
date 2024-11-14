package com.revature.Project1.controllers;

import com.revature.Project1.models.DTOs.IncomingReimbursementDTO;
import com.revature.Project1.models.Reimbursement;
import com.revature.Project1.services.ReimbursementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reimbursements")
@CrossOrigin
public class ReimbursementController {

    private ReimbursementService reimbursementService;

    @Autowired
    public ReimbursementController(ReimbursementService reimbursementService){
        this.reimbursementService = reimbursementService;
    }

    @PostMapping
    public ResponseEntity<Reimbursement> createReimbursement(@RequestBody IncomingReimbursementDTO reimbursementDTO) {

        Reimbursement r = reimbursementService.createReimbursement(reimbursementDTO);

        return ResponseEntity.status(201).body(r);

    }


    @GetMapping
    public ResponseEntity<List<Reimbursement>> getAllReimbursements() {
        return ResponseEntity.ok(reimbursementService.getAllReimbursements());
    }

    @GetMapping("reimbId/{reimbId}")
    public ResponseEntity<Reimbursement> getReimbursementById(@PathVariable int reimbId) {
        return ResponseEntity.ok(reimbursementService.getReimbursementById(reimbId));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Reimbursement>> getReimbursementsByUserId(@PathVariable int userId) {
        return ResponseEntity.ok(reimbursementService.getReimbursementsByUserId(userId));
    }

    @PatchMapping("/{ReimbId}")
    public ResponseEntity<Reimbursement> updateReimbursementStatus(@PathVariable int ReimbId, @RequestBody String newStatus) {
        return ResponseEntity.status(202).body(reimbursementService.updateReimbursementStatus(ReimbId, newStatus));
    }

    @GetMapping("/pending")
    public ResponseEntity<List<Reimbursement>> getAllPendingReimbursements() {
        return ResponseEntity.ok(reimbursementService.getAllPendingReimbursements());
    }

    //Exception Handler (stole this from the UserController)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e){
        //Return a 400 (BAD REQUEST) status code with the exception message
        return ResponseEntity.status(400).body(e.getMessage());
    }

}
