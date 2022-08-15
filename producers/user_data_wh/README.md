# Data Producer - User
A dummy data generator that will insert and n number of records into a remote or local database.


## Installation

ensure [python](https://www.python.org/downloads/) is installed on your system and accessible via your terminal path.

ensure the corresponding java micro service is running.
use the same certificate  for the microservice from ../src/main/resources/localhost.p12
and copy it somewhere else.

run this command to generate a .pem cert file. keep note of its full directory path.

```bash 
openssl pkcs12 -in localhost.p12 -out cert.pem -nodes

pip install -r requirements.txt
```

## Usage
edit .env file
CRT_PATH is the path to the .pem file generated from before
BASE_URL is url for the corresponding java micro service


```
BASE_URL=https://localhost:8443
LOGIN_ROUTE=/user/login 
CRT_PATH=cert.pem
```

```bash
python data_producer.py [args]

```
