package goit_javadev.hw4.entity;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class Developer {
    private long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Boolean status;
    private Float salary;

    private List<Project> projects;
    private List<Skill> skills;

    public String getInfo() {
        return String.format(
                "%s %s. Status: %s. Birthday: %s. Salary: %.2f",
                firstName,
                lastName,
                Boolean.TRUE.equals(status) ? "active" : "disabled",
                birthDate.toString(),
                salary
        );
    }
}
