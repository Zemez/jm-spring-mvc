package com.javamentor.jm_spring_mvc.service;

import com.javamentor.jm_spring_mvc.model.User;

import java.util.List;

public interface UserService {

    void saveUser(User user);

    User findUser(Long id);

    List<User> findAllUsers();

    void updateUser(User user);

    void deleteUser(Long id);

}
