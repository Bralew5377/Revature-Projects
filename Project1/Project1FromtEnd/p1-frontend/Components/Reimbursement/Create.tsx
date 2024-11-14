import React, { useState } from 'react';
import { Button, Container, Form } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.css';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { store } from '../../src/globalData/store';

export const Create:React.FC = () => {

        //define a state object to store the username and password

    const[reimbursement,setReimbursement] = useState({
        description:"",
        amount:"",
        userId:""
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
        setReimbursement((reimbursement) => ({...reimbursement,[name]:value}))

        //Remeber the spread operator (...) this SPREADS out the values of 
        //the object so we can access them individually 
        reimbursement.userId = store.loggedInUser.userId
        console.log(reimbursement)
    }

    //register function that sends the username/password to the backend
    const register = async () =>{

        // TODO: chack username/ password are present

        //POST REQUEST - send the new user info to the backend
        const response = await axios.post("http://localhost:7005/reimbursements", reimbursement )
        .then(()=> {alert("Success!")})
        .catch((error)=>{alert("Failed! " + error.message)})
    }

    if (store.loggedInUser.role == "Manager"){
    return(
        // my and mx , magin for y and x axis 
        //mx auto centers the content horizontally
        <Container className="my-auto mx-auto">
            <div>
                <h1>Please enter reimbursement details!</h1>
                <div>
                    <Form.Control
                        type="text"
                        placeholder="Description"
                        name="description"
                        onChange={storeValues}
                    />
                </div><div>
                    <Form.Control
                        type="text"
                        placeholder="Amount"
                        name="amount"
                        onChange={storeValues}
                    />
                </div>
                <div>
                <Button className="btn-success m-1" onClick={register}>Submit</Button>
                <Button className="btn-dark" onClick={()=>navigate("/EmployeeReimbursements")}>Back</Button>
                </div>
            </div>
        </Container>
    )
    
}else{
    return(
        // my and mx , magin for y and x axis 
        //mx auto centers the content horizontally
        <Container className="my-auto mx-auto">
            <div>
                <h1>Please enter reimbursement details!</h1>
                <div>
                    <Form.Control
                        type="text"
                        placeholder="Description"
                        name="description"
                        onChange={storeValues}
                    />
                </div><div>
                    <Form.Control
                        type="text"
                        placeholder="Amount"
                        name="amount"
                        onChange={storeValues}
                    />
                </div>
                <div>
                <Button className="btn-success m-1" onClick={register}>Submit</Button>
                <Button className="btn-dark" onClick={()=>navigate("/reimbursements")}>Back</Button>
                </div>
            </div>
        </Container>
    )
}

}