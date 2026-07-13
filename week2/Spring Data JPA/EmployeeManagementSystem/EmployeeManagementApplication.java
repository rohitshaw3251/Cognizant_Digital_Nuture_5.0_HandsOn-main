package employee;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
public class EmployeeManagementApplication {
    public static void main(String[] args) {
        System.out.println("=== Starting Employee Management Application ===");
        SpringApplication.run(EmployeeManagementApplication.class, args);
        System.out.println("=================================================");
    }

    @Bean
    public CommandLineRunner initData(DepartmentRepository deptRepo, EmployeeRepository empRepo) {
        return args -> {
            System.out.println("\n--- Seeding Initial Data ---");
            Department hr = deptRepo.save(new Department("HR"));
            Department it = deptRepo.save(new Department("IT"));

            empRepo.save(new Employee("Alice", "alice@example.com", 60000.0, hr));
            empRepo.save(new Employee("Bob", "bob@example.com", 80000.0, it));
            empRepo.save(new Employee("Charlie", "charlie@example.com", 90000.0, it));

            System.out.println("Departments seeded: " + deptRepo.count());
            System.out.println("Employees seeded: " + empRepo.count());

            System.out.println("\n--- Testing JPQL Query Method ---");
            Employee e = empRepo.findByEmailJPQL("alice@example.com");
            System.out.println("Found JPQL Employee: " + e.getName() + " in " + e.getDepartment().getName());

            System.out.println("\n--- Testing Native SQL Query ---");
            List<Employee> highPaid = empRepo.findBySalaryNative(70000.0);
            highPaid.forEach(emp -> System.out.println("High Paid Native: " + emp.getName() + " - $" + emp.getSalary()));

            System.out.println("\n--- Testing Dynamic Projection Query ---");
            List<EmployeeProjection> projections = empRepo.findByDepartmentName("IT", EmployeeProjection.class);
            projections.forEach(p -> System.out.println("Projection: Name=" + p.getName() + ", Salary=$" + p.getSalary()));
            System.out.println("-----------------------------\n");
        };
    }
}
