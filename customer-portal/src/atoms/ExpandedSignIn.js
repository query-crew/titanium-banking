import React, { useState, useEffect } from "react";
import "./ExpandedSignIn.css";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import FloatingLabel from "react-bootstrap/FloatingLabel";
import Card from 'react-bootstrap/Card';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import VisibilityOffIcon from '@mui/icons-material/VisibilityOff';
import VisibilityIcon from '@mui/icons-material/Visibility';
import InputGroup from 'react-bootstrap/InputGroup';
import axios from "axios";
import { useSelector, useDispatch } from 'react-redux';
import { setToken } from '../tokenReducer';
import { Link, useNavigate } from "react-router-dom";

function ExpandedSignIn(props) {
const[passwordVisible, setPasswordVisible] = useState(false);
const[username, setUsername] = useState(getInitialUsername());
const[password, setPassword] = useState("");
const[checked, setChecked] = useState(localStorage.getItem("checked") === "true" ? true : false);
const[usersLoading, setUsersLoading] = useState(false);
const token = useSelector((state) => state.token.value)
const dispatch = useDispatch();
const navigate = useNavigate();

function login(onSuccess, onError) {
    const login = { username: username, password: password};
    setUsersLoading(true);
    axios.post("/user/login", login)
    .then(response => { 
        dispatchToken(response.data)
        onSuccess();
    })
    .catch(({ response }) => onError(response.data));
}

function getInitialUsername() {
    const initUsername = localStorage.getItem("username");
    if (initUsername) {
        return initUsername;
    }
    else {
        return "";
    }
}

const changeVisibility = () => {
    setPasswordVisible(!passwordVisible);
}

async function saveUsername() {
    return new Promise((resolve, reject) => {
        if(username !== "") {
            try {
                localStorage.setItem("username", username);
                localStorage.setItem("checked", "true");
                resolve();
            }
            catch (err) {
                reject(err);
            }
        }
        else {
            reject(new Error("Username is blank."));
        }
    });
}

function clearUsername(onSuccess, onError) {
    return new Promise((resolve, reject) => {
        try {
            localStorage.removeItem("username");
            localStorage.removeItem("checked");
            resolve();
        }
        catch (err) {
            reject(err);
        }
    });
}

async function handleRememberMe(onSuccess, onError) {
    if (checked) {
        try {
            await saveUsername();
            onSuccess();
        }
        catch (err) {
            onError(err);
        }
    }
    else {
        try {
            await clearUsername();
            onSuccess();
        }
        catch (err) {
            onError(err);
        }
    }
}

async function dispatchToken(token) {
    await dispatch(setToken(token));
    setUsersLoading(false);
}

async function handleSubmit(event) {
    event.preventDefault();
    handleRememberMe(function onSuccess() {
        login(
            function onSuccess() {
                navigate('/account');
            },
            function onError(err) {
                if (typeof err == "string") {
                    alert(err);
                }
                else {
                    let alertMessage = "";
                    const values = Object.values(err);
                    for (const value of values) {
                        alertMessage += value + "\n";
                    }
                    alert(alertMessage);
                }
        });
    },
    function onError(err) {
        alert(err.message);
    })
}

  return (
    <div id="expanded-sign-in-override-bootstrap" className={props.className}>
        <Container fluid="md">
            <h2 className="expanded-sign-in-title">Sign In</h2>
            <p className="expanded-sign-in-description">Sign in to manage your accounts.</p>
            <Container fluid className="d-flex justify-content-center">
                <Form className="expanded-sign-in-form">
                    <Form.Group className="mb-3">
                        <FloatingLabel
                        controlId="floatingInput"
                        label="Username"
                        className="mb-3"
                        >
                        <Form.Control
                            className="expanded-sign-in-user-input"
                            value={username}
                            type="text"
                            placeholder="Username"
                            onChange={e => setUsername(e.target.value)}
                        />
                        </FloatingLabel>
                    </Form.Group>
                    <InputGroup className="mb-3">
                        <FloatingLabel className="expanded-sign-in-floating" controlId="floatingPassword" label="Password">
                            <Form.Control
                                className="expanded-sign-in-user-input"
                                type={passwordVisible ? "text" : "password"}
                                placeholder="Password"
                                aria-label="Password"
                                aria-describedby="basic-addon2"
                                onChange={e => setPassword(e.target.value)}
                            />
                        </FloatingLabel>
                        <Button className="expanded-sign-in-visibility-button" variant="outline-secondary" id="button-addon2" onClick={ changeVisibility }>
                            {passwordVisible ? <VisibilityIcon/> : <VisibilityOffIcon/>}
                        </Button>
                    </InputGroup>
                    <Form.Group className="my-5" controlId="formBasicCheckbox">
                        <Form.Check data-testid="expanded-sign-in-checkbox" type="checkbox" label="Remember me" checked={ checked } onChange={e => setChecked(e.target.checked)}/>
                    </Form.Group>
                    <Container className="expanded-sign-in-container d-flex justify-content-center">
                        <Button className="expanded-sign-in-button" variant="primary" onClick={handleSubmit}>
                            Sign In
                        </Button>
                    </Container>
                </Form>
            </Container>
        </Container>
    </div>
  );
}

export default ExpandedSignIn;