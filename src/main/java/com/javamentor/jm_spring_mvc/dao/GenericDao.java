package com.javamentor.jm_spring_mvc.dao;

import java.util.List;

public interface GenericDao<T> {

    void save(T entity);

    T find(Long id);

    List<T> find();

    void update(T entity);

    void delete(Long id);

}
