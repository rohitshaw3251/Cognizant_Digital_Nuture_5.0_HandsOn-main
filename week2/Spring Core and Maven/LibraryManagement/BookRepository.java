package library;

import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {
    public void getBookDetails() {
        System.out.println("Fetching book details from database...");
    }
}
