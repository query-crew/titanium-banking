import React, { useState } from "react";
import "../styles/SignInCard.css";
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
import { useDispatch } from 'react-redux';
import { setToken } from '../redux/tokenReducer';
import { Link, useNavigate } from "react-router-dom";
import SignInService from "../services/SignInService";

function SignInCard(props) {

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
            <Button className="visibility-button" variant="outline-secondary" id="button-addon2" onClick={ handleVisibilityButtonClick }>
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
