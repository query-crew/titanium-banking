import React, { useEffect, useState } from "react";
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import FloatingLabel from "react-bootstrap/FloatingLabel";
import Card from 'react-bootstrap/Card';
import axios from "axios";
import "../styles/LandingPageNavBar.css"
import "../styles/MemberRegistrationCard.css";
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import validator from 'validator';
import { useNavigate } from "react-router-dom";
import Container from 'react-bootstrap/Container';
import RegistrationService from '../services/RegistrationService';
import { SettingsPhoneOutlined } from "@mui/icons-material";

const listOfStates = [{"Alabama": "AL"}, {"Alaska": "AK"}, {"Arizona": "AZ"}, {"Arkansas": "AR"}, {"California": "CA"},
{"Colorado": "CO"}, {"Connecticut": "CT"}, {"Delaware": "DE"}, {"Florida": "FL"}, {"Georgia": "GA"}, {"Hawaii": "HI"},
{"Idaho": "ID"}, {"Illinois": "IL"}, {"Indiana": "IN"}, {"Iowa": "IA"}, {"Kansas": "KS"}, {"Kentucky": "KY"},
{"Louisiana": "LA"}, {"Maine": "ME"}, {"Maryland": "MD"}, {"Massachusetts": "MA"}, {"Michigan": "MI"},
{"Minnesota": "MN"}, {"Mississippi": "MS"}, {"Missouri": "MO"}, {"Montana": "MT"}, {"Nebraska": "NE"},
{"Nevada": "NV"}, {"New Hampshire": "NH"}, {"New Jersey": "NJ"}, {"New Mexico": "NM"}, {"New York": "NY"},
{"North Carolina": "NC"}, {"North Dakota": "ND"}, {"Ohio": "OH"}, {"Oklahoma": "OK"}, {"Oregon": "OR"},
{"Pennsylvania": "PA"}, {"Rhode Island": "RI"}, {"South Carolina": "SC"}, {"South Dakota": "SD"}, 
{"Tennessee": "TN"}, {"Texas": "TX"}, {"Utah": "UT"}, {"Vermont": "VT"}, {"Virginia": "VA"}, {"Washington": "WA"},
{"West Virginia": "WV"}, {"Wisconsin": "WI"}, {"Wyoming": "WY"}, {"District of Columbia": "DC"}, {"American Samoa": "AS"},
{"Guam": "GU"}, {"Northern Mariana Islands": "MP"}, {"Puerto Rico": "PR"}, {"U.S. Virgin Islands": "VI"}];

