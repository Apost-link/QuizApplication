package ru.aniskov.petproject.pojo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class QuizUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String name;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private Date registrerDate;

    public QuizUser() {
    }

    public QuizUser(String name){
        this.name = name;
        this.role = Role.USER.getValue();
    }

    public QuizUser(String name, String password, String role){
        this.name = name;
        this.password = password;
        this.role = Role.getRole(role).getValue();
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getRegistrerDate() {
        return registrerDate;
    }

    public void setRegistrerDate(Date registrerDate) {
        this.registrerDate = registrerDate;
    }
}
