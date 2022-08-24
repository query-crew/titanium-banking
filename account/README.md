
# Account Microservice

The Account microservice was built to create, obtain, and maintain account and account types data.

## Installation

Pre-installation steps:
1. Download and install Java 17.
- https://www.oracle.com/java/technologies/downloads/#java17
2. Download and install Apache Maven.
- https://maven.apache.org/download.cgi

Installation:

1. Clone the repository

```bash
git clone https://github.com/query-crew/titanium-banking.git
```
2. Go to the account directory

```bash
cd account
```

## Usage

1. Create a jar file
```bash
mvn clean package
```

2. Navigate to the target file
```bash
cd target
```

3. Run the jar file
```bash
java -jar account-0.0.1-SNAPSHOT.jar
```

Utilize the REST API with GET, POST, PUT, and DELETE HTTP requests with your respective language.

CRUD Account paths:
- https://localhost:8444/account
- https://localhost:8444/account/{accountId}

GET:
- https://localhost:8444/account?pageNo=<number>&pageSize=<number>

Account object format:
{
    "balance": 500,
    "lastStatementDate": "2022-04-15",
    "enabled": 1,
    "memberId": 33,
    "accountTypeId: "22"
}

CRUD AccountType paths:
- https://localhost:8444/accountType
- https://localhost:8444/accountType/{accountTypeId}

Account type object format:
{
    "accountType": "Titanium Savings,
    "accountTypeAbbr": "Savings",
    "interest": 1,
    "balanceRequirement": 500,
    "loanId": "22"
}


## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.
