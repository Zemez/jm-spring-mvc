package com.javamentor.jm_spring_mvc.dao;

import com.javamentor.jm_spring_mvc.model.Role;

public interface RoleDao extends GenericDao<Role> {

    Role find(String name);

}
