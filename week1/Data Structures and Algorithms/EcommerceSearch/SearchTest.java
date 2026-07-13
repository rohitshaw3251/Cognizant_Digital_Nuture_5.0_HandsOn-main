import java.util.Arrays;
import java.util.Comparator;

public class SearchTest {
    public static void main(String[] args) {
        Product[] products = {
            new Product("P105", "Wireless Mouse", "Electronics"),
            new Product("P101", "Mechanical Keyboard", "Electronics"),
            new Product("P103", "Leather Wallet", "Accessories"),
            new Product("P104", "Running Shoes", "Footwear"),
            new Product("P102", "Smart Watch", "Electronics")
        };

        System.out.println("=== Testing Linear Search ===");
        String targetId = "P103";
        long startLinear = System.nanoTime();
        Product linearResult = SearchAlgorithms.linearSearch(products, targetId);
        long endLinear = System.nanoTime();
        
        System.out.println("Search Target: " + targetId);
        System.out.println("Result: " + (linearResult != null ? linearResult : "Not Found"));
        System.out.println("Time taken by Linear Search: " + (endLinear - startLinear) + " ns\n");

        System.out.println("=== Sorting Products by ID for Binary Search ===");
        Arrays.sort(products, Comparator.comparing(Product::getProductId));
        for (Product p : products) {
            System.out.println(p);
        }
        System.out.println();

        System.out.println("=== Testing Binary Search ===");
        long startBinary = System.nanoTime();
        Product binaryResult = SearchAlgorithms.binarySearch(products, targetId);
        long endBinary = System.nanoTime();

        System.out.println("Search Target: " + targetId);
        System.out.println("Result: " + (binaryResult != null ? binaryResult : "Not Found"));
        System.out.println("Time taken by Binary Search: " + (endBinary - startBinary) + " ns");
    }
}
