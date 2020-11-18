package ru.aniskov.petproject.db.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class QuizSet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String setName;

    public QuizSet() {
    }

    public QuizSet(String setName) {
        this.setName = setName;
    }
}
