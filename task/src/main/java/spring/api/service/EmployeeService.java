package spring.api.service;


import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import spring.api.exception.EmployeesNotFoundException;
import spring.api.model.Employee;
import spring.api.repository.EmployeeRepository;

@Service
public class EmployeeService {
    EmployeeRepository employeeRepositories;

    public EmployeeService(EmployeeRepository employeeRepositories) {
        this.employeeRepositories = employeeRepositories;
    }

    public Page<Employee> getAllEmployees(int page) {
        try {
            Pageable pageable = PageRequest.of(page, 5, Sort.by("id").ascending());
            Page<Employee> employees = employeeRepositories.findAll(pageable);

            if (employees.isEmpty()) {
                throw new EmployeesNotFoundException("No employees found.");
            }
            return employees;
        } catch (DataAccessException dae) {
            System.err.println("Database error: " + dae.getMessage());
            throw new RuntimeException("Failed to retrieve employees due to DB error", dae);
        }
    }


}
