package com.example.orderadminsys.mapper;

import com.example.orderadminsys.entity.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Select("SELECT * FROM orders WHERE id = #{id}")
    Order selectById(Long id);

    @Select("SELECT * FROM orders")
    List<Order> selectAll();

    @Insert("INSERT INTO orders (customer_id, product_id, quantity, total_price, status) VALUES (#{customerId}, #{productId}, #{quantity}, #{totalPrice}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Order order);

    @Update("UPDATE orders SET customer_id = #{customerId}, product_id = #{productId}, quantity = #{quantity}, total_price = #{totalPrice}, status = #{status} WHERE id = #{id}")
    int update(Order order);

    @Delete("DELETE FROM orders WHERE id = #{id}")
    int deleteById(Long id);

    @Delete("<script>DELETE FROM orders WHERE id IN <foreach item='id' collection='ids' open='(' separator=',' close=')'>#{id}</foreach></script>")
    int deleteBatch(List<Long> ids);

    // For search - simple, can be enhanced
    @Select("SELECT * FROM orders WHERE customer_id = #{customerId} OR product_id = #{productId}")
    List<Order> selectByCustomerOrProduct(Long customerId, Long productId);

    // For display with customer and product names - using XML
    List<Order> selectAllWithDetails();
}
