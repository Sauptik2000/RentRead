**RentRead - Book Rental System API**

**Overview**

RentRead is a RESTful API service for managing an online book rental system. The service uses Spring Boot for the backend and PostgreSQL for data persistence. The system supports user registration, book management, and book rental operations with Basic Authentication and role-based access control.

**Features**

User Registration and Login: Users can register and log in using email and password.
Book Management: Admins can create, update, and delete books. All users can browse available books.
Rental Management: Users can rent and return books. Users are limited to two active rentals at a time.
Role-Based Access Control: Basic Authentication with roles USER and ADMIN.

**Prerequisites**

1. Java 17 or higher
2. Maven or Gradle
3. PostgreSQL
4. Postman or curl for testing (optional)

**API Endpoints**

1. Register User: /api/register
2. Login: /api/login
3. Get All Books: /api/books
5. Create Book (Admin Only): /api/books
6. Update Book (Admin Only): /api/books/{bookId}
7. Delete Book (Admin Only): /api/books/{bookId}
8. Rent a Book: /api/books/{bookId}/rent
9. Return a Book: /api/books/{bookId}/return

**Roles**

USER: Can browse available books, rent, and return books.
ADMIN: Can manage books (create, update, delete).

