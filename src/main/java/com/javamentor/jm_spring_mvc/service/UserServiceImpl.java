package com.javamentor.jm_spring_mvc.service;

import com.javamentor.jm_spring_mvc.dao.RoleDao;
import com.javamentor.jm_spring_mvc.dao.UserDao;
import com.javamentor.jm_spring_mvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void save(User user) {
        if (user.getRoles().size() < 1) {
            user.setRoles(Collections.singletonList(roleDao.find("USER")));
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }

    @Override
    public User find(Long id) {
        return userDao.find(id);
    }

    @Override
    public User find(String username) {
        return userDao.find(username);
    }

    @Override
    public List<User> find() {
        return userDao.find();
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

}
