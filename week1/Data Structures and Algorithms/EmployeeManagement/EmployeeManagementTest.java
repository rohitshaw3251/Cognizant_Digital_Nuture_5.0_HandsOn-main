public class EmployeeManagementTest {
    private Employee[] employees = new Employee[10];
    private int size = 0;

    public void addEmployee(Employee e) {
        if (size < employees.length) {
            employees[size++] = e;
        } else {
            System.out.println("Capacity reached.");
        }
    }

    public Employee searchEmployee(String id) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId().equals(id)) {
                return employees[i];
            }
        }
        return null;
    }

    public void traverse() {
        for (int i = 0; i < size; i++) {
            System.out.println(employees[i]);
        }
    }

    public void deleteEmployee(String id) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId().equals(id)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            for (int i = index; i < size - 1; i++) {
                employees[i] = employees[i+1];
            }
            employees[--size] = null;
        }
    }

    public static void main(String[] args) {
        EmployeeManagementTest m = new EmployeeManagementTest();
        m.addEmployee(new Employee("E01", "Alice", "Developer", 80000));
        m.addEmployee(new Employee("E02", "Bob", "Manager", 95000));

        System.out.println("All Employees:");
        m.traverse();

        System.out.println("\nSearch E01:");
        System.out.println(m.searchEmployee("E01"));

        System.out.println("\nDeleting E01:");
        m.deleteEmployee("E01");
        m.traverse();
    }
}
