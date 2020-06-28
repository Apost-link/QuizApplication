package ru.aniskov.petproject.pojo.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class QuizUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Date registrerDate;

    public QuizUser() {
    }

    public QuizUser(String name){
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getRegistrerDate() {
        return registrerDate;
    }

    public void setRegistrerDate(Date registrerDate) {
        this.registrerDate = registrerDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
