import React from "react";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";

import ActionPanel from "../elements/ActionPanel";
import AccountMainPanel from "../elements/AccountMainPanel";
import "./AccountPage.css";
import AccountListPanel from "../elements/AccountListPanel";

const AccountPage = () => {
  return (
    <Container className="my-5 mx-10">
      <Row className="align-items-center rounded-3 border shadow-lg bg-white gy-3">
        <div className="d-flex justify-content-between align-items-center">
          <div className="fs-4">Accounts</div>
        </div>
        <Container>
          <Row>
            <Col lg={3}>
              <AccountListPanel />
            </Col>
            <Col lg={6}>
              <AccountMainPanel />
            </Col>
            <Col lg={3}>
              <ActionPanel />
            </Col>
          </Row>
        </Container>
        <Col lg={14} className="bs-linebreak" />
      </Row>
    </Container>
  );
};

export default AccountPage;
