package ormlearn;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.List;

@SpringBootApplication
public class OrmLearnApplication {
    public static void main(String[] args) {
        System.out.println("=== Starting OrmLearn Application ===");
        SpringApplication.run(OrmLearnApplication.class, args);
        System.out.println("=====================================");
    }

    @Bean
    public CommandLineRunner demo(CountryService countryService) {
        return args -> {
            System.out.println("\n--- 1. Fetching All Countries ---");
            countryService.getAllCountries().forEach(System.out::println);

            System.out.println("\n--- 2. Find Country by Code 'IN' ---");
            Country in = countryService.findCountryByCode("IN");
            System.out.println("Result: " + in);

            System.out.println("\n--- 3. Search Country Containing 'ia' ---");
            List<Country> iaCountries = countryService.searchCountryByNameContaining("ia");
            iaCountries.forEach(System.out::println);

            System.out.println("\n--- 4. Search Country Starting With 'U' ---");
            List<Country> uCountries = countryService.searchCountryByNameStartingWith("U");
            uCountries.forEach(System.out::println);

            System.out.println("\n--- 5. Adding New Country 'ZZ' ---");
            Country newCountry = new Country();
            newCountry.setCode("ZZ");
            newCountry.setName("Testland");
            countryService.addCountry(newCountry);
            System.out.println("Added: " + countryService.findCountryByCode("ZZ"));

            System.out.println("\n--- 6. Updating Country 'ZZ' Name ---");
            countryService.updateCountry("ZZ", "New Testland");
            System.out.println("Updated: " + countryService.findCountryByCode("ZZ"));

            System.out.println("\n--- 7. Deleting Country 'ZZ' ---");
            countryService.deleteCountry("ZZ");
            System.out.println("Deleted. Checking 'ZZ': " + countryService.findCountryByCode("ZZ"));
        };
    }
}
