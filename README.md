# 🏢 Employee Leave Management System

A production-ready REST API built with **Spring Boot, Spring Security, JWT Authentication, and MySQL** that allows employees to apply for leaves and admins to approve or reject them.

---

## 🚀 Tech Stack

| Technology | Usage |
|---|---|
| Java 21 | Core programming language |
| Spring Boot 3.5.11 | Backend framework |
| Spring Security | Authentication & Authorization |
| JWT (JSON Web Token) | Stateless authentication |
| Spring Data JPA | Database operations |
| Hibernate | ORM mapping |
| MySQL 8.0 | Database |
| Maven | Dependency management |
| Postman | API testing |

---

## 📁 Project Structure
```
employee-leave-system/
├── src/main/java/com/hemanth/employee_leave_system/
│   ├── controller/
│   │   ├── AuthController.java        # Register & Login APIs
│   │   ├── EmployeeController.java    # Employee APIs
│   │   └── AdminController.java       # Admin APIs
│   ├── service/
│   │   ├── EmployeeService.java       # Employee business logic
│   │   └── LeaveService.java          # Leave business logic
│   ├── repository/
│   │   ├── EmployeeRepository.java    # Employee DB operations
│   │   └── LeaveRequestRepository.java # Leave DB operations
│   ├── entity/
│   │   ├── Employee.java              # Employee entity
│   │   └── LeaveRequest.java          # Leave request entity
│   ├── dto/
│   │   ├── RegisterRequest.java       # Register DTO
│   │   ├── LoginRequest.java          # Login DTO
│   │   ├── LeaveRequestDTO.java       # Leave request DTO
│   │   └── LeaveStatusDTO.java        # Leave status DTO
│   ├── security/
│   │   ├── JwtUtil.java               # JWT token generation & validation
│   │   ├── JwtFilter.java             # JWT request filter
│   │   └── SecurityConfig.java        # Spring Security configuration
│   └── Application.java               # Main class
├── src/main/resources/
│   └── application.properties         # App configuration
└── pom.xml                            # Maven dependencies
```

---

## ⚙️ Features

### 👤 Employee
- ✅ Register with email and password
- ✅ Login and get JWT token
- ✅ Apply for different types of leaves (Casual, Sick, Earned, Maternity)
- ✅ View personal leave history
- ✅ Cancel pending leave requests

### 🛡️ Admin
- ✅ View all employee leave requests
- ✅ View all pending leave requests
- ✅ Approve or Reject leave requests
- ✅ View all registered employees

### 🔐 Security
- ✅ JWT based stateless authentication
- ✅ BCrypt password encoding
- ✅ Role based access control (EMPLOYEE vs ADMIN)
- ✅ Protected API endpoints

---

## 🗄️ Database Schema
```sql
CREATE TABLE employees (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('EMPLOYEE', 'ADMIN') NOT NULL
);

CREATE TABLE leave_requests (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    employee_id BIGINT NOT NULL,
    leave_type ENUM('CASUAL','SICK','EARNED','MATERNITY') NOT NULL,
    from_date DATE NOT NULL,
    to_date DATE NOT NULL,
    reason VARCHAR(255) NOT NULL,
    status ENUM('PENDING','APPROVED','REJECTED') NOT NULL DEFAULT 'PENDING',
    FOREIGN KEY (employee_id) REFERENCES employees(id)
);
```

---

## 🚀 How to Run

### Prerequisites
- Java 21
- MySQL 8.0
- Maven 3.9+

### Database Setup
```sql
CREATE DATABASE employee_leave_db;
```

### Configure application.properties
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/employee_leave_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### Run the Application
```bash
mvn spring-boot:run
```

App starts at:
```
http://localhost:8080
```

---

## 📡 API Endpoints

### 🔓 Auth APIs (Public)

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/auth/register` | Register employee or admin |
| POST | `/api/auth/login` | Login and get JWT token |

### 👤 Employee APIs (Requires EMPLOYEE JWT Token)

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/employee/apply-leave` | Apply for leave |
| GET | `/api/employee/my-leaves/{id}` | Get my leave history |
| DELETE | `/api/employee/cancel-leave/{id}` | Cancel pending leave |

### 🛡️ Admin APIs (Requires ADMIN JWT Token)

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/admin/all-leaves` | Get all leave requests |
| GET | `/api/admin/pending-leaves` | Get pending leave requests |
| PUT | `/api/admin/update-leave-status` | Approve or reject leave |
| GET | `/api/admin/all-employees` | Get all employees |

---

## 📬 Sample API Requests

### Register Employee
```json
POST /api/auth/register
{
    "name": "Hemanth Vemula",
    "email": "hemanth@gmail.com",
    "password": "hemanth123",
    "role": "EMPLOYEE"
}
```

### Login
```json
POST /api/auth/login
{
    "email": "hemanth@gmail.com",
    "password": "hemanth123"
}
```

### Response
```json
{
    "token": "eyJhbGciOiJIUzI1NiJ9...",
    "role": "EMPLOYEE",
    "name": "Hemanth Vemula",
    "id": 1
}
```

### Apply for Leave
```json
POST /api/employee/apply-leave
Headers: Authorization: Bearer <token>
{
    "employeeId": 1,
    "leaveType": "SICK",
    "fromDate": "2026-04-01",
    "toDate": "2026-04-03",
    "reason": "Fever and cold"
}
```

### Approve Leave
```json
PUT /api/admin/update-leave-status
Headers: Authorization: Bearer <token>
{
    "leaveId": 1,
    "status": "APPROVED"
}
```

---

## 👨‍💻 Author

**Hemanth Vemula**
Java Backend Developer
📧 vemulahemanth194509@gmail.com
🔗 [LinkedIn](https://linkedin.com/in/vemula-hemanth)
⌨ [GitHub](https://github.com/vemulahemanth)

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).