package ru.aniskov.petproject.pojo.model;

import javax.persistence.*;

@Entity
public class Set {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String setName;

    public Set() {
    }

    public Set(String setName) {
        this.setName = setName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }
}
