package com.revature.Project1.models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;
import java.util.List;

@Component //1 of 4 stereotype annotations. Registers this class as a Spring Bean
@Entity //This Class will be a DB table thanks to Spring Data JPA
@Table(name = "reimbursements") //This lets us change the name of our DB table
public class Reimbursement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reimbId;


    @Column(nullable = false)
    private String description;


    @Column(nullable = false)
    private double amount;



    @Column(nullable = false)
    private String status;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId") //this links our FK to the PK in User (name Names the column!)
    private Employee employee;

    //Boiler plate


    public Reimbursement() {
    }

    public Reimbursement(int reimbId, String description, double amount, String status, Employee employee) {
        this.reimbId = reimbId;
        this.description = description;
        this.amount = amount;
        this.status = status;
        this.employee = employee;
    }

    public int getReimbId() {
        return reimbId;
    }

    public void setReimbId(int reimbId) {
        this.reimbId = reimbId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "reimbId=" + reimbId +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", employee=" + employee +
                '}';
    }

}
