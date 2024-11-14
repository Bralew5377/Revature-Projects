import React from "react"
import { Button, Container, Table } from "react-bootstrap"
import { useNavigate } from "react-router-dom"
import { store } from '../../src/globalData/store';
import axios from "axios";


// This comopnent takes in 
export const EmployeeTable:React.FC <{employees:any[]}> = ({employees}) => {

    const navigate = useNavigate()

        //hypothetical methods for update pet and delete pet ( which both need pet id to find the pet )
        const updateEmployee = async(id:number,employee:any)=> {
            alert("Employee with id" + id +" has been Updated")
            if(employee.role =="Manager"){
                
                 const response = await axios.patch("http://localhost:7005/employees/" + id,"Employee", {
                    headers:{
                        "Content-Type":"text/plain"
                    }
                 } )
                 .then(()=> {alert("Success!")})
                 .catch((error)=>{alert("Failed! " + error.message)})
                 window.location.reload()

            } else if(employee.role =="Employee"){
            
            const response = await axios.patch("http://localhost:7005/employees/" + id, "Manager", {
                headers:{
                    "Content-Type":"text/plain"
                }
             })
             .then(()=> {alert("Success!")})
             .catch((error)=>{alert("Failed! " + error.message)})
            window.location.reload()

        }
        
        }

        //Delete
        const deleteEmployee = async (id:number)=> {
             alert("Employee with id" + id +" has been deleted")


             
            //request to delete this employee
            const response = await axios.delete("http://localhost:7005/employees/" + id) 
            .then(()=> {alert("Success!")})
            .catch((error)=>{alert("Failed! " + error.message)})

            //Hope that this will reload the page and rebuild the table
            //navigate("/EmployeeReimbursements") .then
            window.location.reload()

        }

        const clickHandler = (id:number)=>{
            //  store id for EployeeReimbursement to use
            store.userID = id;
            //go to page with reimbursements by employee id
            navigate("/EmployeeReimbursements")

        }


    return(
        <Container>
                <Table>
                    <thead>
                        <tr>
                            <th>UserID</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Username</th>
                            <th>Role</th>
                            <th><Button className="btn-primary" onClick={()=> navigate("/EmployeeAllReimbursements")}>All Reimbursements</Button><Button className="btn-primary" onClick={()=> navigate("/EmployeeAllPending")}>All Pending Reimbursements</Button></th>
                        </tr>
                    </thead>
                    <tbody>
                    {employees.map((employee:any)=>(
                        <tr>
                            <td>{employee.userId}</td>
                            <td>{employee.firstName}</td>
                            <td>{employee.lastName}</td>
                            <td>{employee.username}</td>
                            <td>{employee.role}</td>
                            <td><Button className= "btn-primary" onClick={()=>clickHandler(employee.userId)}>Reimbursements</Button>
                            <Button className= "btn-info" onClick={()=>{updateEmployee(employee.userId,employee.role)}}>Update Role</Button>
                            <Button className="btn-danger" onClick={()=>{deleteEmployee(employee.userId)}}>Delete Employee</Button></td>
                        </tr>

                    ))}

                    </tbody>

                </Table>

        </Container>


    )

}
