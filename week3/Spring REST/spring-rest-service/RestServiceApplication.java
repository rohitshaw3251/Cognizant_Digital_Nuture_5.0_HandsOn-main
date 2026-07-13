package rest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestServiceApplication {
    public static void main(String[] args) {
        System.out.println("=== Starting Spring REST & Security Application ===");
        SpringApplication.run(RestServiceApplication.class, args);
        System.out.println("====================================================");
    }

    @Bean
    public CommandLineRunner seed(CountryRepository countryRepo, EmployeeRepository employeeRepo) {
        return args -> {
            countryRepo.save(new Country("US", "United States"));
            countryRepo.save(new Country("IN", "India"));
            countryRepo.save(new Country("JP", "Japan"));

            employeeRepo.save(new Employee("Alice", "alice@example.com", 60000.0));
            employeeRepo.save(new Employee("Bob", "bob@example.com", 80000.0));

            System.out.println("Seed countries count: " + countryRepo.count());
            System.out.println("Seed employees count: " + employeeRepo.count());
        };
    }
}
