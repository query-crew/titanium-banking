FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
#ENV spring.datasource.url="jdbc:mysql://localhost:3306/TitaniumBanking"
#ENV spring.datasource.username="root"
#ENV spring.datasource.password="Wxvm85k1"
#ENV spring.properties.hibernate.dialect="org.hibernate.dialect.MySQL5InnoDBDialect"
ENTRYPOINT ["java", "-jar", "/app.jar"]
