package ru.aniskov.petproject.repository;

import org.springframework.data.repository.CrudRepository;
import ru.aniskov.petproject.pojo.Quiz;

public interface QuizRepository extends CrudRepository<Quiz, Long> {
    public Iterable<Quiz> findQuizByCategory(Long category);
}
