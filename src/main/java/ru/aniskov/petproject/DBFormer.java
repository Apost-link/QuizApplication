package ru.aniskov.petproject;

import ru.aniskov.petproject.pojo.Quiz;

import java.util.Optional;

public interface DBFormer {
    Iterable<Quiz> findQuizAll();

    Optional<Quiz> findQuizById(long id);

    Iterable<Quiz> findQuizByCategory(long category);

    Quiz saveQuiz(Quiz quiz);
}
