import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";

export default function ViewUser(){
    
    const navigate = useNavigate();

    const[user, setUser] = useState({
        name:"",
        username:"",
        email:""
    })

    const{name, username, email}=user;

    const {id}=useParams();

    const loadUser =async ()=>{
        const result = await axios.get(`http://localhost:8080/user/${id}`)
        setUser(result.data);
    }

    useEffect(() =>{
        loadUser();
    },[])


    return (
        <div className="container">
            <div className="row">
                <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
                    <h2 className="text-center m-4">View User</h2>
                        <div className="mb-3">
                            <label htmlFor="id" className="form-label">User Id: {id}</label> 
                        </div>
                        <div className="mb-3">
                            <label htmlFor="Name" className="form-label">Name: {name}</label> 
                        </div>
                        <div className="mb-3">
                            <label htmlFor="Username" className="form-label">Username: {username}</label>
                        </div>
                        <div className="mb-3">
                            <label htmlFor="Email" className="form-label">Email: {email}</label>
                        </div>
                        <Link to="/" className="btn btn-outline-primary mx-2">Go Back</Link>
                </div>
            </div>
        </div>
    )
}