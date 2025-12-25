package com.example.orderadminsys.mapper;

import com.example.orderadminsys.entity.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Select("SELECT * FROM products WHERE id = #{id}")
    Product selectById(Long id);

    @Select("SELECT * FROM products")
    List<Product> selectAll();

    @Insert("INSERT INTO products (name, description, price, stock_quantity) VALUES (#{name}, #{description}, #{price}, #{stockQuantity})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Product product);

    @Update("UPDATE products SET name = #{name}, description = #{description}, price = #{price}, stock_quantity = #{stockQuantity} WHERE id = #{id}")
    int update(Product product);

    @Delete("DELETE FROM products WHERE id = #{id}")
    int deleteById(Long id);

    @Delete("<script>DELETE FROM products WHERE id IN <foreach item='id' collection='ids' open='(' separator=',' close=')'>#{id}</foreach></script>")
    int deleteBatch(List<Long> ids);

    // For search
    @Select("SELECT * FROM products WHERE name LIKE CONCAT('%', #{keyword}, '%') OR description LIKE CONCAT('%', #{keyword}, '%')")
    List<Product> selectByKeyword(String keyword);
}
