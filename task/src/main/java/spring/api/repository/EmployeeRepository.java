package spring.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.api.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,String> {


}
