import axios from "axios"
import React, { useEffect, useState } from "react"
import { Button, Container } from "react-bootstrap"
import  { ReimbursementTable } from "./ReimbursementTable"
import { store } from "../../src/globalData/store"
import { useNavigate } from "react-router-dom"


export const ReimbursementContainer:React.FC = () => {

    //well store a state ibject that contains an Array of PetInterfaces
    //TODO: make the pet interface, but for now, we can just use an any []
    const [reimbursements, setReimbursements] = useState([])
    
    const navigate = useNavigate()

    // defining a useEffect that calls the function that gets pets by userId
    useEffect(()=> {
        getReimbursementsByUserId()
    },[]) // this useEffect triggers on component load


    // The function that gets all pets with an axios GET request
    const getReimbursementsByUserId = async () => {

        //axios GET request
        // Note : using the id of the logged in user to get only their pets 
        const response = await axios.get("http://localhost:7005/reimbursements/" + store.loggedInUser.userId)
        //TODO: then(), catch() etc
        
        //populate the pets state object
        setReimbursements(response.data) // data holds thje data in the resopnse body 

        console.log(response.data)
    }


    return(

        // TODO: Nav bar thing
        <Container>
            <h3>Reimbursements</h3>
        <ReimbursementTable reimbursements={reimbursements}></ReimbursementTable>
        <Button className="btn-success m-1" onClick={()=>navigate("/Create")}>Create New Reimbursement</Button>
        <br/>
        <Button className="btn-dark" onClick={()=>navigate("/")}>Logout</Button>

        </Container>
    )
}