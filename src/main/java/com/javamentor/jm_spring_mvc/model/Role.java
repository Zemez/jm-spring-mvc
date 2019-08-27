package com.javamentor.jm_spring_mvc.model;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 15, unique = true, nullable = false, updatable = false)
    private String name = UserRole.USER.getName();

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public Role(Long id, String name) {
        this(name);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthority() {
        return name;
    }

    @Override
    public String toString() {
        return "Role: {" +
                " id: " + id +
                ", name: " + name +
                " }";
    }
}
