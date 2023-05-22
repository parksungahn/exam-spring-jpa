package com.example.examspringjpa.controller;

import com.example.examspringjpa.dao.DeptRepository;
import com.example.examspringjpa.dao.vo_customer_dept;
import com.example.examspringjpa.entity.Dept;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeptController {

    DeptRepository repository;

    public DeptController(DeptRepository repository)
    {
        super();
        this.repository = repository;
    }

    @GetMapping("/dept")
    public Dept getDept(String dept_code)
    {
        return repository.findById(dept_code).orElseThrow();
    }

    @PostMapping("/dept")
    public Dept postDept(Dept dept)
    {
        return repository.save(dept);
    }


}
