
import './App.css'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import { Login } from '../Components/LoginRegister/Login'
import { Register } from '../Components/LoginRegister/Register'
import { Employee} from '../Components/Employee/Employee'
import { ReimbursementContainer } from '../Components/Reimbursement/ReimbursementContainer'
import { EmployeeReimbursements} from '../Components/Employee/EmployeeReimbursements'
import { EmployeeAllReimbursements} from '../Components/Employee/EmployeeAllReimbursements'
import { EmployeeAllPending} from '../Components/Employee/EmployeeAllPending'

import { Create} from '../Components/Reimbursement/Create'

function App() {
 
  return (
    <>
      <div>
        <BrowserRouter>
          <Routes>
              <Route path="" element={<Login/>}/> 

              <Route path="/register" element={<Register/>}/> 

              <Route path="/reimbursements" element={<ReimbursementContainer/>}/>
              <Route path="/employees" element={<Employee/>}/>
              <Route path="/EmployeeReimbursements" element={<EmployeeReimbursements/>}/>
              <Route path="/EmployeeAllReimbursements" element={<EmployeeAllReimbursements/>}/>
              <Route path="/Create" element={<Create/>}/>
              <Route path="/EmployeeAllPending" element={<EmployeeAllPending/>}/>
              
          </Routes>
        
        </BrowserRouter>
       
      </div>
    </>
  )
}

export default App
