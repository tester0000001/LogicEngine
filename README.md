# LogicEngine

**LogicEngine** is a Spring Boot API that creates, stores, and evaluates logical expressions using custom logic, powered by an in-memory H2 database.

## Table of Contents
- [API Endpoints](#api-endpoints)
- [Building the Project](#building-the-project)
- [Running the Project](#running-the-project)
- [Curl Examples](#curl-examples)

## API Endpoints

### 1. **POST /expression**

- **Input:**
  - **Name:** The name of the logical expression.
  - **Value:** The logical expression to be stored (e.g., `customer.firstName == "JOHN" && customer.salary < 100`).

- **Response:**
  - A unique identifier (ID) for the newly created logical expression.

### 2. **POST /evaluate**

- **Input:**
  - **Expression ID:** The unique ID of the stored expression.
  - **JSON Object:** The object to evaluate the logical expression against.

- **Response:**
  - The result of the evaluation (`true`/`false`).

## Building the Project

To build the project, run the following command:

      mvn clean install

## Running the Project

      mvn spring-boot:run

## Curl examples
  ### Customer example
  #### /expression
         curl -X POST http://localhost:8080/expression -H "Content-Type: application/json" -d '{
             "name": "Check salary and city for Pero",
             "value": "(customer.firstName == \"Pero\" && customer.salary > 4200) OR (customer.address != null && customer.address.city == \"Varaždin\")"
         }'

   #### /evaluate -true
      curl -X POST http://localhost:8080/evaluate -H "Content-Type: application/json" -d '{
          "expressionId": 1,
          "data": {
              "customer": {
                  "firstName": "Pero",
                  "lastName": "Kovač",
                  "address": {
                      "city": "Varaždin",
                      "zipCode": 42000,
                      "street": "Ivana Kukuljevića",
                      "houseNumber": 10
                  },
                  "salary": 4900,
                  "type": "INDIVIDUAL"
              }
          }
      }'


#### /evaluate -false
      curl -X POST http://localhost:8080/evaluate -H "Content-Type: application/json" -d '{
          "expressionId": 1,
          "data": {
              "customer": {
                  "firstName": "Pero",
                  "lastName": "Kovač",
                  "address": {
                      "city": "Varaždin",
                      "zipCode": 42000,
                      "street": "Ivana Kukuljevića",
                      "houseNumber": 10
                  },
                  "salary": 3800,
                  "type": "INDIVIDUAL"
              }
          }
      }'
      
 ### Product example
  #### /expression
         curl -X POST http://localhost:8080/expression -H "Content-Type: application/json" -d '{
              "name": "Check product eligibility for discount",
              "value": "(product.price < 100 && product.stock > 0) OR (product.category == \"electronics\" && product.isOnSale == true)"
          }'


   #### /evaluate -true
      curl -X POST http://localhost:8080/evaluate -H "Content-Type: application/json" -d '{
            "expressionId": 2,
            "data": {
                "product": {
                    "name": "Wireless Headphones",
                    "price": 89.99,
                    "stock": 15,
                    "category": "electronics",
                    "isOnSale": true
                }
            }
        }'



#### /evaluate -false
    curl -X POST http://localhost:8080/evaluate -H "Content-Type: application/json" -d '{
        "expressionId": 2,
        "data": {
            "product": {
                "name": "Coffee Maker",
                "price": 120.00,
                "stock": 0,
                "category": "kitchen",
                "isOnSale": false
            }
        }
    }'
