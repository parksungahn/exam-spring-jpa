package com.example.examspringjpa.dao;

import com.example.examspringjpa.entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


//--CrudRepository 해주면 save, delete 기능하는 메소드만들지않고 사용가능.
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    //--추가적으로 Name 검색시 추가해줌
    List<Customer> findByName(String name);




    List<Customer> findByNameLike(String name);

    //--위의 findByNameLike 검색결과에서  address 로 정렬해서 가져옴
    List<Customer> findByNameLikeOrderByAddressDesc(String name);



    List<Customer> findByNameOrAddress(String name, String address);

    //--JPQL 쿼리 실행, 도메인명으로 검색가능 primaryGrad
    @Query("from Customer where name=?1 and primaryGrade=?2")
    List<Customer> findByVip(String value1, String value2);

    //--native 기존 쿼리방법 사용시, primary_Grade 실제생성된 컬럼
    @Query(value="SELECT * from Customer where name = ?1 and primary_grade = ?2", nativeQuery=true)
    List<Customer> findByVip2(String value1, String value2);
}
