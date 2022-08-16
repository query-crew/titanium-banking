import BankNavBar from "../atoms/LandingPageNavBar";
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import "../styles/BranchPage.css";
import { useEffect, useState } from "react";
import { Card } from "@mui/material";
import Modal from 'react-bootstrap/Modal';
import BranchViewService from '../services/BranchViewService';

function BranchPage() {
    const [branchList, setBranches] = useState([]);
    const [showModal, setShow] = useState(false);
    const [modalBranchDetails, setModalBranchDetails] = useState({});
    const [cityOptions, setCityOptions] = useState([]);
    const [stateOptions, setStateOptions] = useState([]);
    const closeModal = () => setShow(false);
    const openModal = () => setShow(true);

    function populateCitiesAndStates() {
        BranchViewService.searchBranches( (response) => {
            
            const branchResponse = response;
            let cities = [];
            let states = [];

            cities.push("All");
            states.push("All");     

            for(let x in branchResponse) {
                if(cities.includes(branchResponse[x].city) === false) {
                    cities.push(branchResponse[x].city)
                }
                if(states.includes(branchResponse[x].state) === false) {
                    states.push(branchResponse[x].state)
                }
            }
            setCityOptions(cities);
            setStateOptions(states);
        });
    }

    function branchHelper() {
        BranchViewService.searchBranches( (response) => {

            const branchNameInput = document.getElementById("branchName-input").value;
            const citySelect = document.getElementById("city-select").value;
            const stateSelect = document.getElementById("state-select").value;

            let searchCriteria = {};

            if(branchNameInput !== "") {
                searchCriteria.branchName = branchNameInput;
            }
            if(citySelect !== "All") {
                searchCriteria.city = citySelect;
            }
            if(stateSelect !== "All") {
                searchCriteria.state = stateSelect;
            }
            
            const filteredBranches = BranchViewService.filterBranches(response, searchCriteria);
            setBranches(filteredBranches);
        }, (error) => {
            console.log(error);
        });
    }
    
    useEffect(() => {
        populateCitiesAndStates();
        branchHelper();
    }, []);

    return (
        <div>
            <BankNavBar />
            <div className="mainDiv">
                <div className = "branchSearch">
                    <Row>
                        <p id = "branchSearchHeading">Branches</p>
                    </Row>
                    <Row>
                        <Col>
                            <label htmlFor = "branchSearch">Branch Name</label>
                            <input type = "text" name = "branchSearch" data-testid="branch-search-input" id = "branchName-input"></input>
                        </Col>
                        <Col>
                            <label htmlFor = "city">City</label>
                            <select name = "city" id = "city-select" data-testid="branch-search-city">
                                {
                                    cityOptions.map( (city) => {
                                        return <option key = {city} value = {city}>{city}</option>
                                    })
                                }
                            </select>
                        </Col>
                        <Col>
                            <label htmlFor = "state">State</label>
                            <select name = "state" id = "state-select" data-testid="branch-search-state">
                                {
                                    stateOptions.map( (statesInList) => {
                                        return <option key = {statesInList} value = {statesInList}>{statesInList}</option>
                                    })
                                }
                            </select>
                        </Col>
                        <Col>
                            <button type = "button" className = "btn btn-primary" onClick = {branchHelper}>Search</button>
                        </Col>
                    </Row>
                    <Row>
                        <div id = "branchView">
                            {
                                (branchList !== undefined && branchList.length > 0) ?
                                branchList.map( (branch) => 
                                    <div key = {branch.branchId}>
                                        <Card className = "branchCard">
                                            <Row>
                                                <Col>
                                                    <h4>{branch.branchName}</h4>
                                                    <h4>{branch.addressLine1}, {branch.city}, {branch.state} {branch.zipCode}</h4>
                                                </Col>
                                                <Col>
                                                    <button className = "btn btn-primary branchDetailsButton" onClick = {() => {
                                                        BranchViewService.branchDetails(branch.branchId, (response) => {
                                                            setModalBranchDetails(response);
                                                        }, (err) => {
                                                            console.log(err);
                                                        });
                                                        openModal();
                                                    }}>View Branch Details</button>
                                                </Col>
                                            </Row>
                                        </Card>
                                    </div>
                                ) : (
                                    <h4>No Results</h4>
                                )
                            }
                        </div>
                    </Row>
                </div>
            </div>
            <Modal show = {showModal} onHide = {closeModal}>
                <Modal.Dialog>
                    <Modal.Header closeButton>
                        <Modal.Title>{modalBranchDetails.branchName}</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        {modalBranchDetails.branchDetails}
                    </Modal.Body>
                    <Modal.Footer>
                        <button className = "btn btn-primary">Make Appointment at this Branch</button>
                    </Modal.Footer>
                </Modal.Dialog>
            </Modal>
        </div>
    );
}

export default BranchPage;