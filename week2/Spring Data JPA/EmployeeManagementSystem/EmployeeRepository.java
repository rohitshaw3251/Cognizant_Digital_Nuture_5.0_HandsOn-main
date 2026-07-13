package employee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByDepartmentId(Long departmentId);

    Page<Employee> findBySalaryGreaterThan(double salary, Pageable pageable);

    @Query("SELECT e FROM Employee e WHERE e.email = :email")
    Employee findByEmailJPQL(@Param("email") String email);

    @Query(value = "SELECT * FROM employee WHERE salary > :salary", nativeQuery = true)
    List<Employee> findBySalaryNative(@Param("salary") double salary);

    <T> List<T> findByDepartmentName(String deptName, Class<T> type);
}
