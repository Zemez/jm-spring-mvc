package com.javamentor.jm_spring_mvc.model;

import java.io.Serializable;

public enum UserRole implements Serializable {
    ADMIN("ADMIN"),
    USER("USER");

    String name;

    UserRole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
