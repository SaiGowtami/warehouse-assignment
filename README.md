# warehouse-assignment
A Warehouse Application Implementation

## Requirements

For building and running the application you need:

- [JDK 1.8]
- [Maven 3]

Clone the project and use Maven to build the server

```shell
$ mvn clean install
```
Need a data base with schema (empty) as `warehouse` and update those details accordingly as shown below.
You can configure Database parameters in [application.properties](https://github.com/SaiGowtami/warehouse-assignment/blob/main/src/main/resources/application.yml):
```yaml
spring:
  sql:
    init:
      mode: always
  datasource:
    initialization-mode: always
    # Database URL
    url: jdbc:mysql://localhost:3306/warehouse?useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false
    # Database username
    username: root
    # Database password
    password: admin
  jpa:
    hibernate:
      ddl-auto: none
      # Database Dialect
      dialect: org.hibernate.dialect.MySQL5InnoDBDialect
```

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.warehouse.WarehouseAssignmentApplication` class from your IDE.

Alternatively you can use the Spring Boot Maven plugin like so:

```shell
mvn spring-boot:run
```
Or run the generate jar under target

Browser URL
Open your browser at the following URL for Swagger UI:
(http://localhost:8080/swagger-ui/index.html)
There you will find all the endpoints.

You can find the sample payloads under:
[Assignment](https://github.com/SaiGowtami/warehouse-assignment/tree/main/assignment) as inventory.json and products.json

#Point Notes:-
- Added columns for pre-caleculating the available product stock
- Updating the loggers 
- Adding more Swagger documents
- Handling more Exceptions
- Adding Java Doc comments
- Add Unit testcases
- Implimenting interfaces for controller and service


