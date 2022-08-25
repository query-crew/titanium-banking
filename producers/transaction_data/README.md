# Data Producer - Transactions
A dummy data generator that will insert and n number of records into a remote or local database.

## Virtual Env Installation
using python venv is recommend way to ensure same dependency versioning and such
```bash
# create virtual env in producer path
python3 -m venv venv

source venv/bin/activate

pip install -r requirements.txt
```

## Installation

ensure [python](https://www.python.org/downloads/) is installed on your system and accessible via your terminal path.

ensure the corresponding java micro service is running.

```bash 
pip install -r requirements.txt # IF NOT USING virtual env
```


## Usage
create an .env file
BASE_URL is url for the corresponding java micro service


```
AUTH_HEADER=Basic YWRtaW46cGFzc3dvcmQ=
BASE_URL=http://localhost:8080
TRANSACTION_ROUTE=/transaction/
FROM_ACCOUNT_ROUTE=/fromAccount/
TO_ACCOUNT_ROUTE=/toAccount/
TRANSACTION_TYPE_ROUTE=/transactionType/
DATE_ROUTE=/date/
```

```bash
python transaction_producer.py [args]


examples:

python transaction_producer.py -n 10 # creates 10 records and inserts them into the db

```


## Testing

```bash
pytest test.py
```

## To do 

will be adding verification and the creation of valid rather than arbitrary transactions for already existing accounts.


