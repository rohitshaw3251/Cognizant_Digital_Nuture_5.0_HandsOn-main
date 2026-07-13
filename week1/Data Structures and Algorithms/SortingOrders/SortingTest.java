public class SortingTest {
    public static void bubbleSort(Order[] orders) {
        int n = orders.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (orders[j].getTotalPrice() > orders[j+1].getTotalPrice()) {
                    Order temp = orders[j];
                    orders[j] = orders[j+1];
                    orders[j+1] = temp;
                }
            }
        }
    }

    public static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pi = partition(orders, low, high);
            quickSort(orders, low, pi - 1);
            quickSort(orders, pi + 1, high);
        }
    }

    private static int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].getTotalPrice();
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (orders[j].getTotalPrice() < pivot) {
                i++;
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }
        Order temp = orders[i+1];
        orders[i+1] = orders[high];
        orders[high] = temp;
        return i + 1;
    }

    public static void main(String[] args) {
        Order[] arr1 = {
            new Order("O01", "Alice", 250.0),
            new Order("O02", "Bob", 120.5),
            new Order("O03", "Charlie", 350.75),
            new Order("O04", "David", 80.0)
        };

        Order[] arr2 = arr1.clone();

        System.out.println("Bubble Sorting:");
        bubbleSort(arr1);
        for (Order o : arr1) {
            System.out.println(o);
        }

        System.out.println("\nQuick Sorting:");
        quickSort(arr2, 0, arr2.length - 1);
        for (Order o : arr2) {
            System.out.println(o);
        }
    }
}
