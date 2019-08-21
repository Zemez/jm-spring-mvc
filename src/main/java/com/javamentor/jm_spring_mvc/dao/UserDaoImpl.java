package com.javamentor.jm_spring_mvc.dao;

import com.javamentor.jm_spring_mvc.model.User;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("UserDao")
public class UserDaoImpl extends AbstractDao implements UserDao{

    @Override
    public void saveUser(User user) {
        getSession().persist(user);
    }

    @Override
    public User findUser(Long id){
        return getSession().get(User.class, id);
    }

    @Override
    public List<User> findAllUsers() {
        //noinspection JpaQlInspection
        return getSession().createQuery("from User", User.class).list();
    }

    @Override
    public void updateUser(User user) {
        getSession().update(user);
    }

    @Override
    public void deleteUser(Long id) {
        //noinspection JpaQlInspection
        Query query = getSession().createQuery("delete from User where id = :id", User.class);
        query.setParameter("id", id);
        query.executeUpdate();
    }

}
