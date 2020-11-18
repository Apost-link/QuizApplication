package ru.aniskov.petproject.db.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String title;

    public Category() {
    }

    public Category(String title) {
        this.title = title;
    }
}
