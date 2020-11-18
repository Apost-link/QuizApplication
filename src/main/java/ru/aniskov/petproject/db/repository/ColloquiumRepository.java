package ru.aniskov.petproject.db.repository;

import org.springframework.data.repository.CrudRepository;
import ru.aniskov.petproject.db.model.Colloquium;

public interface ColloquiumRepository extends CrudRepository<Colloquium, Long> {
    public Iterable<Colloquium> findBySetId(Long setId);
}
