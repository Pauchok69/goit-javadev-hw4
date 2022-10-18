package goit_javadev.hw4.entity;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Project {
    private long id;
    private long companyId;
    private long customerId;
    private String name;
    private Float budget;
    private Boolean status;
    private Date dateStart;
    private Date dateEnd;
    private Float cost;

    private List<Developer> developers;
}
