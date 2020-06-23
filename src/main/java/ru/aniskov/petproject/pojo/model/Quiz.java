package ru.aniskov.petproject.pojo.model;

import javax.persistence.*;


@Entity
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

    public long getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getCategory() {
        return category;
    }

    public void setCategory(long category) {
        this.category = category;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
