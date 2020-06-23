package ru.aniskov.petproject.repository;

import org.springframework.data.repository.CrudRepository;
import ru.aniskov.petproject.pojo.model.Set;

public interface SetRepository extends CrudRepository<Set, Long> {
}
