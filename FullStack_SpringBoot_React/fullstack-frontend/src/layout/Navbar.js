import React from "react";
import { Link } from "react-router-dom";

export default function Navbar() {
    return (
        <div>
            <nav className="navbar bg-body-tertiary">
                <div className="container-fluid">
                    <Link to="/" className="navbar-brand mb-0 h1">Full Stack Application</Link>
                    <Link className="btn btn-outline-dark" to={"/adduser"}>Add User</Link>
                </div>
            </nav>
        </div>
    )
}