import React from "react";
import Card from "react-bootstrap/Card";
import Container from "react-bootstrap/Container";

const Transaction = (props) => {
  const { transaction, from } = props;
  const transactionDate = new Date(transaction.transactionDate);
  return (
    <Container className="gy-3">
      <Card>
        <div className="d-flex justify-content-between align-items-center">
          <div>{transactionDate.toLocaleDateString()}</div>
          <div>{transaction.description}</div>
          {from ? (
            <div>-${transaction.amount / 100}</div>
          ) : (
            <div>+${transaction.amount / 100}</div>
          )}
        </div>
        {from ? (
          <div>To Account: {transaction.accountToId}</div>
        ) : (
          <div>From Account: {transaction.accountFromId}</div>
        )}
      </Card>
    </Container>
  );
};

export default Transaction;
