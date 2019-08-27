package com.javamentor.jm_spring_mvc.service;

import com.javamentor.jm_spring_mvc.model.Role;

public interface RoleService extends GenericService<Role> {

    Role find(String name);

}
