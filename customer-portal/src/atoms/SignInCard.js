import React, { useState, useEffect } from "react";
import "./SignInCard.css";
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

function SignInCard(props) {
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
    <div id="sign-in-override-bootstrap" className={props.className}>
      <Card>
        <Card.Body>
        <h2>Sign In</h2>
        <p className="sign-in-description">Sign in to manage your accounts.</p>
        <Form>
          <Form.Group className="mb-3">
            <FloatingLabel
              controlId="floatingInput"
              label="Username"
              className="mb-3"
            >
              <Form.Control
                className="user-input"
                value={username}
                type="text"
                placeholder="Username"
                onChange={e => setUsername(e.target.value)}
              />
            </FloatingLabel>
          </Form.Group>
          <InputGroup className="mb-3">
            <FloatingLabel className="floating" controlId="floatingPassword" label="Password">
                <Form.Control
                    className="user-input"
                    type={passwordVisible ? "text" : "password"}
                    placeholder="Password"
                    aria-label="Password"
                    aria-describedby="basic-addon2"
                    onChange={e => setPassword(e.target.value)}
                />
            </FloatingLabel>
            <Button className="visibility-button" variant="outline-secondary" id="button-addon2" onClick={ changeVisibility }>
                {passwordVisible ? <VisibilityIcon/> : <VisibilityOffIcon/>}
            </Button>
            </InputGroup>
            <Form.Group className="mb-3" controlId="formBasicCheckbox">
                <Form.Check data-testid="sign-in-card-checkbox" type="checkbox" label="Remember me" checked={ checked } onChange={e => setChecked(e.target.checked)}/>
            </Form.Group>
            <Container className="button-container" fluid="md">
                <Row>
                    <Col md={8}>
                        <Button className="sign-in-button" variant="primary" onClick={handleSubmit}>
                            Sign In
                        </Button>
                    </Col>
                    <Col className="d-flex align-items-center">
                        <Link to="/enroll" className="enroll-button" variant="primary">
                            Enroll
                        </Link>
                    </Col>
                </Row>
            </Container>
        </Form>
        </Card.Body>
        <Card.Footer>
            <Link to="/password-help" className="forgot-username-password-text">Forgot username or password?</Link>
        </Card.Footer>
      </Card>
    </div>
  );
}

export default SignInCard;
