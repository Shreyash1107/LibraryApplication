import { useState } from "react";
import "./login.css";
export default function Login() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");
    const handleSubmit = (e) => {
        e.preventDefault();
        setError("");
        if (!email) {
            setError("Email is required.");
            return;
        }
        if (!password) {
            setError("Password is required.");
            return;
        }
        console.log("Logging in with:", { email, password });
        alert(`Login attempted for ${email}`);
    };
    return (
        <div className="login-container">
            <form className="login-card" onSubmit={handleSubmit} noValidate>
                <h2 className="login-title">Login</h2>
                <div className="form-group">
                    <label htmlFor="email">Email</label>
                    <input
                        id="email"
                        type="email"
                        placeholder="you@example.com"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                        className="form-input"
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="password">Password</label>
                    <input
                        id="password"
                        type="password"
                        placeholder="Enter your password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                        className="form-input"
                    />
                </div>
                {error && <div className="form-error">{error}</div>}
                <button type="submit" className="login-button">
                    Login
                </button>
            </form>
        </div>
    );
}