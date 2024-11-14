
import axios from "axios"
import React, { useEffect, useState } from "react"
import { Button, Container } from "react-bootstrap"
import  { ReimbursementTable } from "../Reimbursement/ReimbursementTable"
import { useNavigate } from "react-router-dom"

export const EmployeeAllReimbursements:React.FC = () => {

const [Allreimbursements, setAllReimbursements] = useState([])
    
const navigate = useNavigate()

// defining a useEffect that calls the function that gets reimbursements by userId
useEffect(()=> {
    getAllReimbursements()
},[]) // this useEffect triggers on component load


// The function that gets all pets with an axios GET request
const getAllReimbursements = async () => {

    //axios GET request
    // Note : using the id of the logged in user to get only their pets 
    const response = await axios.get("http://localhost:7005/reimbursements")
    //TODO: then(), catch() etc
    
    //populate the pets state object
    setAllReimbursements(response.data) // data holds thje data in the resopnse body 

    console.log(response.data)
   
}

return(

    // TODO: Nav bar thing
    <Container>
         <h3 >All Reimbursements</h3>
    <ReimbursementTable reimbursements={Allreimbursements}></ReimbursementTable>
    <Button className="btn-dark" onClick={()=>navigate("/employees")}>Back</Button>

    </Container>
)

}