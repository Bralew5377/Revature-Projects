import React from "react"
import { Button, Container, Table } from "react-bootstrap"
import { store } from "../../src/globalData/store"
import axios from "axios"


// This comopnent takes in 
export const ReimbursementTable:React.FC <{reimbursements:any[]}> = ({reimbursements}) => {
    
        //hypothetical methods for update pet and delete pet ( which both need pet id to find the pet )
        const updateReimbursement = async (id:number, reimbursement:any)=> {
             alert("Reimbursement with id " + id +" has been Updated")
             console.log(reimbursement.status)
             if(reimbursement.status ==='PENDING'){
                
                const response = await axios.patch("http://localhost:7005/reimbursements/" + id,"ACCEPTED", {
                   headers:{
                       "Content-Type":"text/plain"
                   }
                } )
                .then(()=> {alert("Success! 1")})
                 .catch((error)=>{alert("Failed! " + error.message)})
                window.location.reload()

           } else if(reimbursement.status ==='ACCEPTED'){
           
           const response = await axios.patch("http://localhost:7005/reimbursements/" + id, "DENIED", {
               headers:{
                   "Content-Type":"text/plain"
               }
            })
            .then(()=> {alert("Success! 2")})
            .catch((error)=>{alert("Failed! " + error.message)})
           window.location.reload()
        }else{  
           
            const response = await axios.patch("http://localhost:7005/reimbursements/" + id, "ACCEPTED", {
            headers:{
                "Content-Type":"text/plain"
            }
         })
         .then(()=> {alert("Success! 3")})
        .catch((error)=>{alert("Failed! " + error.message)})
        window.location.reload()}
        console.log(reimbursement.status)
            
        
      
        }

    
        if (store.loggedInUser.role == "Manager"){
            store.loggedInUser.role = "Manager"
    return(
        <Container>

                <Table>
                    <thead>
                        <tr>
                            <th>Reimbursement ID</th>
                            <th>Description</th>
                            <th>Amount</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                    {reimbursements.map((reimbursement:any)=>(
                        <tr>
                            <td>{reimbursement.reimbId}</td>
                            <td>{reimbursement.description}</td>
                            <td>${reimbursement.amount}.00</td>
                            <td>{reimbursement.status}</td>
                            <td><Button className= "btn-info" onClick={()=>{updateReimbursement(reimbursement.reimbId,reimbursement.status)}}>Update</Button></td>
                            
                        </tr>

                    ))}

                    </tbody>

                </Table>

        </Container>


    )

}else{

    return(
        <Container>

                <Table>
                    <thead>
                        <tr>
                            <th>Reimbursement ID</th>
                            <th>Description</th>
                            <th>Amount</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                    {reimbursements.map((reimbursement:any)=>(
                        <tr>
                            <td>{reimbursement.reimbId}</td>
                            <td>{reimbursement.description}</td>
                            <td>${reimbursement.amount}.00</td>
                            <td>{reimbursement.status}</td>
                        </tr>

                    ))}

                    </tbody>

                </Table>

        </Container>


    )
}

}