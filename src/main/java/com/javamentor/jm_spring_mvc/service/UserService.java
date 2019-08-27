package com.javamentor.jm_spring_mvc.service;

import com.javamentor.jm_spring_mvc.model.User;

public interface UserService extends GenericService<User> {

    User find(String username);

}
