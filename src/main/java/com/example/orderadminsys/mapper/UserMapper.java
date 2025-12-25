package com.example.orderadminsys.mapper;

import com.example.orderadminsys.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM users WHERE id = #{id}")
    User selectById(Long id);

    @Select("SELECT * FROM users WHERE username = #{username}")
    User selectByUsername(String username);

    @Select("SELECT * FROM users")
    List<User> selectAll();

    @Insert("INSERT INTO users (username, password, email) VALUES (#{username}, #{password}, #{email})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(User user);

    @Update("UPDATE users SET username = #{username}, password = #{password}, email = #{email} WHERE id = #{id}")
    int update(User user);

    @Delete("DELETE FROM users WHERE id = #{id}")
    int deleteById(Long id);

    @Delete("<script>DELETE FROM users WHERE id IN <foreach item='id' collection='ids' open='(' separator=',' close=')'>#{id}</foreach></script>")
    int deleteBatch(List<Long> ids);

    // For search
    @Select("SELECT * FROM users WHERE username LIKE CONCAT('%', #{keyword}, '%') OR email LIKE CONCAT('%', #{keyword}, '%')")
    List<User> selectByKeyword(String keyword);
}
