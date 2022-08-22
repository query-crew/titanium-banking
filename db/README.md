## Docker containers individually
### Prereqs:
1. Be able to build each micro service locally with maven
2. have [Docker Engine](https://docs.docker.com/get-docker/) and [docker-compose](https://docs.docker.com/compose/install/)


we use create a network, set network to host network
so that microservices can connect to localhost

in production, we would not use that. but we can use the
images we create to better connect them with docker swarm or docker-compose

# Application.properties # can be defined in Dockerfiles
1. adhere to confluence pre-defined [format](https://chloejohnson.atlassian.net/wiki/spaces/QUERYCREW/pages/6356998/Main)
2. port must be set that corresponds with micro service from [here](https://chloejohnson.atlassian.net/wiki/spaces/QUERYCREW/pages/6291457/Dev+environment+variables)
3. database is currently TitaniumBanking, unsure if they should have individual ones
4. username is root, pass is Wxvm85k1
5. if using https, must include .p12 file, then compile, and then build docker image

# Build the images
cd into micro service directory where a Dockerfile will be, run docker build after compiling the microservice. 
- BUILD each micro service for compose to work
- tags are 0.1 right now
```bash
cd user
mvn clean package # if you want to update it or recompile it
sudo docker build -t titanium/user:0.1 .

cd account 
sudo docker build -t titanium/account:0.1 .

cd transactions
sudo docker build -t titanium/transactions:0.1 .

cd branch
sudo docker build -t titanium/branch:0.1 .
```

#  Build the Database
before building, you can edit .sql for schema, but for now all we do is create a db
you can edit Dockerfile to change root password or port 
```bash
cd db
sudo docker build -t titanium-sql:0.1 .
```

# Docker-compose deploy - Recommended
* build all images: db, user, transaction, branch
you may need to edit environment variables via docker-compose.yml
- IMPORTANT: put your localhost.p12 in a directory in crts/localhost.p12
ALL micro services will use this certificate, it makes the client side easier to manage as well.
right now only a few options: 
- spring.datasource.url
```bash
cd db

sudo docker-compose up
```

# Deploy without Compose
```bash
sudo docker run --network=host --name=titanium-sql -p 3306:3306 titanium-sql:0.1
sudo docker run --network=host titanium/user:0.1
sudo docker run --network=host titanium/transactions:0.1
sudo docker run --network=host titanium/branch:0.1
```

# Changing DB password
You will want rebuild the images and change the MYSQL_ROOT_PASS variable in the db/Dockerfile.
- reflect these changes in the Dockerfiles as well
There's a possibility the MySQL volume will not update as it is stateless, and once initialized it will not look to instantiate a root pass again. consider deleting or reseting the docker volume


#  Some Issues
tests and requests will fail unless proper TLS/SSL is setup.
Images are built with standardized [application.properties](https://chloejohnson.atlassian.net/wiki/spaces/QUERYCREW/pages/6356998/Main)

ports should be selected with this standardized approach from [confluence](https://chloejohnson.atlassian.net/wiki/spaces/QUERYCREW/pages/6291457/Dev+environment+variables)

# ENV Properties - Optional way to pass variables
These will take precendence over the application.properties
these are lines that need to be included or modified
Since we are using network=host in docker, we can use localhost for datasource
Why we have [properties.hibernate.dialect](https://github.com/spring-guides/gs-accessing-data-mysql/issues/38)
```bash
vim Dockerfile # in microservice directory
```

# To do
- Get all applications working with https
- push images to remote repository
