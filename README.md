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
- 20+ end-points thoroughly tested

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

## How To Run The Project

### 1. Clone the repository
on your git bash, please enter the following commands:

a) git clone https://github.com/whoa12/food-ordering-service.git

b) cd food-ordering-service

### 2. Configure the Database
Before running the application, please make sure that you have MySQL installed and running on your system.

Then, create a database named user_order_master. You can do this either in MySQL Workbench or using MySQL Shell:

CREATE DATABASE user_order_master;

(Please make sure that MySQL is running at port 3306 on your local machine)

### 3. Configure Credentials & Run the Application
In Git Bash (from inside the project directory), run the following command:

export DB_USERNAME=your_username && export DB_PASSWORD=your_password && mvn spring-boot:run

Please replace the placeholders with your actual MySQL username and password.

### 4. Endpoints:
Here are the main API endpoints you can try out:

POST   /auth/register       Registers a new user
POST   /auth/login          Login
POST   /api/admin/restaurants   makes a new restaurant
GET    /api/restaurants       gets all available restaurants


## Demo of some End-points:

User registration:
![image](https://github.com/whoa12/food-ordering-service/blob/main/Food-Ordering-Website/Screenshot%20(111).png?raw=true)


User Login:
![image](https://github.com/whoa12/food-ordering-service/blob/main/Food-Ordering-Website/Screenshot%20(112).png?raw=true)


creating a restaurant:
![image](https://github.com/whoa12/food-ordering-service/blob/main/Food-Ordering-Website/createRest.png?raw=true)


Food list by restaurant id:
[!image](https://github.com/whoa12/food-ordering-service/blob/main/Food-Ordering-Website/food%20list%20by%20restid.png?raw=true)


Getting category by restaurant id:
![image](https://github.com/whoa12/food-ordering-service/blob/main/Food-Ordering-Website/CatByRestId.png?raw=true)


Getting food list by category:
![image](https://github.com/whoa12/food-ordering-service/blob/main/Food-Ordering-Website/FoodListByCat.png?raw=true)
