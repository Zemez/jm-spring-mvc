package com.javamentor.jm_spring_mvc.dao;

import com.javamentor.jm_spring_mvc.model.User;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.logging.Logger;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class.getName());

    @Override
    public void save(User user) {
        getSession().persist(user);
    }

    @Override
    public User find(Long id) {
        return getSession().get(User.class, id);
    }

    @Override
    public User find(String username) {
        //noinspection JpaQlInspection
        Query<User> query = getSession().createQuery("from User where username = :username", User.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }

    @Override
    public List<User> find() {
        //noinspection JpaQlInspection
        return getSession().createQuery("from User", User.class).list();
    }

    @Override
    public void update(User user) {
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
