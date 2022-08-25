import React from "react";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import InputGroup from "react-bootstrap/InputGroup";

const SearchBar = (props) => {
  const onSubmit = (e) => {
    e.preventDefault();
    props.handleSubmit();
  };
  return (
    <InputGroup className="mb-3">
      <Form.Control
        placeholder="Search Transactions"
        aria-label="search_transactions"
        onChange={(e) => props.setSearch(e.target.value)}
      />
      <Button variant="outline-secondary" onClick={onSubmit}>
        Search
      </Button>
    </InputGroup>
  );
};

export default SearchBar;
