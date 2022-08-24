# Data Producer - Accounts
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
use the same certificate  for the microservice from ../src/main/resources/localhost.p12
and copy it somewhere else.

run this command to generate a .pem cert file. keep note of its full directory path.

```bash 
openssl pkcs12 -in localhost.p12 -out cert.pem -nodes

pip install -r requirements.txt # IF NOT USING virtual env
```
## Note on *.p12 files
Since this producer relies on two micro services, both certificates need to be the same when building each micro service. Otherwise you will run into authentication issues


## Usage
- Requires user microservice to be running or accessible remotely
- Requires an admin in the bank_user table with known user and password
edit .env file
Example .env file

```
BASE_URL=https://localhost:8444
LOGIN_URL=https://localhost:8443/user/login
ADMIN_USER=iamauser2
ADMIN_PASS=mypass
CRT_PATH=cert.pem
ACCOUNT_ROUTE=/account
ACCOUNT_TYPE_ROUTE=/accountType
```

```bash
python accounts_producer.py [args]

examples:

python accounts_producer.py -n 10 -d -l # will delete all records, insert 10 records, and list the branchs

python accounts_producer.py -d # delete all records

python accounts_producer.py -l # list all records

python accounts_producer.py -n 100 # insert 100 records
```


## Testing

```bash
pytest test.py
```
