import { faEye, faEyeSlash } from "@fortawesome/free-regular-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { useState } from "react"
import "./register.css";
const RegisterComponent = () => {
    const [reg, setReg] = useState({
        firstName: "",
        lastName: "",
        email: "",
        contact: "",
        password: "",
        confirmPassword: "",
        role: ""
    });
    const [error, setError] = useState({
        firstName: "",
        lastName: "",
        email: "",
        contact: "",
        password: "",
        confirmPassword: "",
        role: ""
    });
    const [showPassword, setShowPassword] = useState(false);
    const [showConfirmPassword, setShowConfirmPassword] = useState(false);
    const handleFormFields = (event) => {
        setReg(event.target.value);
    }
    const handleSubmitDetails = (event) => {
        event.preventDefault();
    }
    return (<>
        <div className="register-page">
            <div className="register-card">
                <div className="register-header">
                    <h2 className="register-title">Create an Account</h2>
                    <div className="register-grid">
                        <div className="field">
                            <label htmlFor="firstName" className="label">
                                First Name
                            </label>
                            <input type="text" name="firstName" value={reg.firstName}
                                onChange={handleFormFields} autoComplete="off" placeholder="First Name" />
                        </div>
                        <div className="field">
                            <label htmlFor="lastName" className="label">
                                Last Name
                            </label>
                            <input type="text" name="lastName" value={reg.lastName}
                                onChange={handleFormFields} autoComplete="off" placeholder="Last Name" />
                        </div>
                        <div className="field full-width">
                            <label htmlFor="email" className="label">
                                Email
                            </label>
                            <input type="email" name="email" value={reg.email}
                                onChange={handleFormFields} autoComplete="off" placeholder="Email-Id" />
                        </div>
                        <div className="field full-width">
                            <label htmlFor="contact" className="label">
                                Contact
                            </label>
                            <input type="number" name="contact" value={reg.contact}
                                onChange={handleFormFields} autoComplete="off" placeholder="Contact" />
                        </div>
                        <div className="field">
                            <label htmlFor="password" className="label">
                                Password
                            </label>
                            <div className="password-row">
                                <input
                                    type={!showPassword ? "text" : "password"}
                                    name="password"
                                    value={reg.password}
                                    onChange={handleFormFields}
                                    autoComplete="off"
                                    placeholder="Password"
                                    className="input"
                                />
                                <button
                                    type="button"
                                    onClick={() => setShowPassword(!showPassword)}
                                    className="password-toggle"
                                    aria-label="Toggle password visibility"
                                >
                                    <FontAwesomeIcon icon={showPassword ? faEyeSlash : faEye} />
                                </button>
                            </div>
                        </div>
                        <div className="field">
                            <label htmlFor="confirmPassword" className="label">
                                Confirm Password
                            </label>
                            <div className="password-row">
                                <input
                                    type={!showConfirmPassword ? "text" : "password"}
                                    name="confirmPassword"
                                    value={reg.confirmPassword}
                                    onChange={handleFormFields}
                                    placeholder="Re-type Password"
                                    className="input"
                                    autoComplete="off"
                                />
                                <button
                                    type="button"
                                    onClick={() => setShowConfirmPassword(!showConfirmPassword)}
                                    className="password-toggle"
                                    aria-label="Toggle Confirm Password Visibility"
                                >
                                    <FontAwesomeIcon icon={showConfirmPassword ? faEyeSlash : faEye} />
                                </button>
                            </div>
                        </div>
                        <div className="field full-width">
                            <label htmlFor="role" className="label">
                                Select Role
                            </label>
                            <select name="role" value={reg.role} onChange={handleFormFields}>
                                <option value="" disabled>Select Role</option>
                                <option value="admin">Admin</option>
                                <option value="user">Student</option>
                                <option value="user">Librarian</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div className="actions">
                    <button type="submit" name="Register" value="reg" onClick={handleSubmitDetails}>
                        Register
                    </button>
                </div>
            </div>
        </div>
    </>)
}
export default RegisterComponent;