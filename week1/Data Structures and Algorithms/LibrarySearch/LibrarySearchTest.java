import java.util.Arrays;
import java.util.Comparator;

public class LibrarySearchTest {
    public static Book linearSearch(Book[] books, String title) {
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(title)) {
                return b;
            }
        }
        return null;
    }

    public static Book binarySearch(Book[] books, String title) {
        int low = 0;
        int high = books.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = books[mid].getTitle().compareToIgnoreCase(title);
            if (cmp == 0) {
                return books[mid];
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Book[] books = {
            new Book("B01", "The Hobbit", "J.R.R. Tolkien"),
            new Book("B02", "1984", "George Orwell"),
            new Book("B03", "To Kill a Mockingbird", "Harper Lee")
        };

        System.out.println("Linear Search for '1984':");
        System.out.println(linearSearch(books, "1984"));

        Arrays.sort(books, Comparator.comparing(Book::getTitle, String.CASE_INSENSITIVE_ORDER));

        System.out.println("\nBinary Search for 'The Hobbit' (sorted):");
        System.out.println(binarySearch(books, "The Hobbit"));
    }
}
