package rest;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable Long id) {
        return employeeRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Employee with ID " + id + " not found"));
    }

    @PostMapping
    public Employee create(@Valid @RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @Valid @RequestBody Employee employee) {
        Employee existing = employeeRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Employee with ID " + id + " not found"));
        existing.setName(employee.getName());
        existing.setEmail(employee.getEmail());
        existing.setSalary(employee.getSalary());
        return employeeRepository.save(existing);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Employee existing = employeeRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Employee with ID " + id + " not found"));
        employeeRepository.delete(existing);
    }
}
