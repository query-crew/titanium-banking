# Data Producer - User
A dummy data generator that will insert and n number of records into a remote or local database.

## Virtual Env Installation
using python venv is recommend way to ensure same dependency versioning and such
```bash
# create virtual env in producer path
python3 -m venv myenv

source myenv/bin/activate

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


examples:

python data_producer.py member 10

python data_producer.py user 50
```


## Testing

```bash
pytest test.py
```
