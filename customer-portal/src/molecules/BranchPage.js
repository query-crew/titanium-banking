import BankNavBar from "../atoms/BankNavBar";
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import "./BranchPage.css";
import axios from "axios";

function searchBranches() {

    let branchView = document.getElementById("branchView");
    axios.get("http://localhost:8080/branch").then(Response => {
        for(let x in Response.data.branches) {
            let el = document.createElement("div");
            el.innerHTML = "Name: " + Response.data.branches[x].branchName + " Details: " + Response.data.branches[x].branchDetails;
            branchView.appendChild(el);
        }
    });
}

function BranchPage() {
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
                            <label for = "branchSearch">Branch Name</label>
                            <input type = "text" name = "branchSearch"></input>
                        </Col>
                        <Col>
                            <label for = "city">City</label>
                            <select name = "city">
                                <option value = "city1">City 1</option>
                                <option value = "city2">City 2</option>
                                <option value = "city3">City 3</option>
                            </select>
                        </Col>
                        <Col>
                            <label for = "state">State</label>
                            <select name = "state">
                                <option value = "ca">California</option>
                                <option value = "az">Arizona</option>
                                <option value = "nv">Nevada</option>
                            </select>
                        </Col>
                        <Col>
                            <button type = "button" className = "btn btn-primary" onClick = {searchBranches}>Search</button>
                        </Col>
                    </Row>
                    <Row>
                        <div id = "branchView">
                            
                        </div>
                    </Row>
                </div>
            </div>
        </div>
    );
}

export default BranchPage;