package com.example.examspringjpa.controller;


import com.example.examspringjpa.dao.CustomerRepository;
import com.example.examspringjpa.dao.vo_customer_dept;
import com.example.examspringjpa.entity.Customer;
import com.oracle.svm.core.annotate.Delete;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    private CustomerRepository repository;

    public CustomerController(CustomerRepository repository) {
//        super();
        this.repository = repository;
    }

    //--전체조회 http://localhost:8080/customer/list
    @GetMapping("/customer/list")
    public List<Customer> getCustomerList()
    {
        return (List<Customer>)repository.findAll();
    }

    //--1개 조회  http://localhost:8080/customer?id=3
    @GetMapping("/customer")
    public Customer getCustomer(int id)
    {
        //--orElse 데이터 없을때 null 전송
//        return repository.findById(id).orElse(null);

        //--null 500 error
        return repository.findById(id).orElseThrow();
    }

    //--이름검색, 새로운메쏘드 추가필요 (CustomerRepository)http://localhost:8080/customer/name?name=홍길동3
    @GetMapping("/customer/name")
    public List<Customer> getCustomer2(String name)
    {
        return repository.findByName(name);
    }




    //--이름 like 검색  http://localhost:8080/customer/search?name=홍길동1
    @GetMapping("customer/search")
    public List<Customer> searchCustomer(String name)
    {
        return repository.findByNameLike("%" + name + "%");
    }

    //--이름 like 검색후 정렬  http://localhost:8080/customer/search2?name=홍길동1
    @GetMapping("customer/search2")
    public List<Customer> searchCustomer2(String name)
    {
        //--자동정렬
        return repository.findByNameLikeOrderByAddressDesc("%" + name + "%");
    }





    //--이름 + 주소 검색 (http://localhost:8080/customer/search3?name=홍길동1&address=서울1)
    @GetMapping("customer/search3")
    public List<Customer> searchCustomer3(String name, String address)
    {
        return repository.findByNameOrAddress(name, address);
    }







    //--###################################################  SQL 쿼리사용
    //--http://localhost:8080/customer/vip?value1=홍길동1&value2=1
    @GetMapping("customer/vip")
    public List<Customer> vipCustomer(String value1, String value2)
    {
        return repository.findByVip(value1, value2);
    }

    //--기존방식 직접 쿼리사용
    //--http://localhost:8080/customer/vip2?value1=홍길동1&value2=1
    @GetMapping("customer/vip2")
    public List<Customer> vipCustomer2(String value1, String value2)
    {
        return repository.findByVip2(value1, value2);
    }






    //--################################################### 저장, 수정

    //--@RequestBody 없으면 쿼리스트링전송가능.
    //--추가 http://localhost:8080/customer?name=홍길동1&address=서울시1&primaryGrade=1
    @PostMapping("/customer")
    public Customer postCustomer(Customer customer)
    {
        return repository.save(customer);
    }

    //--수정 http://localhost:8080/customer?name=홍길동2&address=서울시2&primaryGrade=2
    @PutMapping("/customer")
    public Customer putCustomer(Customer customer)
    {
        return repository.save(customer);
    }

    //--삭제 http://localhost:8080/customer?id=3
    @DeleteMapping("/customer")
    public void deleteCustomer(int id)
    {
        repository.deleteById(id);
    }




    //--jpql 쿼리
    @GetMapping("/dept/join1")
    public List<Object[]> searchJPQL2(int id)
    {
        return (List<Object[]>)repository.searchJPQL2(id);
    }


    //--native 쿼리
    @GetMapping("/dept/join2")
    public List<vo_customer_dept> searchNativeSQL(int id)
    {
        return (List<vo_customer_dept>)repository.searchNativeSQL(id);
    }
}
