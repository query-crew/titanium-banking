import BankNavBar from "../atoms/BankNavBar";
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import "./BranchPage.css";
import { useEffect, useState } from "react";
import { Card } from "@mui/material";
import Modal from 'react-bootstrap/Modal';
import BranchViewService from '../services/BranchViewService';

function BranchPage() {
    const [branchList, setBranches] = useState([]);
    const [showModal, setShow] = useState(false);
    const [modalBranchDetails, setModalBranchDetails] = useState({});
    const closeModal = () => setShow(false);
    const openModal = () => setShow(true);

    function branchHelper() {
        BranchViewService.searchBranches( (response) => {
            const branchNameInput = document.getElementById("branchName-input").value;
            const citySelect = document.getElementById("city-select").value;
            const stateSelect = document.getElementById("state-select").value;

            let searchCriteria = {};

            if(branchNameInput !== "") {
                searchCriteria.branchName = branchNameInput;
            }
            if(citySelect !== "all") {
                searchCriteria.city = citySelect;
            }
            if(stateSelect !== "all") {
                searchCriteria.state = stateSelect;
            }
            
            const filteredBranches = BranchViewService.filterBranches(response, searchCriteria);
            console.log(filteredBranches);
            setBranches(filteredBranches);
        }, (error) => {
            console.log(error);
        });
    }
    
    useEffect(() => {
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
                                <option value = "all">All</option>
                                <option value = "San Francisco">San Francisco</option>
                                <option value = "Los Angeles">Los Angeles</option>
                                <option value = "Fresno">Fresno</option>
                                <option value = "Reno">Reno</option>
                                <option value = "Las Vegas">Las Vegas</option>
                                <option value = "Phoenix">Phoenix</option>
                                <option value = "Tuscon">Tuscon</option>
                            </select>
                        </Col>
                        <Col>
                            <label htmlFor = "state">State</label>
                            <select name = "state" id = "state-select" data-testid="branch-search-state">
                                <option value = "all">All</option>
                                <option value = "California">California</option>
                                <option value = "Arizona">Arizona</option>
                                <option value = "Nevada">Nevada</option>
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
                                                    <h4>{branch.branchDetails}</h4>
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