import React, { useState } from "react";
import "../styles/ExpandedSignInCard.css";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import FloatingLabel from "react-bootstrap/FloatingLabel";
import Card from 'react-bootstrap/Card';
import Container from 'react-bootstrap/Container';
import VisibilityOffIcon from '@mui/icons-material/VisibilityOff';
import VisibilityIcon from '@mui/icons-material/Visibility';
import InputGroup from 'react-bootstrap/InputGroup';
import axios from "axios";
import { useDispatch } from 'react-redux';
import { setToken } from '../redux/tokenReducer';
import { Link, useNavigate } from "react-router-dom";
import SignInService from "../services/SignInService";

function ExpandedSignInCard(props) {

// State variables
const[passwordVisible, setPasswordVisible] = useState(false);
const[username, setUsername] = useState(SignInService.getInitialUsername());
const[password, setPassword] = useState("");
const[checked, setChecked] = useState(localStorage.getItem("checked") === "true" ? true : false);

// React hooks
const dispatch = useDispatch();
const navigate = useNavigate();

// Component functions
function handleSubmit(event) {
    event.preventDefault();
    SignInService.signIn(username, password, checked, 
        function dispatchToken(token) {
            dispatch(token);
        },
        function navigateAfterLogin(path) {
            navigate(path);
        });
}

function handleVisibilityButtonClick(event) {
    setPasswordVisible(SignInService.toggleVisibility(passwordVisible));
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
                        <Button className="expanded-visibility-button" variant="outline-secondary" id="visibilityToggle" onClick={ handleVisibilityButtonClick }>
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
