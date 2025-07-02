# 🍽️ Food Ordering Service

A Spring Boot-based RESTful backend service for a Food Ordering System. This project handles restaurant listing, food item management, cart functionality, and order processing with secure user authentication.

---

## 🚀 Features

- User registration and login with **JWT-based authentication**
- Restaurant and food item CRUD operations
- Cart management (add, remove, update items)
- Order placement and history tracking
- Role-based access control (`USER`, `ADMIN`)
- API tested with **Talend API Tester** and **Postman**
- Integration with **MySQL** database using **Spring Data JPA**
- Modular and scalable structure with Maven

---

## 🛠️ Tech Stack

- **Java 21**
- **Spring Boot**
- **Spring Security** (JWT Authentication)
- **Spring Data JPA**
- **Hibernate ORM**
- **MySQL**
- **Talend API Tester** / **Postman**
- **Maven**
- **Git**

---

## 🗂️ Project Structure

food-ordering-service/
├── src/
│ ├── main/
│ │ ├── java/com/example/foodordering/
│ │ │ ├── controller/
│ │ │ ├── model/
│ │ │ ├── repository/
│ │ │ ├── service/
│ │ │ ├── security/
│ │ │ └── FoodOrderingServiceApplication.java
│ │ └── resources/
│ │ ├── application.properties
│ │ └── data.sql
└── pom.xml

