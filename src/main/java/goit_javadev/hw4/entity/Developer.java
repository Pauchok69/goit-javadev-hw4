package goit_javadev.hw4.entity;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Developer {
    private long id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private Boolean status;

    private List<Project> projects;
    private List<Skill> skills;
}
