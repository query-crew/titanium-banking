
# User Microservice

The User microservice was built to create, obtain, and maintain user data including customer data and admin data.
The service itself has user authentication and authorization built in.

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
2. Go to the user directory

```bash
cd user
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
java -jar user-0.0.1-SNAPSHOT.jar
```

Utilize the REST API with GET, POST, PUT, and DELETE HTTP requests with your respective language.
Currently there are 4 API endpoints. 

Post and put requests must include a user, member, or login object matching the specified object format.

POST:

To create a user:
https://localhost:4443/user

User object format:
{
    "userType": "admin",
    "email": "iamauser@gmail.com",
    "username": "iamauser",
    "password": "mypass"
}

To create a member:
https://localhost:4443/member

Member object format:

{
    "email": "myemail2@gmail.com",
    "username": "test10",
    "password": "Testpassssss1",
    "firstName": "chloe",
    "lastName": "johnson",
    "phone": "(208)954-1744",
    "dateOfBirth": "1997-04-15",
    "socialSecurityNumber": "543-21-9871",
    "addressLine1": "1337 N Highwood Ave",
    "city": "Boise",
    "state": "Idaho",
    "zipcode": "83713"
}

To generate a JWT token cookie and login:
https://localhost:4443/user/login

Login object format:

{
    "username": "iamauser",
    "password": "mypass"
}

GET:
To return an authorization role such as member or admin:
https://localhost:4443/user/authorize


## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.
