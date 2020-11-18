package ru.aniskov.petproject.db.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ru.aniskov.petproject.pojo.model.Role;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
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
}
