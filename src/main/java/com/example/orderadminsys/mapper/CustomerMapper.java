package com.example.orderadminsys.mapper;

import com.example.orderadminsys.entity.Customer;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CustomerMapper {

    @Select("SELECT * FROM customers WHERE id = #{id}")
    Customer selectById(Long id);

    @Select("SELECT * FROM customers")
    List<Customer> selectAll();

    @Insert("INSERT INTO customers (name, email, phone, address) VALUES (#{name}, #{email}, #{phone}, #{address})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Customer customer);

    @Update("UPDATE customers SET name = #{name}, email = #{email}, phone = #{phone}, address = #{address} WHERE id = #{id}")
    int update(Customer customer);

    @Delete("DELETE FROM customers WHERE id = #{id}")
    int deleteById(Long id);

    @Delete("<script>DELETE FROM customers WHERE id IN <foreach item='id' collection='ids' open='(' separator=',' close=')'>#{id}</foreach></script>")
    int deleteBatch(List<Long> ids);

    // For search
    @Select("SELECT * FROM customers WHERE name LIKE CONCAT('%', #{keyword}, '%') OR email LIKE CONCAT('%', #{keyword}, '%') OR phone LIKE CONCAT('%', #{keyword}, '%')")
    List<Customer> selectByKeyword(String keyword);
}
