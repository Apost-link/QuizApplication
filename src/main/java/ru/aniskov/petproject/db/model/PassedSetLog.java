package ru.aniskov.petproject.db.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
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
}
