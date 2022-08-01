import React, { useEffect, useState } from "react";
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import FloatingLabel from "react-bootstrap/FloatingLabel";
import Card from 'react-bootstrap/Card';
import axios from "axios";
import "../styles/LandingPageNavBar.css"
import { StarRateSharp } from "@mui/icons-material";
import "../styles/MemberRegistrationCard.css";
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import validator from 'validator';

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
    const[phoneValid, setPhoneValid] = useState(true);
    const[dob, setDob] = useState("");
    const[social, setSocial] = useState("");
    const[socialValid, setSocialValid] = useState(false);
    const[addressLine1, setAddressLine1] = useState("");
    const[addressLine2, setAddressLine2] = useState("");
    const[state, setState] = useState("State");
    const[city, setCity] = useState("");
    const[zipcode, setZipcode] = useState("");
    const[zipcodeValid, setZipCodeValid] = useState(false);
    const[submitDisabled, setSubmitDisabled] = useState(true);

    useEffect(() => {
        if (validator.isEmail(email) && !validator.isEmpty(username) && isUsername(username) &&
        validator.isStrongPassword(password, {minLength: 8, minLowercase: 1,minUppercase: 1, minNumbers: 1}) &&
        isName(firstName) && isName(lastName) && validator.isMobilePhone(phone, 'en-US') && validator.isDate(dob) && 
        isSocial(social) && isAddressOne(addressLine1) && isAddressTwo(addressLine2) && isState(state) && isCity(city) &&
        validator.isPostalCode(zipcode, 'en-US')) {
            setSubmitDisabled(false);
        }
        else {
            setSubmitDisabled(true);
        }
    })

    function setAndCheckPhoneNumber(value) {
        let phoneVal = value;
        if (phoneVal.length > 3 && phoneVal.length < 7 && /^[^\(\)-]+$/.test(phoneVal)) {
            phoneVal = "(" + phoneVal.slice(0, 3) + ")" + phoneVal.slice(3);
            setPhone(phoneVal);
        }
        else if (phoneVal.length > 4 && phoneVal.length < 8 && /^[^\)-]+$/.test(phoneVal)) {
            phoneVal = phoneVal.slice(0, 4) + ")" + phoneVal.slice(4);
            setPhone(phoneVal);
        }
        else if (phoneVal.length > 8 && phoneVal.length < 13 && /^[^-]+$/.test(phoneVal)) {
            phoneVal = phoneVal.slice(0, 8) + "-" + phoneVal.slice(8);
            setPhone(phoneVal);
        }
        else {
            setPhone(phoneVal);
        }
        setPhoneValid(/^(\(\d{3}\)\d{3}-\d{4}|\(\d{3}\)\d{3}-\d{0,4}|\(\d{3}\)\d{0,3}|\(\d{1,3}\)|\(\d{1,3})$/.test(phoneVal));
    }

    function setAndCheckSocialSecurity(value) {
        let socialValue = value;
        if (socialValue.length > 3 && socialValue.length < 6 && /^[^-]+$/.test(socialValue)) {
            socialValue = socialValue.slice(0,3) + "-" + socialValue.slice(3);
            setSocial(socialValue);
        }
        else if (socialValue.length > 6 && socialValue.length < 11 && /^[^-]+-[^-]+$/.test(socialValue)) {
            socialValue = socialValue.slice(0, 6) + "-" + socialValue.slice(6);
            setSocial(socialValue);
        }
        else {
            setSocial(socialValue);
        }
        setSocialValid(!(/^([0-9]{3}\-[0-9]{2}\-[0-9]{4}|[0-9]{3}\-[0-9]{2}\-[0-9]{0,4}|[0-9]{3}\-[0-9]{0,2}|[0-9]{1,3})$/.test(socialValue)));
    }

    function setAndCheckZipcode(value) {
        let zipcodeValue = value;
        if (zipcodeValue.length > 5 && zipcodeValue.length < 10 && /^[^-]+$/.test(zipcodeValue)) {
            zipcodeValue = zipcodeValue.slice(0,5) + "-" + zipcodeValue.slice(5);
            setZipcode(zipcodeValue);
        }
        else {
            setZipcode(zipcodeValue);
        }
        setZipCodeValid(!(/^([0-9]{5}\-[0-9]{4}|[0-9]{5}\-[0-9]{0,4}|[0-9]{1,5})$/.test(zipcodeValue)));
        setZipcode(zipcodeValue);
    }

    function register() {
        axios.post("/user/member", )
        .then(response => { 
        })
        .catch(({ response }) => {
            toast.error(response.data, {
                position: "top-right",
                autoClose: false,
                hideProgressBar: true,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
                });
        });
    }

    function isUsername(value) {
        if (/^[a-zA-Z\d_.-]+$/) {
            return true;
        }
        return false;
    }

    function isName(value) {
        if (/^[^!@#$%^&*(),.?":{}|<>//d]+$/.test(value)) {
            return true;
        }
        return false;
    }

    function isSocial(value) {
        if (/^[0-9]{3}-[0-9]{2}-[0-9]{4}$/.test(value)) {
            return true;
        }
        return false;
    }

    function isAddressOne(value) {
        if (/^[\da-z-A-Z.-]+$/.test(value)) {
            return true;
        }
        return false;
    }

    function isAddressTwo(value) {
        if (/^(?![\s\S])|[\da-z-A-Z.-]+$/.test(value)) {
            return true;
        }
        return false;
    }

    function isCity(value) {
        if(/^[a-zA-Z]+$/.test(value)) {
            return true;
        }
        return false;
    }

    function isState(value) {
        return value !== "State";
    }

    function checkRegistration() {
        if (submitDisabled) {
            if (!validator.isEmail(email)) {
                toast.error("Email is invalid", {
                    position: "top-right",
                    autoClose: false,
                    hideProgressBar: true,
                    closeOnClick: true,
                    pauseOnHover: true,
                    draggable: true,
                    progress: undefined,
                    });
            }
        }
    }


    const populateSelectList = listOfStates.map(state => (
            <option key={Object.values(state)[0]} value={Object.values(state)[0]}>{Object.keys(state)[0]}</option>
    ));

    return (
      <div id="member-registration-card-override-bootstrap">
        <Card>
            <Card.Header>
                <h2>Enroll</h2>
            </Card.Header>
            <Card.Body>
                <Form>
                <Row>
                    <Col md="auto" className="user-col">
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
                            <Col md="auto">
                                <Form.Group>
                                    <FloatingLabel
                                        label="First Name"
                                        className="mb-3"
                                    >
                                        <Form.Control type="text" placeholder="First Name" maxLength={200}/>
                                    </FloatingLabel>
                                </Form.Group>
                            </Col>
                            <Col md="auto">
                                <Form.Group>
                                    <FloatingLabel
                                        label="Last Name"
                                        className="mb-3"
                                    >
                                        <Form.Control type="text" placeholder="Last Name" maxLength={200}/>
                                    </FloatingLabel>
                                </Form.Group>
                            </Col>
                        </Row>
                        <Form.Group>
                            <FloatingLabel
                                label="Phone"
                                className="mb-3"
                            >
                                <Form.Control type="tel" placeholder="Phone" value={phone} onChange={e => setAndCheckPhoneNumber(e.target.value)} isInvalid={!phoneValid} maxLength={13}/>
                            </FloatingLabel>
                        </Form.Group>
                        <Form.Group>
                        <FloatingLabel
                            label="Date of Birth"
                            className="mb-3"
                        >
                            <Form.Control type="date" placeholder="Date of Birth" onChange={e => setDob(e)}/>
                        </FloatingLabel>
                        </Form.Group>
                        <Form.Group>
                            <FloatingLabel
                                label="Social Security Number"
                                className="mb-3"
                            >
                                <Form.Control type="text" placeholder="Social Security Number" value={ social } onChange={e => setAndCheckSocialSecurity(e.target.value)} isInvalid={ socialValid } maxLength={11}/>
                            </FloatingLabel>
                        </Form.Group>
                    </Col>
                    <Col md={5} className="address-col">
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
                                <Form.Control type="text" placeholder="Zipcode" value={ zipcode } onChange={e => setAndCheckZipcode(e.target.value)} isInvalid={ zipcodeValid } maxLength={10}/>
                            </FloatingLabel>
                        </Form.Group>
                    </Col>
                </Row>
                </Form>
            </Card.Body>
            <Card.Footer>
                <div>
                    <Button className={submitDisabled && "disabled"} type="submit">
                        Submit
                    </Button>
                </div>
            </Card.Footer>
        </Card>
      </div>
    );
  }
  
  export default MemberRegistrationCard;