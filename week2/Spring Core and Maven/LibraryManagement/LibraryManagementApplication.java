package library;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
public class LibraryManagementApplication {
    public static void main(String[] args) {
        System.out.println("=== Starting Library Management Boot Application ===");
        SpringApplication.run(LibraryManagementApplication.class, args);
        System.out.println("====================================================");
    }

    @Bean
    public CommandLineRunner demo(BookService bookService) {
        return args -> {
            System.out.println("\n--- Testing AOP and Services ---");
            bookService.manageBooks();
            System.out.println("--------------------------------\n");
        };
    }
}
