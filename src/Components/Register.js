import { faEye, faEyeSlash } from "@fortawesome/free-regular-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { useState, useEffect } from "react"
import axios from "axios";
import "./register.css";
import Swal from "sweetalert2";
import { Link } from "react-router-dom";
const RegisterComponent = () => {
    const [reg, setReg] = useState({
        firstName: "",
        lastName: "",
        email: "",
        contact: "",
        password: "",
        confirmPassword: "",
        roleId: ""
    });
    const [roles, setRoles] = useState([]);
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
    useEffect(() => {
        axios.get("http://localhost:9090/roles/viewRoles")
            .then((res) => setRoles(res.data))
            .catch((err) => console.log("Error in Fetching Roles: ", err));
    }, []);
    const handleFormFields = (event) => {
        setReg({ ...reg, [event.target.name]: event.target.value });
    }
    const handleSubmitDetails = (event) => {
        event.preventDefault();
        const { firstName, lastName, email, contact, password, confirmPassword, roleId } = reg;
        let hasError = false;
        const regError = {
            firstName: "",
            lastName: "",
            email: "",
            contact: "",
            password: "",
            confirmPassword: "",
            role: ""
        }
        if (!firstName || firstName.trim() === "") {
            regError.firstName = "First Name Cannot be Empty";
            hasError = true;
        } else if (!(/^[A-Za-z]*$/).test(firstName)) {
            regError.firstName = "First Name should contain only alphabets";
            hasError = true;
        } if (!lastName || lastName.trim() === "") {
            regError.lastName = "Last Name Cannot be Empty";
            hasError = true;
        } if (!(/^[A-Za-z]*$/).test(lastName)) {
            regError.lastName = "Last Name should contain only alphabets";
            hasError = true;
        } if (!email || email.trim() === "") {
            regError.email = "Email Cannot be Empty";
            hasError = true;
        } else if (!(/^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/).test(email)) {
            regError.email = "Invalid Email Format";
            hasError = true;
        } if (!contact || contact.trim() === "") {
            regError.contact = "Contact Cannot be Empty";
            hasError = true;
        } else if (!(/^[0-9]{10}$/).test(contact)) {
            regError.contact = "Contact should be a 10-digit number";
            hasError = true;
        } if (!password || password.trim() === "") {
            regError.password = "Password Cannot be Empty";
            hasError = true;
        } else if (!(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/).test(password)) {
            regError.password = "Provide Valid Password";
            hasError = true;
        } if (!confirmPassword || confirmPassword.trim() === "") {
            regError.confirmPassword = "Please Retype the Password";
            hasError = true;
        } else if (confirmPassword !== password) {
            regError.confirmPassword = "Password Fields should Match";
            hasError = true;
        } if (roleId === "") {
            regError.role = "Select a Role";
            hasError = true;
        }
        if (!hasError) {
            const payload = {
                ...reg,
                rolesAssigned: { id: reg.roleId }
            };
            axios.post("http://localhost:9090/register/saveUsers", payload)
                .then((res) => {
                    Swal.fire({
                        title: "Great!",
                        text: `Registration Successful for ${reg.firstName} and email sent for Verification`,
                        icon: "success",
                        timer: 1500,
                    });
                    setReg({
                        firstName: "",
                        lastName: "",
                        email: "",
                        contact: "",
                        password: "",
                        confirmPassword: "",
                        roleId: ""
                    });
                    setError({
                        firstName: "",
                        lastName: "",
                        email: "",
                        contact: "",
                        password: "",
                        confirmPassword: "",
                        role: ""
                    });
                })
                .catch((err) => {
                    console.log("Error in Registration:", err);
                    let messages = [];
                    if (err.response?.data) {
                        if (Array.isArray(err.response.data)) {
                            messages = err.response.data;
                        } else {
                            messages = [err.response.data.message || err.response.data];
                        }
                    }
                    Swal.fire({
                        title: "Validation Errors",
                        html: messages.map(msg => `<p>${msg}</p>`).join(""),
                        icon: "error",
                        confirmButtonText: "OK",
                    });
                });
        }
        setError(regError);
        setReg({
            firstName: "",
            lastName: "",
            email: "",
            contact: "",
            password: "",
            confirmPassword: "",
            roleId: ""
        });
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
                            <span className="error-message">{error.firstName}</span>
                        </div>
                        <div className="field">
                            <label htmlFor="lastName" className="label">
                                Last Name
                            </label>
                            <input type="text" name="lastName" value={reg.lastName}
                                onChange={handleFormFields} autoComplete="off" placeholder="Last Name" />
                            <span className="error-message">{error.lastName}</span>
                        </div>
                        <div className="field full-width">
                            <label htmlFor="email" className="label">
                                Email
                            </label>
                            <input type="email" name="email" value={reg.email}
                                onChange={handleFormFields} autoComplete="off" placeholder="Email-Id" />
                            <span className="error-message">{error.email}</span>
                        </div>
                        <div className="field full-width">
                            <label htmlFor="contact" className="label">
                                Contact
                            </label>
                            <input type="number" name="contact" value={reg.contact}
                                onChange={handleFormFields} autoComplete="off" placeholder="Contact" />
                            <span className="error-message">{error.contact}</span>
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
                            <span className="error-message">{error.password}</span>
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
                            <span className="error-message">{error.confirmPassword}</span>
                        </div>
                        <div className="field full-width">
                            <label htmlFor="role" className="label">
                                Select Role
                            </label>
                            <select name="roleId" value={reg.roleId} onChange={handleFormFields}>
                                <option value="" disabled>Select Role</option>
                                {roles.map((role) => (
                                    <option key={role.id} value={role.id}>
                                        {role.roles}
                                    </option>
                                ))}
                            </select>
                            <span className="error-message">{error.role}</span>
                        </div>
                    </div>
                </div>
                <div className="actions">
                    <button type="submit" name="Register" value="reg" onClick={handleSubmitDetails}>
                        Register
                    </button>
                </div>
                <div className="login-redirect">
                    <p>Already have an account? <Link to="/">Login Here</Link></p>
                </div>
            </div>
        </div>
    </>)
}
export default RegisterComponent;