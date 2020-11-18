package ru.aniskov.petproject.db.model;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
public class Quiz {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private long category;

    @Column(nullable = false)
    private String question;

    @Column(nullable = false)
    private String answer;

    public Quiz(long category, String question, String answer) {
        this.category = category;
        this.question = question;
        this.answer = answer;
    }

    public Quiz() {
    }
}
