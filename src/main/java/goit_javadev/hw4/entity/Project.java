package goit_javadev.hw4.entity;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class Project {
    private long id;
    private long companyId;
    private long customerId;
    private String name;
    private Double budget;
    private Boolean status;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private Double cost;

    private List<Developer> developers;
    private int developersCount;
}
