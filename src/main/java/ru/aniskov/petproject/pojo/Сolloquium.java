package ru.aniskov.petproject.pojo;

import javax.persistence.*;

@Entity
public class Сolloquium {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private long setId;

    @Column(nullable = false)
    private long quizId;

    public Сolloquium() {
    }

    public Сolloquium(long setId, long quizId) {
        this.setId = setId;
        this.quizId = quizId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSetId() {
        return setId;
    }

    public void setSetId(long setId) {
        this.setId = setId;
    }

    public long getQuizId() {
        return quizId;
    }

    public void setQuizId(long quizId) {
        this.quizId = quizId;
    }
}
