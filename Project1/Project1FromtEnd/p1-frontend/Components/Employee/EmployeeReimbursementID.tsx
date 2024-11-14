
import axios from "axios"
import React, { useEffect, useState } from "react"
import { Button, Container } from "react-bootstrap"
import  { ReimbursementTable } from "../Reimbursement/ReimbursementTable"
import { store } from "../../src/globalData/store"
import { useNavigate } from "react-router-dom"

export const EmployeeReimbursementID:React.FC = () => {

const [reimbursementsByID, setReimbursementsById] = useState([])
    
const navigate = useNavigate()

// defining a useEffect that calls the function that gets reimbursements by userId
useEffect(()=> {
    getReimbursementsById()
},[]) // this useEffect triggers on component load


// The function that gets all pets with an axios GET request
const getReimbursementsById = async () => {

    //axios GET request
    // Note : using the id of the logged in user to get only their pets 
    const response = await axios.get("http://localhost:7005/reimbursements/reimbId/" )
    //TODO: then(), catch() etc
    
    //populate the pets state object
    setReimbursementsById(response.data) // data holds thje data in the resopnse body 

    console.log(response.data)
}

return(

    // TODO: Nav bar thing
    <Container>
        <h3 >Reimbursement to Update</h3>
    <ReimbursementTable reimbursements={reimbursementsByID}></ReimbursementTable>
    <Button className="btn-dark" onClick={()=>navigate("/EmployeeReimbursements")}>Back</Button>

    </Container>
)

}