package com.example.orderadminsys.service;

import com.example.orderadminsys.entity.User;
import com.example.orderadminsys.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User login(String username, String password) {
        User user = userMapper.selectByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public boolean register(User user) {
        if (userMapper.selectByUsername(user.getUsername()) != null) {
            return false; // username exists
        }
        userMapper.insert(user);
        return true;
    }

    public List<User> getAllUsers() {
        return userMapper.selectAll();
    }

    public User getUserById(Long id) {
        return userMapper.selectById(id);
    }

    public void updateUser(User user) {
        userMapper.update(user);
    }

    public void deleteUser(Long id) {
        userMapper.deleteById(id);
    }

    public void deleteUsersBatch(List<Long> ids) {
        userMapper.deleteBatch(ids);
    }

    public List<User> searchUsers(String keyword) {
        return userMapper.selectByKeyword(keyword);
    }
}
