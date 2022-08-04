import React from "react";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import "../styles/bootstrap.css";
import "../styles/LandingPageNavBar.css";
import { useMediaQuery } from 'react-responsive';

function LandingPageNavBar(props) {

  const smallScreen = useMediaQuery({ query: `(max-width: 992px)` });

  return (
    <Navbar id="nav-bar-override-bootstrap" expand="lg" variant="light" bg="primary">
      <Container className="nav-container">
        <Navbar.Brand>
          <div className="bankTitle" style={{ color: "#d85c27" }}>
            T
            <span className="bankTitle" style={{ color: "white" }}>
              ITANIUM
            </span>
          </div>
          <div className="bankTitle" style={{ color: "white" }}>
            BANKING
          </div>
        </Navbar.Brand>
        {smallScreen && props.signin && <Nav.Link className="enroll-button" href="/enroll">Enroll</Nav.Link>}
        {smallScreen && (props.home || props.enroll) && <Nav.Link className="sign-in-button" href="/signin">Sign In</Nav.Link>}
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="justify-content-end" style={{width:"100%"}}>
            {(props.enroll || props.signin) && <Nav.Link href="/">Home</Nav.Link>}
            {smallScreen && props.home && <Nav.Link className="enroll" href="/enroll">Enroll</Nav.Link>}
            <Nav.Link href="#home">ATMs/Locations</Nav.Link>
            <Nav.Link href="#link">Credit Cards</Nav.Link>
            <Nav.Link href="#home">Loans</Nav.Link>
          </Nav>
        </Navbar.Collapse>
        {!smallScreen && props.signin && <Nav.Link className="enroll-button" href="/enroll">Enroll</Nav.Link>}
        {!smallScreen && (props.home || props.enroll) && <Nav.Link className="sign-in-button" href="/signin">Sign In</Nav.Link>}
      </Container>
    </Navbar>
  );
}

export default LandingPageNavBar;
