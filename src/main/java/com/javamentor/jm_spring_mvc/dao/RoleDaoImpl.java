package com.javamentor.jm_spring_mvc.dao;

import com.javamentor.jm_spring_mvc.model.Role;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("roleDao")
public class RoleDaoImpl extends AbstractDao implements RoleDao {

    @Override
    public void save(Role role) {
        getSession().persist(role);
    }

    @Override
    public Role find(Long id) {
        return getSession().get(Role.class, id);
    }

    @Override
    public Role find(String name) {
        Query<Role> query = getSession().createQuery("from Role where name = :name", Role.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    public List<Role> find() {
        return getSession().createQuery("from Role", Role.class).list();
    }

    @Override
    public void update(Role role) {
        getSession().update(role);
    }

    @Override
    public void delete(Long id) {
        Query query = getSession().createQuery("delete from Role where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

}
