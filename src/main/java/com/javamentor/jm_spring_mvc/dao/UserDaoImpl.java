package com.javamentor.jm_spring_mvc.dao;

import com.javamentor.jm_spring_mvc.model.User;
import org.hibernate.Hibernate;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.logging.Logger;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class.getName());

    @Autowired
    private RoleDao roleDao;

    @Override
    public void save(User user) {
        getSession().persist(user);
    }

    @Override
    public User find(Long id) {
        User user = getSession().get(User.class, id);
        if (user != null) {
            Hibernate.initialize(user.getRoles());
        }
        return user;
    }

    @Override
    public User find(String username) {
        //noinspection JpaQlInspection
        Query<User> query = getSession().createQuery("from User where username = :username", User.class);
        query.setParameter("username", username);
        User user = query.getSingleResult();
        if (user != null) {
            Hibernate.initialize(user.getRoles());
        }
        return user;
    }

    @Override
    public List<User> find() {
        //noinspection JpaQlInspection
        List<User> users = getSession().createQuery("from User", User.class).list();
        for (User user : users) {
            Hibernate.initialize(user.getRoles());
        }
        return users;
    }

    @Override
    public void update(User user) {
//        Hibernate.initialize(user.getRoles());
//        for (Role role : user.getRoles()) {
//            role.setId(roleDao.find(role.getName()).getId());
//        }
        logger.info(user.toString());
        getSession().update(user);
    }

    @Override
    public void delete(Long id) {
        //noinspection JpaQlInspection
        Query query = getSession().createQuery("delete from User where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

}
