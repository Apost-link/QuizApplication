package ru.aniskov.petproject.db.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.aniskov.petproject.db.repository.ColloquiumRepository;
import ru.aniskov.petproject.db.service.ColloquiumService;
import ru.aniskov.petproject.db.model.Colloquium;

@Component
public class ColloquiumServiceImpl implements ColloquiumService {

    private ColloquiumRepository repository;

    @Autowired
    public ColloquiumServiceImpl(ColloquiumRepository repository){
        this.repository = repository;
    }

    @Override
    public Iterable<Colloquium> findColloquiumBySetId(long setId) {
        return repository.findBySetId(setId);
    }
}
