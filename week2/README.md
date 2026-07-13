# Week 2: Spring Framework & Spring Data JPA

This directory contains completed hands-on exercises for Week 2, covering Spring Core, AOP, Maven packaging, and Spring Data JPA.

---

## Projects Included

### 1. LibraryManagement (Spring Core, DI, AOP, Boot)
A Spring Boot application configured using both XML and Annotations:
- **XML Context**: `applicationContext.xml` handles dependency injection, namespace resolution, and auto-proxy settings.
- **Component Scanning**: Classes like `BookService` and `BookRepository` are loaded via annotations.
- **Aspect Oriented Programming (AOP)**: `LoggingAspect` intercepts execution of `BookService` methods to measure and log execution times.

### 2. orm-learn (Spring Data JPA Queries & CRUD)
A Spring Boot Data JPA project demonstrating:
- Custom derived queries.
- HQL queries via `@Query`.
- Native SQL queries.
- Safe CRUD insertions, updates, and deletions of database rows.

### 3. EmployeeManagementSystem (Auditing, Relationships, Pagination)
An advanced JPA relational management service featuring:
- **Relational Mapping**: `@OneToMany` and `@ManyToOne` department-employee mappings.
- **Auditing**: Automatic timestamping (`@CreatedDate`, `@LastModifiedDate`).
- **Pagination & Sorting**: Paginated and sorted endpoint queries.
- **Dynamic Projections**: Custom database interfaces fetching specific projection profiles.

---

## Compilation & Run Instructions

To compile and run any project, navigate to its folder and execute:
```bash
mvn clean compile
mvn spring-boot:run
```
