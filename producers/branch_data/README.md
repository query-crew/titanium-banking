# Data Producer - Branch
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

```bash 
pip install -r requirements.txt # IF NOT USING virtual env
```


## Usage
edit .env file



```
BASE_URL=http://localhost:8080
INSERT_URL=/branch
GET_URL=/branch
DELETE_URL=/branch
```

```bash
python data_producer.py [args]
-n [number of records to insert]
-d (delete all records before executing other args)
-l (list all branches as json)


examples:

python branch_producer.py -n 10 -d -l # will delete all records, insert 10 records, and list the branchs

python branch_producer.py -d # delete all records

python branch_producer.py -l # list all records

python branch_producer.py -n 100 # insert 100 records

```


## Testing

```bash
pytest test.py
```
