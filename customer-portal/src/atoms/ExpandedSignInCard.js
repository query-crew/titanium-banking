import React, { useState, useEffect } from "react";
import "./ExpandedSignInCard.css";
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

function ExpandedSignInCard(props) {
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
    <div id="expanded-sign-in-card-override-bootstrap" className={props.className}>
      <Card>
        <Card.Body className="justify-content-center">
            <h2 className="expanded-sign-in-card-title">Sign In</h2>
            <p className="expanded-sign-in-card-description">Sign in to manage your accounts.</p>
            <Container className="d-flex justify-content-center">
                <Form className="expanded-sign-in-card-form">
                    <Form.Group className="mb-3">
                        <FloatingLabel
                        controlId="expandedCardFloatingInput"
                        label="Username"
                        className="mb-3"
                        >
                        <Form.Control
                            className="expanded-user-input"
                            value={username}
                            type="text"
                            placeholder="Username"
                            onChange={e => setUsername(e.target.value)}
                        />
                        </FloatingLabel>
                    </Form.Group>
                    <InputGroup className="mb-3">
                        <FloatingLabel className="expanded-floating" controlId="expandedCardFloatingPassword" label="Password">
                            <Form.Control
                                className="expanded-user-input"
                                type={passwordVisible ? "text" : "password"}
                                placeholder="Password"
                                aria-label="Password"
                                aria-describedby="visibilityToggle"
                                onChange={e => setPassword(e.target.value)}
                            />
                        </FloatingLabel>
                        <Button className="expanded-visibility-button" variant="outline-secondary" id="visibilityToggle" onClick={ changeVisibility }>
                            {passwordVisible ? <VisibilityIcon/> : <VisibilityOffIcon/>}
                        </Button>
                    </InputGroup>
                    <Form.Group className="my-5" controlId="expandedCardFormBasicCheckbox">
                        <Form.Check data-testid="expanded-sign-in-card-checkbox" type="checkbox" label="Remember me" checked={ checked } onChange={e => setChecked(e.target.checked)}/>
                    </Form.Group>
                    <Container className="expanded-sign-in-card-container d-flex justify-content-center">
                        <Button className="expanded-sign-in-card-button" variant="primary" onClick={handleSubmit}>
                            Sign In
                        </Button>
                    </Container>
                </Form>
            </Container>
        </Card.Body>
        <Card.Footer className="justify-content-center">
            <Link to="/password-help" className="expanded-forgot-username-password-text">Forgot username or password?</Link>
        </Card.Footer>
      </Card>
    </div>
  );
}

export default ExpandedSignInCard;
