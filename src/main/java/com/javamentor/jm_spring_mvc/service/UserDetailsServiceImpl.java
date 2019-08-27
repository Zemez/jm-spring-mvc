package com.javamentor.jm_spring_mvc.service;

import com.javamentor.jm_spring_mvc.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.logging.Logger;

@Service("userDetailsService")
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = Logger.getLogger(UserDetailsServiceImpl.class.getName());

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.javamentor.jm_spring_mvc.model.User user;
        try {
            user = userDao.find(username);
        } catch (NoResultException e) {
            throw new UsernameNotFoundException("User not found", e);
        }

        if (user == null) throw new UsernameNotFoundException("User not found");

        User.UserBuilder builder = User.withUsername(username);
        builder.disabled(!user.isEnabled());
        builder.password(user.getPassword());
        builder.authorities(user.getRoles().stream().map(r -> "ROLE_" + r.getName()).toArray(String[]::new));
        return builder.build();
    }

}
