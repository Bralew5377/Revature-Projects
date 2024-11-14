import React, { useEffect, useState } from "react"
import { Button, Container, Form } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.css';
import  { EmployeeTable } from "./EmployeeTable"
import { store } from "../../src/globalData/store"
import axios from 'axios';
import { useNavigate } from "react-router-dom"

//This is a placeholder component - admins get navigated to this after login 
// in p 1this is where admins could see all users update delete etc
//and maybe there is a nav bar or some button that navigates to the admin reimbursment manager
export const Employee:React.FC = () => {

    const navigate = useNavigate()

    const [employees, setEmployees] = useState([])

    // defining a useEffect that calls the function that gets all employees
    useEffect(()=> {
        getAllEmployees()
    },[]) // this useEffect triggers on component load


    // The function that gets all pets with an axios GET request
    const getAllEmployees = async () => {

        //axios GET request
        // Note : using the id of the logged in user to get only their pets 
        const response = await axios.get("http://localhost:7005/employees")
        //TODO: then(), catch() etc
        
        //populate the pets state object
        setEmployees(response.data) // data holds thje data in the resopnse body 

        console.log(response.data)
    }


    //TODO: useEffect that checks the user's role and redirects them back to the login component if they aren't admins
    // * Route Guards
    return(

        <>
            <h3>Staff</h3>
        <Container>
        <EmployeeTable employees={employees}></EmployeeTable>
        <Button className="btn-dark" onClick={()=>navigate("/")}>Logout</Button>

        </Container>
        </>
    )
}