import React from "react";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Button from "react-bootstrap/Button";

const ActionPanel = (props) => {
  //maybe will use this to disabled current page button.
  // const curPage = props.curPage;
  return (
    <Container className="my-5 mx-10 ">
      <Row className="rounded-3 border shadow-lg bg-white justify-content-center">
        <div className="d-flex justify-content-center">
          <div className="fs-4">Actions</div>
        </div>
        <Row className="mb-3 g-2 justify-content-center">
          <Button variant="primary" size="md">
            Dashboard
          </Button>
        </Row>
        <Row className="mb-3 g-2 justify-content-center">
          <Button variant="primary" size="md" disabled={true}>
            Accounts
          </Button>
        </Row>
        <Row className="mb-3 g-2 justify-content-center">
          <Button variant="primary" size="md">
            Transfers
          </Button>
        </Row>
        <Row className="mb-3 g-2 justify-content-center">
          <Button variant="primary" size="md">
            Loans
          </Button>
        </Row>
        <Row className="mb-3 g-2 justify-content-center">
          <Button variant="primary" size="md">
            Open a New Account
          </Button>
        </Row>
      </Row>
    </Container>
  );
};

export default ActionPanel;
