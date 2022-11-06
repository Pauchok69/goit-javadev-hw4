package goit_javadev.hw4.entity;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Company {
    private long id;
    private String name;
    private Boolean status;
    private LocalDate dateStart;
}
