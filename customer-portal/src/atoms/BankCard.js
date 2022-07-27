import React, { useState, useEffect } from "react";
import WifiIcon from '@mui/icons-material/Wifi';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Container from 'react-bootstrap/Container';
import './BankCard.css'

function BankCard() {
    
    return (
      <div id="override-bootstrap-bank-card" className="bank-card">
        <div className="bank-title" style={{ color: "#d85c27" }}>
            T
            <span className="bank-title white" style={{ color: "white" }}>
              ITANIUM BANKING
            </span>
        </div>
        <Container>
            <Row className="card-description-row">
                <Col md={1} className="d-flex align-items-center">
                    <img className="card-chip" src={require('../chip.png')}/>
                </Col>
                <Col md={3} className="d-flex align-items-center wifi-col">
                    <WifiIcon className="wifi-icon"/>
                </Col>
                <Col className="d-flex align-items-center card-description-text">
                    <div className="card-text">
                        CREDIT
                    </div>
                </Col>
            </Row>
            <div className="card-description-and-logo">
                <Row>
                    <Col className="d-flex justify-content-end">
                        <div className="x-small-card-description">
                            CREDIT
                        </div>
                    </Col>
                </Row>
                <Row>
                    <Col className="d-flex justify-content-end">
                        <img className="visa-logo" src={require('../visa.png')}/>
                    </Col>
                </Row>
            </div>
        </Container>
      </div>
    );
  }
  
  export default BankCard;