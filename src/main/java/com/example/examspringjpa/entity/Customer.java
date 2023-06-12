package com.example.examspringjpa.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

//--Data 롬복 사용시 getter, setter 자동생성됨
//--@Id 는 pk 역할
//--@GeneratedValue 추가하면 아이디 자동생성
//--@GeneratedValue(strategy= GenerationType.IDENTITY)  해줘야 쿼리에서 아이디 자동생성됨  [insert into Customer(name, address) values ('홍길동1', '서울시 강남구 대치동')];
//--@Column(length = 1024) 지정안하면 String 은 기본 : 255자리
@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(length = 1024)
    private String address;
    private String primaryGrade;        //-- 카멜케이스는 db 에 primary_Grade 대문자분리됨
    private String dept_code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrimaryGrade() {
        return primaryGrade;
    }

    public void setPrimaryGrade(String primaryGrade) {
        this.primaryGrade = primaryGrade;
    }

    public String getDept_code() {
        return dept_code;
    }

    public void setDept_code(String dept_code) {
        this.dept_code = dept_code;
    }

    @ManyToOne
    @JoinColumn(name="dept_code")
    Dept dept;
}
