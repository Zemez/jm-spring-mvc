package com.javamentor.jm_spring_mvc.service;

import com.javamentor.jm_spring_mvc.dao.RoleDao;
import com.javamentor.jm_spring_mvc.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public Role find(Long id) {
        return roleDao.find(id);
    }

    @Override
    public Role find(String name) {
        return roleDao.find(name);
    }

    @Override
    public List<Role> find() {
        return roleDao.find();
    }

    @Override
    public void update(Role role) {
        roleDao.update(role);
    }

    @Override
    public void delete(Long id) {
        roleDao.delete(id);
    }

}
