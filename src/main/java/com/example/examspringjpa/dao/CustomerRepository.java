package com.example.examspringjpa.dao;

import com.example.examspringjpa.entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


//--CrudRepository 해주면 save, delete 기능하는 메소드만들지않고 사용가능.
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    //--추가적으로 Name 검색시 추가해줌
    List<Customer> findByName(String name);




    List<Customer> findByNameLike(String name);

    //--위의 findByNameLike 검색결과에서  address 로 정렬해서 가져옴
    List<Customer> findByNameLikeOrderByAddressDesc(String name);



    List<Customer> findByNameOrAddress(String name, String address);











    //--################ JPQL 쿼리 실행, 도메인명으로 검색가능 primaryGrad
    @Query("from Customer where name=?1 and primaryGrade=?2")
    List<Customer> findByVip(String value1, String value2);




    //--################ JPQL 쿼리 실행, 연관관계 설정안된건 ON 절 써줘야함.
    //--A, B 테이블명쓰고,  object[] 하면 2개 테이블, json 배열로 리턴함.
//    {"id": 1, "name": "홍길동1", "address": null, "primaryGrade": null, "dept_code": "001"…},
//    {"dept_code": "001", "dept_name": "부서1"}
    @Query("select a, b from Customer a left join Dept b ON a.dept_code=b.dept_code where a.id =:id")
    List<Object[]> searchJPQL2(@Param("id") int id);















    //--################## native 기존 쿼리방법 사용시, primary_Grade 실제생성된 컬럼
//    @Query(value="SELECT * from Customer where name = ?1 and primary_grade = ?2", nativeQuery=true)

    String sql = "SELECT * from Customer where name = ?1 and primary_grade = ?2";
    @Query(value=sql, nativeQuery=true)
    List<Customer> findByVip2(String value1, String value2);



    String sql2 = "select a.id, a.name, b.dept_name from Customer a left outer join Dept b on a.dept_code=b.dept_code where a.id=?1";
    @Query(value=sql2, nativeQuery = true)
    List<vo_customer_dept> searchNativeSQL(int id);
}
