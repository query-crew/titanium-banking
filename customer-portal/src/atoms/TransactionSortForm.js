import React, { useState } from "react";
import Accordion from "react-bootstrap/Accordion";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";

const TransactionSortForm = (props) => {
  const onSubmit = (e) => {
    e.preventDefault();
    props.handleSubmit();
  };
  return (
    <Accordion defaultActiveKey="0">
      <Accordion.Item eventKey="0">
        <Accordion.Header>Sorting/Filtering options</Accordion.Header>
        <Accordion.Body>
          <Form>
            <Form.Check
              name="sortOptions"
              type="radio"
              id="radio1"
              label="Date ascending"
              onChange={() => props.setSortProp("datea")}
            />
            <Form.Check
              name="sortOptions"
              type="radio"
              id="radio2"
              label="Date descending"
              onChange={() => props.setSortProp("dated")}
            />
            <Form.Check
              name="sortOptions"
              type="radio"
              id="radio3"
              label="Amount ascending"
              onChange={() => props.setSortProp("amounta")}
            />
            <Form.Check
              name="sortOptions"
              type="radio"
              id="radio4"
              label="Amount descending"
              onChange={() => props.setSortProp("amountd")}
            />
            <Button
              variant="outline-secondary"
              className="float-right"
              onClick={onSubmit}
            >
              Sort
            </Button>
          </Form>
        </Accordion.Body>
      </Accordion.Item>
    </Accordion>
  );
};

export default TransactionSortForm;
