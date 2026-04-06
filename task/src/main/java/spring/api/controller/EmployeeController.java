package spring.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.api.model.Employee;
import spring.api.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Example URL: /employees/page/0
    @GetMapping("/page/{page}")
    public Page<Employee> getEmployees(
            @PathVariable int page) {
        return employeeService.getAllEmployees(page);
    }
}
