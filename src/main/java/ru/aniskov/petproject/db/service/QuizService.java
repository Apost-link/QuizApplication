package ru.aniskov.petproject.db.service;

import ru.aniskov.petproject.pojo.model.Quiz;

import java.util.Optional;

public interface QuizService {
    Iterable<Quiz> findQuizAll();

    Optional<Quiz> findQuizById(long id);

    Iterable<Quiz> findQuizByCategory(long category);

    Quiz saveQuiz(Quiz quiz);
}
