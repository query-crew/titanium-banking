import React from "react";
import LandingPageNavBar from '../atoms/LandingPageNavBar';
import SignInCard from '../atoms/SignInCard';
import BankCard from '../atoms/BankCard';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Container from 'react-bootstrap/Container';
import Button from "react-bootstrap/Button";
import '../styles/HomePageSignIn.css'

function HomePageSignIn() {
    
    return (
      <div id="override-bootstrap-login-page" className="sign-in-container">
          <LandingPageNavBar home signin={false}/>
          <Container>
            <Row>
                <Col md={4}>
                    <SignInCard className="sign-in-card"/>
                </Col>
                <Col>
                    <div className="cashback-title-container">
                        <h1 className="cashback-title">Earn 5% cashback on everyday purchases</h1>
                    </div>
                    <Row>
                        <Col md={5}>
                            <p className="terms-apply-title">Terms Apply.</p>
                            <Button className="learn-more-button">Learn More</Button>
                        </Col>
                        <Col className="bank-card-col">
                            <BankCard/>
                        </Col>
                    </Row>
                </Col>
            </Row>
          </Container>
      </div>
    );
  }
  
  export default HomePageSignIn;