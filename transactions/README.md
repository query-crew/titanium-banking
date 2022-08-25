# Transactions microservice

The Transactions microservice creates and retrieves records of transactions between Accounts. 

## Security

A basic Spring Security configuration is in use for this microservice. To gain authorization, you must pass a string called "Authorization" in the request header with the following data:
```
  Authorization: Basic YWRtaW46cGFzc3dvcmQ=
```

## Transaction Types

Transaction types are currently represented as an integer in the Transactions model, with each integer representing a specific type. While this field can technically be any number (this allows enumeration of different types of transactions, if they need to be added), the following transaction types are enumerated as follows on the Angular frontend:
```
1: Debit (transactions originating from the account and paid to another account)
2: Credit (transactions being paid to the account)
3: Transfer (transactions originating from the account and transfered to another account that the user owns)
```

## Routes

GET /transaction/ - Gets a list of all transactions on the database

GET /transaction/{transactionId} - Gets a specific transaction specified by the transactionId.

GET /transaction/fromAccount/{accountId} - Gets a list of all transactions originating from the account specified in the request.

GET /transaction/toAccount/{accountId} - Gets a list of all transactions going to the account specified in the request.

GET /transaction/transactionType/{transactionType} - Gets a list of all transactions of the type specified in the request

GET /transaction/date/{transactionDate} - Gets a list of all transactions on the date specified in the request.

POST /transaction - Creates a transaction. Use the following headers with `Content-Type: application/json`:

```
  {
    "transactionType": INT,
    "transactionDate": DATE,
    "description": String,
    "amount": INT,
    "accountFromId": INT,
    "accountToId": INT
  }
```

PUT not available, as transactions should be immutable and cannot be changed once committed to the database.

DELETE not available, as transactions should not be deleted for auditing purposes.
