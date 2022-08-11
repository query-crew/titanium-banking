import React from "react";
import Transaction from "./Transaction";

const TransactionList = (props) => {
  return (
    <>
      {(props.transactions === undefined || props.transactions.length) === 0 ? (
        <div>No transactions found</div>
      ) : (
        props.transactions.map((transaction) => (
          <Transaction
            key={transaction.transactionId}
            transaction={transaction}
            from={props.from}
          />
        ))
      )}
    </>
  );
};

export default TransactionList;
