import { useEffect, useState } from "react";
import { useNavigate } from 'react-router-dom';
import LandingPageNavBar from "../atoms/LandingPageNavBar";
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import '../styles/AccountRegister.css';
import AccountRegistrationService from "../services/AccountRegistrationService";

const AccountRegister = () => {
    
    const [accountTypes, setAccountTypes] = useState([]);
    const [selectedAccountType, setSelectedAccountType] = useState({});
    const navigate = useNavigate();

    const populateAccountTypes = () => {
        AccountRegistrationService.retrieveAccountTypes( (response) => {
            setAccountTypes(response.data.accountTypes);
            onSelectFunction();
        }, (err) => {
            console.log(err);
        });
    }

    const onClickRegisterAccount = () => {
        let initialDepositAmount = parseInt(document.getElementById("initialDepositInput").value * 100);
        console.log(initialDepositAmount);
        let accountType = selectedAccountType.accountTypeId;
        AccountRegistrationService.registerAccount(initialDepositAmount, accountType, (response) => {
            console.log(response);
            successfulRegistration();
        }, (err) => {
            console.log(err);
        });
    }

    const onSelectFunction = () => {
        let selectedValue = document.getElementById("accountTypesSelect").value;
        if(selectedValue === "default") {
            setSelectedAccountType({accountType: "None", interest: 0, balanceRequirement: 0});
            document.getElementById("registerAccountButton").disabled = true;
        }
        else {
            setSelectedAccountType(accountTypes[selectedValue - 1]);
            document.getElementById("registerAccountButton").disabled = false;
        }
    }

    const successfulRegistration = () => {
        let path = process.env.REACT_APP_SUCCESSFUL_ACCOUNT_REGISTRATION
        navigate(path);
    }

    useEffect( () => {
        populateAccountTypes();
    }, []);

    return (
        <div>
            <LandingPageNavBar />
            <div>
                <div className = "registerHeader">
                    Register For A New Account
                </div>
                <div className = "registerBody">
                    <Row>
                        <Col>
                            <label htmlFor="accountTypesSelect">Select Account Type: </label>
                        </Col>
                        <Col>
                            <select name = "accountTypesSelect" id = "accountTypesSelect" onChange = {onSelectFunction}>
                                <option value = "default"></option>
                                {
                                    accountTypes.map( (account) => {
                                        return (<option key = {account.accountAbbr} value = {account.accountTypeId}>{account.accountTypeAbbr}: {account.accountType}</option>);
                                    })
                                }
                            </select>
                        </Col>
                    </Row>
                    <Row>
                        <div>
                            <Row>
                                <div className="accountInfoDiv">
                                    <h4>Account Information</h4>
                                    <h6>Name: {selectedAccountType.accountType}</h6>
                                    <h6>Interest Rate: {selectedAccountType.interest / 100}%</h6>
                                    <h6>Minimum Balance: ${selectedAccountType.balanceRequirement}</h6>
                                </div>
                            </Row>
                        </div>
                    </Row>
                    <Row>
                        <Col>
                            <label htmlFor="initialDepositInput">Initial Deposit: </label>
                        </Col>
                        <Col>
                            <input type="number" id = "initialDepositInput" name="initialDepositInput" />
                        </Col>
                    </Row>
                    <button id = "registerAccountButton" className = "btn btn-primary" onClick={onClickRegisterAccount}>Register</button>
                </div>
            </div>
        </div>
    );
}

export default AccountRegister;