package employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public List<Department> getAll() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public Department getById(@PathVariable Long id) {
        return departmentService.getDepartmentById(id);
    }

    @PostMapping
    public Department create(@RequestBody Department dept) {
        return departmentService.saveDepartment(dept);
    }

    @PutMapping("/{id}")
    public Department update(@PathVariable Long id, @RequestBody Department dept) {
        Department existing = departmentService.getDepartmentById(id);
        if (existing != null) {
            existing.setName(dept.getName());
            return departmentService.saveDepartment(existing);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
    }
}