function MemberRegistrationCard() {
    
    const[email, setEmail] = useState("");
    const[username, setUsername] = useState("");
    const[password, setPassword] = useState("");
    const[firstName, setFirstName] = useState("");
    const[lastName, setLastName] = useState("");
    const[phone, setPhone] = useState("");
    const[dob, setDob] = useState("");
    const[social, setSocial] = useState("");
    const[addressLine1, setAddressLine1] = useState("");
    const[addressLine2, setAddressLine2] = useState("");
    const[state, setState] = useState("State");
    const[city, setCity] = useState("");
    const[zipcode, setZipcode] = useState("");
    const[submitDisabled, setSubmitDisabled] = useState(true);

    const navigate = useNavigate();

    useEffect(() => {
        if (RegistrationService.formValid(email, username, password, firstName, lastName, phone, dob, social, addressLine1,
            addressLine2, state, city, zipcode)) {
            setSubmitDisabled(false);
        }
        else {
            setSubmitDisabled(true);
        }
    }, [email, username, password, firstName, lastName, phone, dob, social, addressLine1, addressLine2, state, city, zipcode])

    function handleSubmit(event) {
        event.preventDefault();
        RegistrationService.addBankMember(email, username, password, firstName, lastName, phone, 
            dob, social, addressLine1, addressLine2, state, city, zipcode,
            function onSuccess() {
                navigate("/");
            });
    }

    function checkRegistration() {
        RegistrationService.checkRegistration(email, username, password, firstName, lastName,
            phone, dob, social, addressLine1, addressLine2, city, state, zipcode, submitDisabled);
    }


    const populateSelectList = RegistrationService.listOfStates.map(state => (
            <option key={Object.values(state)[0]} value={Object.values(state)[0]}>{Object.keys(state)[0]}</option>
    ));

    return (
      <Container fluid id="member-registration-card-override-bootstrap">
        <Card className="member-registration-card">
            <Form onSubmit={handleSubmit}>
            <Card.Header className="member-registration-card-header">
                <h2 className="title">Enroll</h2>
            </Card.Header>
            <Card.Body>
                <Row>
                    <Col md={6} className="user-col">
                        <h3>Sign In Info</h3>
                        <Form.Group>
                            <FloatingLabel
                                label="Email"
                                className="mb-3"
                            >
                                <Form.Control type="email" placeholder="Email" value={email} onChange={e => setEmail(e.target.value)}/>
                            </FloatingLabel>
                        </Form.Group>
                        <Form.Group>
                            <FloatingLabel
                                label="Username"
                                className="mb-3"
                            >
                                <Form.Control type="text" placeholder="Username" value={username} maxLength={100} onChange={e => setUsername(e.target.value)}/>
                            </FloatingLabel>
                        </Form.Group>
                        <Form.Group>
                            <FloatingLabel className="mb-3" controlId="floatingPassword" label="Password">
                                <Form.Control type="password" placeholder="Password" value={password} maxLength={100} onChange={e => setPassword(e.target.value)}/>
                            </FloatingLabel>
                        </Form.Group>
                        <h3>Personal Info</h3>
                        <Row>
                            <Col md={6}>
                                <Form.Group>
                                    <FloatingLabel
                                        label="First Name"
                                        className="mb-3"
                                    >
                                        <Form.Control type="text" placeholder="First Name" value={firstName} onChange={e => setFirstName(e.target.value)} maxLength={200}/>
                                    </FloatingLabel>
                                </Form.Group>
                            </Col>
                            <Col md={6}>
                                <Form.Group>
                                    <FloatingLabel
                                        label="Last Name"
                                        className="mb-3"
                                    >
                                        <Form.Control type="text" placeholder="Last Name" value={lastName} onChange={e => setLastName(e.target.value)} maxLength={200}/>
                                    </FloatingLabel>
                                </Form.Group>
                            </Col>
                        </Row>
                        <Form.Group>
                            <FloatingLabel
                                label="Phone"
                                className="mb-3"
                            >
                                <Form.Control type="tel" placeholder="Phone" value={phone} onChange={e => setPhone(RegistrationService.formatPhone(e.target.value))} maxLength={13}/>
                            </FloatingLabel>
                        </Form.Group>
                        <Form.Group>
                        <FloatingLabel
                            label="Date of Birth"
                            className="mb-3"
                        >
                            <Form.Control type="date" placeholder="Date of Birth" onChange={e => setDob(e.target.value)}/>
                        </FloatingLabel>
                        </Form.Group>
                        <Form.Group>
                            <FloatingLabel
                                label="Social Security Number"
                                className="mb-3"
                            >
                                <Form.Control type="text" placeholder="Social Security Number" value={ social } onChange={e => setSocial(RegistrationService.formatSocial(e.target.value))} maxLength={11}/>
                            </FloatingLabel>
                        </Form.Group>
                    </Col>
                    <Col md={6} className="address-col">
                        <h3>Address</h3>
                        <Form.Group>
                            <FloatingLabel
                                label="Address Line 1"
                                className="mb-3"
                            >
                                <Form.Control type="text" placeholder="Address Line 1" value={addressLine1} onChange={e => setAddressLine1(e.target.value)} maxLength={46}/>
                            </FloatingLabel>
                        </Form.Group>
                        <Form.Group>
                        <FloatingLabel
                            label="Address Line 2"
                            className="mb-3"
                        >
                            <Form.Control type="text" placeholder="Address Line 2" value={addressLine2} onChange={e => setAddressLine2(e.target.value)} maxLength={46}/>
                        </FloatingLabel>
                        </Form.Group>
                        <Form.Group>
                            <FloatingLabel
                                label="City"
                                className="mb-3"
                            >
                                <Form.Control type="text" placeholder="City" value={city} onChange={e => setCity(e.target.value)} maxLength={100}/>
                            </FloatingLabel>
                        </Form.Group>
                        <Form.Group>
                            <Form.Select aria-label="State" className="mb-3 state" value={state} onChange={e => setState(e.target.value)}>
                                <option>State</option>
                                { populateSelectList }
                            </Form.Select>
                        </Form.Group>
                        <Form.Group>
                            <FloatingLabel
                                label="Zipcode"
                                className="mb-3"
                            >
                                <Form.Control type="text" placeholder="Zipcode" value={ zipcode } onChange={e => setZipcode(RegistrationService.formatZipcode(e.target.value))} maxLength={10}/>
                            </FloatingLabel>
                        </Form.Group>
                    </Col>
                </Row>
            </Card.Body>
            <Card.Footer>
                <Container fluid className="d-flex justify-content-end" onClick={ checkRegistration }>
                    <Button className={"submitButton" || (submitDisabled && "disabled")} type="submit">
                        Submit
                    </Button>
                    <ToastContainer/>
                </Container>
            </Card.Footer>
            </Form>
        </Card>
      </Container>
    );
  }
  
  export default MemberRegistrationCard;