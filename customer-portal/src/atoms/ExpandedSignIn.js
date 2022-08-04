import React, { useState } from "react";
import "../styles/ExpandedSignIn.css";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import FloatingLabel from "react-bootstrap/FloatingLabel";
import Container from 'react-bootstrap/Container';
import VisibilityOffIcon from '@mui/icons-material/VisibilityOff';
import VisibilityIcon from '@mui/icons-material/Visibility';
import InputGroup from 'react-bootstrap/InputGroup';
import { useNavigate } from "react-router-dom";
import SignInService from "../services/SignInService";

function ExpandedSignIn(props) {

// State variables
const[passwordVisible, setPasswordVisible] = useState(false);
const[username, setUsername] = useState(SignInService.getInitialUsername());
const[password, setPassword] = useState("");
const[checked, setChecked] = useState(localStorage.getItem("checked") === "true" ? true : false);

// React hooks
const navigate = useNavigate();

// Component functions
function handleSubmit(event) {
    event.preventDefault();
    SignInService.signIn(username, password, checked, 
        function navigateAfterLogin(path) {
            navigate(path);
        });
}

function handleVisibilityButtonClick(event) {
    setPasswordVisible(SignInService.toggleVisibility(passwordVisible));
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
                        <Button className="expanded-sign-in-visibility-button" variant="outline-secondary" id="button-addon2" onClick={ handleVisibilityButtonClick }>
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