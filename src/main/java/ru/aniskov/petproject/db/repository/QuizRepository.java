package ru.aniskov.petproject.db.repository;

import org.springframework.data.repository.CrudRepository;
import ru.aniskov.petproject.pojo.model.Quiz;

public interface QuizRepository extends CrudRepository<Quiz, Long> {
    public Iterable<Quiz> findQuizByCategory(Long category);
}
