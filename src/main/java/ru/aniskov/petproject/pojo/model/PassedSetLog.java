package ru.aniskov.petproject.pojo.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class PassedSetLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private long userId;

    @Column(nullable = false)
    private long setId;

    @Column(nullable = false)
    private int correctPercent;

    @Column(nullable = false)
    private Date date;

    public PassedSetLog() {
    }

    public PassedSetLog(long userId, long setId, int correctPercent) {
        this.userId = userId;
        this.setId = setId;
        this.correctPercent = correctPercent;
    }

    public PassedSetLog(long userId, long setId, int correctPercent, Date date) {
        this.userId = userId;
        this.setId = setId;
        this.correctPercent = correctPercent;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getSetId() {
        return setId;
    }

    public void setSetId(long setId) {
        this.setId = setId;
    }

    public int getCorrectPercent() {
        return correctPercent;
    }

    public void setCorrectPercent(int correctPercent) {
        this.correctPercent = correctPercent;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
