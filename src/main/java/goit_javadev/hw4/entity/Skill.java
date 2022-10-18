package goit_javadev.hw4.entity;

import java.util.List;

import lombok.Data;

@Data
public class Skill {
    private long id;
    private String scope;
    private int level;
    private Boolean status;

    private List<Developer> developers;
}
