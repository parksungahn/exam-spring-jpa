package com.example.examspringjpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@Entity
public class Dept {
    @Id
    private String dept_code;
    private String dept_name;

    public String getDept_code() {
        return dept_code;
    }

    public void setDept_code(String dept_code) {
        this.dept_code = dept_code;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    @OneToMany(mappedBy = "dept")
    private List<Customer> customer = new ArrayList<>();

}
