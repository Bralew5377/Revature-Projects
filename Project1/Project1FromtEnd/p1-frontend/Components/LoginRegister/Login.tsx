import axios from 'axios';
import React, { useState } from 'react';
import { Button, Container,Form, Toast } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';
import { store } from '../../src/globalData/store';


export const Login:React.FC = () => {

    //A state object that holds username and password
    const[loginCreds, setLoginCreds] = useState({
        username:"",
        password:"",
        role:""
    })

//we need to useNavigate hook to navigate between components programatically 
    //(which means we dont have to manually switch the URl)
    const navigate = useNavigate()

      //Function that stores user input
        //Function that stores user input
    const storeValues = (input:any) => {
 
        const name = input.target.name //the name of the input box that changed
        const value = input.target.value //the value in the input box
 
        setLoginCreds((loginCreds) => ({...loginCreds, [name]:value}))
 
        //check comments in storeValues() of Register.tsx for how this all works
    }
    const login = async () =>{

        //TODO: we should make sure the username/password are inputted first

        //use the username /password in the loginCreds stateobject
        const response = await axios.post("http://localhost:7005/auth",loginCreds)

       .then(
        //if the request is successful :
            //print the data
            //save it locally
            //alert the user they logged in
            //navigate to /pets
                //TODO: navigate to /users OR /pets depending on role

            //resoponse's definition doesn't exist if we try to invoke it without an arrow function
            //React doesnt know what the value is otherwise
            (response) =>{
                console.log(response)
            
            
            //saving the data locally
            store.loggedInUser = response.data

            //greet the user
            alert("welcome, " + store.loggedInUser.username)

            //depending on the users role, //sent them to 
            //pets if they are a non admin
            //and users if they are an admin
                if(store.loggedInUser.role === "Manager"){
                    navigate("/employees")
                }else{
                    navigate("/reimbursements")
                }
            }
       )

       .catch((error)=>
        alert("Login Failed! Please try again.")






       )
    }

    return(
        /*Bootstrap gives us this Container element that does some default padding and centering*/
        <Container> 

            <h1> Employee Reimbursement System</h1>
                <h3>Login</h3>
                
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
                

            <Button className="btn-success m-1" onClick={login}>Login</Button>
            <Button className="btn-dark" onClick={()=>navigate("/register")}>Register</Button>
        </Container>
    )
} 