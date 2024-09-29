# LogicEngine
LogicEngine - A Spring Boot API that creates, stores, and evaluates logical expressions using custom logic, powered by an in-memory H2 database.

# API Endpoints
1. **/expression**
   
  Input:
    Name: The name of the logical expression.
    
  Value: The logical expression to be stored (e.g., customer.firstName == "JOHN" && customer.salary < 100).

  Response:
    A unique identifier (ID) for the newly created logical expression.

2. **/evaluate**

   Input:
     Expression ID: The unique ID of the stored expression.

   JSON Object: The object to evaluate the logical expression against.

   Response:
      The result of the evaluation (true/false).

4. **Build the project using Maven**
   ```shell
    mvn clean install


5. **Running the Project**
   ```shell
   mvn spring-boot:run


