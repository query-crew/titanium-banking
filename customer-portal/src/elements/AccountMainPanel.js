import React from "react";
import Card from "react-bootstrap/Card";
import Tab from "react-bootstrap/Tab";
import Tabs from "react-bootstrap/Tabs";
import Container from "react-bootstrap/Container";

import TransactionTab from "./TransactionTab";
import AccountDetailsTab from "./AccountDetailsTab";
import CardsTab from "./CardsTab";

const AccountPanel = () => {
  return (
    <Container>
      <Card>
        <Card.Header>Current account name/type</Card.Header>
        <Card.Body>
          Current account info or something can make this smaller
        </Card.Body>
      </Card>
      <Container className="border my-1">
        <Tabs
          aria-label="account_tabs"
          defaultActiveKey="transactions"
          className="mb-3 my-1"
          fill
          mountOnEnter
        >
          <Tab eventKey="transactions" title="Transactions">
            <TransactionTab accountNumber={1} />
          </Tab>
          <Tab eventKey="cards" title="Cards">
            <CardsTab />
          </Tab>
          <Tab eventKey="invest" title="Invest">
            Invest
          </Tab>
          <Tab eventKey="details" title="Account Details">
            <AccountDetailsTab />
          </Tab>
          <Tab eventKey="analytics" title="Analytics">
            Analytics
          </Tab>
        </Tabs>
      </Container>
    </Container>
  );
};

export default AccountPanel;
