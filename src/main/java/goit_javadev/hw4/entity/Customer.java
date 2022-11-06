package goit_javadev.hw4.entity;

import lombok.Data;

@Data
public class Customer {
    private long id;
    private String firstName;
    private String lastName;
    private Boolean status;
}
