import BankNavBar from "../atoms/BankNavBar";
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import "./BranchPage.css";
import axios from "axios";
import { useState } from "react";
import { Card } from "@mui/material";
import ModalHeader from 'react-bootstrap/esm/ModalHeader';
import Modal from 'react-bootstrap/Modal';

function BranchPage() {
    const [branchList, setBranches] = useState([]);
    const [showModal, setShow] = useState(false);
    const [modalBranchDetails, setModalBranchDetails] = useState({});

    const closeModal = () => setShow(false);
    const openModal = () => setShow(true);
 
    function branchDetails(branchId) {
        axios.get("http://localhost:8080/branch/" + branchId).then( (Response) => {
            setModalBranchDetails(Response.data.branch);
            openModal();
        });
    }

    function searchBranches() {
        axios.get("http://localhost:8080/branch").then( (Response) => {
            const newBranchList = Response.data.branches;
            setBranches(newBranchList);
        });
    }

    function branchHelper() {
        setBranches(searchBranches);
    }
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
                            <input type = "text" name = "branchSearch"></input>
                        </Col>
                        <Col>
                            <label htmlFor = "city">City</label>
                            <select name = "city">
                                <option value = "sf">San Francisco</option>
                                <option value = "la">Los Angeles</option>
                                <option value = "fre">Fresno</option>
                                <option value = "fre">Reno</option>
                                <option value = "fre">Las Vegas</option>
                                <option value = "fre">Phoenix</option>
                                <option value = "fre">Tuscon</option>
                            </select>
                        </Col>
                        <Col>
                            <label htmlFor = "state">State</label>
                            <select name = "state">
                                <option value = "ca">California</option>
                                <option value = "az">Arizona</option>
                                <option value = "nv">Nevada</option>
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
                                                    <button className = "branchDetailsButton" onClick = {() => branchDetails(branch.branchId)}>View Branch Details</button>
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
                    <ModalHeader closeButton>
                        <Modal.Title>{modalBranchDetails.branchName}</Modal.Title>
                    </ModalHeader>
                    <Modal.Body>
                        {modalBranchDetails.branchDetails}
                    </Modal.Body>
                </Modal.Dialog>
            </Modal>
        </div>

    );
}

export default BranchPage;