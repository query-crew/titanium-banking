FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

ENV jwtSecret=querycrewtitaniumbanksecretjwt
ENV jwtExpirationMs=86400000
ENV spring.jpa.hibernate.ddl-auto=update
ENV spring.datasource.url="jdbc:mysql://localhost:3306/TitaniumBanking"
ENV spring.datasource.username="root"
ENV spring.datasource.password="Wxvm85k1"
ENV spring.properties.hibernate.dialect="org.hibernate.dialect.MySQL5InnoDBDialect"
ENV spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
ENV spring.mvc.pathmatch.matching-strategy=ANT_PATH_MATCHER
ENV server.port=8444
ENV server.ssl.key-store=classpath:localhost.p12
ENV server.ssl.key-store-type=PKCS12
ENV server.ssl.key-store-password=changeit
ENV security.basic.enabled=false
ENTRYPOINT ["java", "-jar", "/app.jar"]

