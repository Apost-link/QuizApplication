package ru.aniskov.petproject.db.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Colloquium {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private long setId;

    @Column(nullable = false)
    private long quizId;

    public Colloquium() {
    }

    public Colloquium(long setId, long quizId) {
        this.setId = setId;
        this.quizId = quizId;
    }
}
