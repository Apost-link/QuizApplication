package ru.aniskov.petproject.db.repository;

import org.springframework.data.repository.CrudRepository;
import ru.aniskov.petproject.db.model.QuizSet;

public interface SetRepository extends CrudRepository<QuizSet, Long> {
}
