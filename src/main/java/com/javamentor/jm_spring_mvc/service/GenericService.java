package com.javamentor.jm_spring_mvc.service;

import java.util.List;

public interface GenericService<T> {

    void save(T model);

    T find(Long id);

    List<T> find();

    void update(T model);

    void delete(Long id);

}
