package ru.aniskov.petproject.db.service;

import ru.aniskov.petproject.db.model.Quiz;

public interface QuizService {
    Iterable<Quiz> findQuizAll();

    Quiz findQuizById(long id);

    Iterable<Quiz> findQuizByCategory(long category);

    Quiz saveQuiz(Quiz quiz);
}
