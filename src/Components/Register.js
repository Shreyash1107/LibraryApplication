import { useState } from "react"
const RegisterComponent=()=>{
    const [reg,setReg] = useState({
        firstName:"",
        lastName:"",
        email:"",
        contact:"",
        password:"",
        confirmPassword:"",
        role:""
    });
    const[error,setError] = useState({
        firstName:"",
        lastName:"",
        email:"",
        contact:"",
        password:"",
        confirmPassword:"",
        role:""
    });
    const handleFormFields=(event)=>{
        setReg(event.target.value);
    }
    const handleSubmitDetails=(event)=>{
        event.preventDefault();
    }
}
export default RegisterComponent;