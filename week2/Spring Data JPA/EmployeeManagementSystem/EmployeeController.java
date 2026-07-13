package employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping
    public Employee create(@RequestBody Employee emp) {
        return employeeService.saveEmployee(emp);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @RequestBody Employee emp) {
        Employee existing = employeeService.getEmployeeById(id);
        if (existing != null) {
            existing.setName(emp.getName());
            existing.setEmail(emp.getEmail());
            existing.setSalary(emp.getSalary());
            existing.setDepartment(emp.getDepartment());
            return employeeService.saveEmployee(existing);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/paged")
    public Page<Employee> getPaged(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size,
        @RequestParam(defaultValue = "salary") String sortBy,
        @RequestParam(defaultValue = "0") double minSalary
    ) {
        return employeeService.getEmployeesBySalaryPaged(minSalary, PageRequest.of(page, size, Sort.by(sortBy).descending()));
    }

    @GetMapping("/projections")
    public List<EmployeeProjection> getProjections(@RequestParam String dept) {
        return employeeService.getEmployeeProjectionsByDept(dept);
    }
}
