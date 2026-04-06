package spring.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import spring.api.model.Employee;
import spring.api.repository.EmployeeRepository;

import java.util.Random;

@Component
public class DataSeeder implements CommandLineRunner {

    EmployeeRepository employeeRepositories;
    private final Random random = new Random();


    public DataSeeder(EmployeeRepository employeeRepositories) {

        this.employeeRepositories = employeeRepositories;
    }

    public void run(String... args) throws Exception {
        String[] names = {
                "Ali", "Samir", "Noor", "Ayman", "Hala",
                "Omar", "Lina", "Ziad", "Rami", "Dana",
                "Fadi", "Maya", "Yousef", "Sara", "Bilal",
                "Reem", "Adnan", "Tala", "Hassan", "Nour",
                "Khaled", "Farah", "Majed", "Ruba", "Ola"
        };

        for (int i = 0; i < names.length; i++) {
            String id = String.valueOf(101 + i);
            String name = names[i];

            int gradeValue = 5 + random.nextInt(6);
            String grade = String.valueOf(gradeValue);

            int salaryValue = 200 + gradeValue * 100;
            String salary = String.valueOf(salaryValue);

            employeeRepositories.save(new Employee(id, name, salary, grade));


        }
        System.out.println("Data inserted into database!");
    }
}