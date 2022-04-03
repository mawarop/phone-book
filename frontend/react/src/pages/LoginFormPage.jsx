import React, {useState} from "react";

import "./Form.css";
import AuthService from "../services/AuthService";
import {Button, Container, Form} from "react-bootstrap";
import {useNavigate} from "react-router-dom";
import axiosInstance from "../services/axiosInstance";


function LoginFormPage(props) {

    const [validated, setValidated] = useState(false);
    const [credentialsFeedback, setCredentialsFeedback] = useState("");
    let navigate = useNavigate();

    function handleSubmit(event) {
        event.preventDefault();

        const form = event.currentTarget;
        if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
        } else {
            let username = event.target.elements.username.value;
            let password = event.target.elements.password.value;

            let loginPromise = AuthService.login(username, password);

            loginPromise
                .then((response) => {
                    console.log("odpowiedź");
                    if (response.status == 200) {
                        axiosInstance.defaults.headers.common['Authorization'] = response.headers.authorization;
                        axiosInstance.get()
                        setCredentialsFeedback("");
                        console.log("zalogowano!");
                        console.log(response.data.role);
                        axiosInstance.get("api/v1/contacts?page=0").then(response => {
                            console.log(response)
                        })

                    }
                })
                .catch((error) => {
                    console.log(error);
                    setValidated(false);
                    form.reset();

                    setCredentialsFeedback("Podano niepoprawne dane logowania");
                });
        }
        setValidated(true);
    }

    return (
        <Container className="form-card mx-auto">
            <Form
                id="login-form"
                noValidate
                validated={validated}
                onSubmit={handleSubmit}
                method="Post"
            >
                <Form.Group className="mb-2">
                    <Form.Label for="usernameInput">Username: </Form.Label>
                    <Form.Control
                        required
                        type="username"
                        name="username"
                        className="form-control"
                        id="usernameInput"
                        aria-describedby="usernameHelp"
                        placeholder="Wprowadź username"
                    />
                </Form.Group>
                <Form.Group className="mb-2">
                    <Form.Label for="passwordInput">Password: </Form.Label>
                    <Form.Control
                        required
                        type="password"
                        name="password"
                        className="form-control"
                        id="passwordInput"
                        placeholder="Wprowadź password"
                    />
                </Form.Group>


                <Button className="mb-2" type="submit" variant="primary">
                    Zaloguj
                </Button>
                <div style={{color: "#dc3545", textAlign: "center"}}>
                    {credentialsFeedback}
                </div>
            </Form>
        </Container>
    );
}

export default LoginFormPage;
