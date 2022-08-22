## Docker containers individually

we use create a network, set network to host network
so that microservices can connect to localhost

in production, we would not use that. but we can use the
images we create to better connect them with docker swarm or docker-compose
BUT for now we're creating and using individual containers without proper networking

# Application.properties
1. adhere to confluence pre-defined [format](https://chloejohnson.atlassian.net/wiki/spaces/QUERYCREW/pages/6356998/Main)
2. port must be set that corresponds with micro service from [here](https://chloejohnson.atlassian.net/wiki/spaces/QUERYCREW/pages/6291457/Dev+environment+variables)
3. database is currently TitaniumBanking, unsure if they should have individual ones
4. username is root, pass is Wxvm85k1
5. if using https, must included .p12 file, compile, then build docker image


# Run the database, necessary for all microservices
before building, you can edit .sql for schema, but for now all we do is create a db
you can edit Dockerfile to change root password or port 
```bash
cd db
sudo docker build -t titanium-sql:latest .
sudo docker run --network=host --name=titanium-sql -p 3306:3306 titanium-sql:latest
```

# Build the image, change /user with respective microservice
An example of how to build the user microservice
cd into micro service directory where a Dockerfile will be, run docker build after compiling the microservice. 
```bash
cd user
mvn clean package # if you want to update it or recompile it
sudo docker build -t titanium/user:latest .
sudo docker run --network=host titanium/user:latest
```

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
- pull images and set container env variables using docker-compose
