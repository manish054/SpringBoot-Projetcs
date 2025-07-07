import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link, useNavigate, useParams } from "react-router-dom";

export default function Home() {
    const[users, setUsers] = useState([]);
    
    const loadUsers = async() =>{
        const result = await axios.get("http://localhost:8080/users");
        console.log(result.data);
        setUsers(result.data);
    }

    useEffect(() => {
        loadUsers();
    },[])

    
    const{id} = useParams();
    const deleteUser = async(id) => {
        
        const result = await axios.delete(`http://localhost:8080/user/${id}`)
        console.log(result.data);
        loadUsers();
    }

    return (
        <div className="container">
            <div className="py-4">
                <table className="table border shadow">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Name</th>
                            <th scope="col">Username</th>
                            <th scope="col">Email</th>
                            <th scope="col">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            users.map((user, indx) =>{
                                // console.log(user.name);
                                return(
                                    <tr>
                                    <th scope="row" key={indx}>{indx+1}</th>
                                    <td>{user.name}</td>
                                    <td>{user.username}</td>
                                    <td>{user.email}</td>
                                    <td>
                                        <Link to={`/viewuser/${user.id}`} className="btn btn-primary mx-2">View</Link>
                                        <Link to={`/edituser/${user.id}`} className="btn btn-outline-primary mx-2">Edit</Link>
                                        <button onClick={() => deleteUser(user.id)} className="btn btn-danger mx-2">Delete</button>
                                    </td>
                                </tr>
                                )
                            })
                        }
                        
                    </tbody>
                </table>
            </div>
        </div>
    )
}