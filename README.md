**RentRead - Book Rental System API**

**Overview**

RentRead is a RESTful API service for managing an online book rental system. The service uses Spring Boot for the backend and PostgreSQL for data persistence. The system supports user registration, book management, and book rental operations with Basic Authentication and role-based access control.

**Features**

User Registration and Login: Users can register and log in using email and password.
Book Management: Admins can create, update, and delete books. All users can browse available books.
Rental Management: Users can rent and return books. Users are limited to two active rentals at a time.
Role-Based Access Control: Basic Authentication with roles USER and ADMIN.

**Prerequisites**

Java 17 or higher
Maven or Gradle
PostgreSQL
Postman or curl for testing (optional)

**Setup**

1. Clone the Repository
bash
Copy code
git clone https://github.com/yourusername/rentread.git
cd rentread
2. Configure Database
Edit the src/main/resources/application.properties file to include your PostgreSQL database settings:

properties
Copy code
spring.datasource.url=jdbc:postgresql://localhost:5432/rentread
spring.datasource.username=yourusername
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
3. Build and Run the Application
Using Maven:

bash
Copy code
mvn clean package
java -jar target/rentread-0.0.1-SNAPSHOT.jar
Using Gradle:

bash
Copy code
./gradlew build
java -jar build/libs/rentread-0.0.1-SNAPSHOT.jar
The application will start on http://localhost:8080.

**API Endpoints**

Public Endpoints
Register User:
URL: /api/register
Login:
URL: /api/login
Get All Books:
URL: /api/books
Create Book (Admin Only):
URL: /api/books
Update Book (Admin Only):
URL: /api/books/{bookId}
Delete Book (Admin Only):
URL: /api/books/{bookId}
Rent a Book:
URL: /api/books/{bookId}/rent
Return a Book:
URL: /api/books/{bookId}/return

**Roles**

USER: Can browse available books, rent, and return books.
ADMIN: Can manage books (create, update, delete).

