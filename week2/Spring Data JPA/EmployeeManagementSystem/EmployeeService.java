package employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Transactional
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Transactional
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<Employee> getEmployeesBySalaryPaged(double minSalary, Pageable pageable) {
        return employeeRepository.findBySalaryGreaterThan(minSalary, pageable);
    }

    @Transactional(readOnly = true)
    public List<EmployeeProjection> getEmployeeProjectionsByDept(String deptName) {
        return employeeRepository.findByDepartmentName(deptName, EmployeeProjection.class);
    }
}
