package ru.aniskov.petproject.repository;

import org.springframework.data.repository.CrudRepository;
import ru.aniskov.petproject.pojo.model.Colloquium;

public interface ColloquiumRepository extends CrudRepository<Colloquium, Long> {
    public Iterable<Colloquium> findBySetId(Long setId);
}
