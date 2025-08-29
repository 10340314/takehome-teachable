# Course Reporting Service

This project is a Spring Boot application that integrates with external services to generate reports of students enrolled in courses.
It uses Feign Clients to fetch courses, enrollments, and users from external APIs, and maintains a cache layer for performance optimization.

---

## Features

* Retrieve course information from external APIs
* Fetch enrollments by course ID
* Retrieve user details (full or summary)
* Generate a course report with enrolled students
* Cache student data and pagination metadata to optimize repeated lookups

---

## Tech Stack

* **Java 17+**
* **Spring Boot**
* **Spring Cloud OpenFeign**
* **JUnit 5 / Mockito** for testing

---

## Example: Course Report

The service generates a `CourseReport` containing:

* **Course Name**
* **Course Heading**
* **List of Enrolled Students** (name + email)

---

## API Integration

The application communicates with external APIs using **Feign Clients**.
Parameters such as `page`, `per`, `email`, and `search_after` can be used when retrieving users.

---

## Tests

Unit tests are implemented with **JUnit 5** and **Mockito**.
Key services tested:

* `CourseService` → validates course retrieval
* `EnrollmentService` → validates enrollment retrieval
* `UserService` → validates user retrieval
* `ReportService` → validates report generation and caching behavior

---

## Configuration

The application requires an API URL and an API key to access external services. These should be configured using an .env file to avoid hardcoding sensitive information.

1. Create an .env file

	In the root of the project, create a file named .env:

	```
	touch .env
	```

2. Add the required environment variables

	Open .env in your text editor and add the following:

	```
	API_URL=https://api.example.com
	API_KEY=your-secret-api-key
	```

	Replace https://api.example.com with the URL of the external API.

	Replace your-secret-api-key with your actual API key.

---

## Running Locally

1. Clone the repository

	```
	git clone https://github.com/10340314/takehome-teachable.git
	cd takehome-teachable
	```

2. Run Docker Compose
	
    ```
	docker compose up
    ```
	
---

## Exploring the API

Since this project does not include a frontend, you can view and test the API endpoints using Swagger UI.

Steps:

Run the application (locally or via Docker Compose):

	```
	docker compose up
	```

Open Swagger UI in your browser:

http://localhost:8080/swagger-ui.html


You will see a list of all available endpoints, including:

/api/reports/courses/{id} → Get course details

/api/reports/users/{id} → Get user details

/api/reports/users → Get all users with pagination

/api/reports/courses/{courseId}/enrollments → Get enrollments for a course

/api/reports/course/{courseId}/enrolled-students → Get the course report with enrolled students

You can try out requests directly from Swagger UI by filling in the parameters and clicking “Execute”. The response will be displayed in the browser.

---

## Cache Behavior & Frontend Usage

This API was developed with the intention of being consumed by a frontend.

To optimize performance, student data is cached on the backend.

The cache is populated when the frontend calls the endpoint /api/reports/users to fetch all users.

Subsequent requests for student details (e.g., enrolled students in a course) will retrieve information from the cache when available, reducing repeated external API calls.

---

## Next Steps

* Improve cache processes → caching courses as well as students for faster report building
* Optimize cache eviction strategy and consider time-to-live or size-based eviction
* Add error handling and fallback strategies for external API failures (e.g., using Resilience4j or Spring Retry)

---
