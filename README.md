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
Register User

URL: /api/register
Method: POST
Request Body:
json
Copy code
{
  "email": "user@example.com",
  "password": "securepassword",
  "firstName": "John",
  "lastName": "Doe"
}
Response: 201 Created
Login

URL: /api/login
Method: POST
Request Body:
json
Copy code
{
  "email": "user@example.com",
  "password": "securepassword"
}
Response: 200 OK for successful login, 401 Unauthorized for invalid credentials
Private Endpoints
Get All Books

URL: /api/books
Method: GET
Response: List of all available books
Create Book (Admin Only)

URL: /api/books
Method: POST
Request Body:
json
Copy code
{
  "title": "Book Title",
  "author": "Author Name",
  "genre": "Fiction",
  "availabilityStatus": "AVAILABLE"
}
Response: 201 Created
Update Book (Admin Only)

URL: /api/books/{bookId}
Method: PUT
Request Body:
json
Copy code
{
  "title": "Updated Book Title",
  "author": "Updated Author Name",
  "genre": "Non-Fiction",
  "availabilityStatus": "UNAVAILABLE"
}
Response: 200 OK
Delete Book (Admin Only)

URL: /api/books/{bookId}
Method: DELETE
Response: 204 No Content
Rent a Book

URL: /api/books/{bookId}/rent
Method: POST
Response: 200 OK for successful rental, 400 Bad Request if user has 2 active rentals
Return a Book

URL: /api/books/{bookId}/return
Method: POST
Response: 200 OK for successful return
Security
Authentication
The API uses Basic Authentication. Users must provide their credentials to access private endpoints.

Roles
USER: Can browse available books, rent, and return books.
ADMIN: Can manage books (create, update, delete).
Logging
Logs are written to the console. Ensure that logging is enabled in application.properties for detailed debug information:

