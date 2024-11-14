import React, { useState } from 'react';
import { Button, Container, Form } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.css';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

export const Register:React.FC = () => {

        //define a state object to store the username and password

    const[employee,setEmployee] = useState({
        username:"",
        password:""
    })

    const navigate = useNavigate()

    const storeValues = (input:any) =>{
        const name = input.target.name // the name of the input box that changed
        const value = input.target.value // the value in the input box

        //input = the entire event 
        //target = the specific input box that triggered the onChange event
        // name/value = the name /value of the input box

        // we need to send the entire user object to make a change to one field 
        //take whatever input was changed and set the matching field in user to the value in the input 
        setEmployee((employee) => ({...employee,[name]:value}))

        //Remeber the spread operator (...) this SPREADS out the values of 
        //the object so we can access them individually 
        
        console.log(employee)
    }

    //register function that sends the username/password to the backend
    const register = async () =>{

        // TODO: chack username/ password are present

        //POST REQUEST - send the new user info to the backend
        const response = await axios.post("http://localhost:7005/employees", employee)
        .then(()=> {alert("Success!")})
        .catch((error)=>{alert("Failed! " + error.message)})
    }

    return(
        // my and mx , magin for y and x axis 
        //mx auto centers the content horizontally
        <Container className="my-auto mx-auto">
            <div>
                <h1>New here? Create an Account for free!</h1>
                <div>
                    <Form.Control
                        type="text"
                        placeholder="First name"
                        name="firstName"
                        onChange={storeValues}
                    />
                </div><div>
                    <Form.Control
                        type="text"
                        placeholder="last name"
                        name="lastName"
                        onChange={storeValues}
                    />
                </div>
                <div>
                    <Form.Control
                        type="text"
                        placeholder="username"
                        name="username"
                        onChange={storeValues}
                    />
                </div>
                <div>
                    <Form.Control
                        type="password"
                        placeholder="password"
                        name="password"
                        onChange={storeValues}
                    />
                </div>
                <div>
                <Button className="btn-success m-1" onClick={register}>Register</Button>
                <Button className="btn-dark" onClick={()=>navigate("/")}>Back</Button>
                </div>
            </div>
        </Container>
    )
    
}