import java.util.HashMap;
import java.util.Map;

public class InventoryTest {
    private Map<String, Product> inventory = new HashMap<>();

    public void addProduct(Product p) {
        inventory.put(p.getProductId(), p);
    }

    public void updateProduct(String id, int qty, double price) {
        Product p = inventory.get(id);
        if (p != null) {
            p.setQuantity(qty);
            p.setPrice(price);
        }
    }

    public void deleteProduct(String id) {
        inventory.remove(id);
    }

    public void displayAll() {
        for (Product p : inventory.values()) {
            System.out.println(p);
        }
    }

    public static void main(String[] args) {
        InventoryTest test = new InventoryTest();
        test.addProduct(new Product("P101", "Laptop", 10, 999.99));
        test.addProduct(new Product("P102", "Phone", 50, 499.99));

        System.out.println("Initial Inventory:");
        test.displayAll();

        System.out.println("\nUpdating Laptop:");
        test.updateProduct("P101", 8, 949.99);
        test.displayAll();

        System.out.println("\nDeleting Phone:");
        test.deleteProduct("P102");
        test.displayAll();
    }
}
