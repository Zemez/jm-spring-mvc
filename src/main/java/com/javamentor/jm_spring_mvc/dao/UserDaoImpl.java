package com.javamentor.jm_spring_mvc.dao;

import com.javamentor.jm_spring_mvc.model.User;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("UserDao")
public class UserDaoImpl extends AbstractDao implements UserDao {

    @Override
    public void save(User user) {
        getSession().persist(user);
    }

    @Override
    public User find(Long id) {
        return getSession().get(User.class, id);
    }

    @Override
    public List<User> find() {
        //noinspection JpaQlInspection
        return getSession().createQuery("from User", User.class).list();
    }

    @Override
    public void update(User user) {
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
