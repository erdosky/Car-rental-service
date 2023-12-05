The created project represents a car rental management system. 
It is characterized by a series of features concerning the management of cars, users, authorization, and car rental processes.

It is based on the Spring framework and uses the H2 Database, which is embedded in Spring. 
The database access is available at: http://localhost:8080/h2-console/login.jsp
The database login details (automatically configured in application.properties) are:

Driver Class: org.h2.Driver
JDBC URL: jdbc:h2:~/test
User Name: sa
Password: 

The database has been configured to save its state upon closure and return to it upon the next application startup.

The system's operation utilizes the SwaggerUI API, accessible at: http://localhost:8080/swagger-ui.html.