package spring.api.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Entity
@Data
public class Employee {

    public Employee(String id, String name, String salary, String grade) {
        this.id = id;
        this.Name = name;
        this.Salary = salary;
        this.Grade = grade;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long empId;

    private String id;

    private String Name;

    private String Salary;

    private String Grade;




}
