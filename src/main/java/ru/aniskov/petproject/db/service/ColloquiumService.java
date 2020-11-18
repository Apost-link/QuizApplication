package ru.aniskov.petproject.db.service;

import ru.aniskov.petproject.db.model.Colloquium;

public interface ColloquiumService {

    public Iterable<Colloquium> findColloquiumBySetId(long setId);

}
