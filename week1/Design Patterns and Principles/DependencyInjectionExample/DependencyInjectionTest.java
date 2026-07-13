public class DependencyInjectionTest {
    public static void main(String[] args) {
        CustomerRepository repo = new CustomerRepositoryImpl();
        CustomerService service = new CustomerService(repo);

        service.showCustomerDetails(1);
        service.showCustomerDetails(2);
    }
}
