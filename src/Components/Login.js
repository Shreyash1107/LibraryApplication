import { useState } from "react";
import "./login.css";
import axios from "axios";
export default function Login() {
    const [login, setLogin] = useState({
        email: "",
        password: ""
    });
    const [error, setError] = useState({
        email: "",
        password: ""
    });
    const onHandleChange = (event) => {
        setLogin({
            ...login,
            [event.target.name]: event.target.value
        });
    }
    const handleLogin = (e) => {
        e.preventDefault();
        const { email, password } = login;
        const loginErrors = {
            email: "",
            password: ""
        };
        let hasError = false;
        if (!email || email.trim() === "") {
            loginErrors.email = "Email is required";
            hasError = true;
        } else if (!(/^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/).test(email)) {
            loginErrors.email = "Invalid Email Format";
            hasError = true;
        } if (!password || password.trim() === "") {
            loginErrors.password = "Password is required";
            hasError = true;
        } else if (!(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/).test(password)) {
            loginErrors.password = "Provide Valid Password";
            hasError = true;
        }
        if (!hasError) {
            axios.post("http://localhost:9090/register/login", login)
                .then((response) => {
                    Swal.fire({

                    })
                });
        }
        setError(loginErrors);
        setLogin({
            email: "",
            password: ""
        });
        return (
            <div className="login-container">
                <div className="login-card">
                    <h2 className="login-title">Login</h2>
                    <div className="form-group">
                        <label htmlFor="email">Email</label>
                        <input
                            id="email"
                            name="email"
                            type="email"
                            placeholder="you@example.com"
                            value={login.email}
                            onChange={onHandleChange}
                            className="login-input"
                        />
                        {error.email && <div className="error-message">{error.email}</div>}
                    </div>
                    <div className="form-group">
                        <label htmlFor="password">Password</label>
                        <input
                            id="password"
                            name="password"
                            type="password"
                            placeholder="Enter your password"
                            value={login.password}
                            onChange={onHandleChange}
                            className="login-input"
                        />
                        {error.password && (
                            <div className="error-message">{error.password}</div>
                        )}
                    </div>
                    <button type="button" className="login-button" onClick={handleLogin}>
                        Login
                    </button>
                </div>
            </div>
        );
    }
}