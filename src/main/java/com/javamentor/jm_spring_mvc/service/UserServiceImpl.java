package com.javamentor.jm_spring_mvc.service;

import com.javamentor.jm_spring_mvc.dao.UserDao;
import com.javamentor.jm_spring_mvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public User find(Long id) {
        return userDao.find(id);
    }

    @Override
    public List<User> find() {
        return userDao.find();
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

}
