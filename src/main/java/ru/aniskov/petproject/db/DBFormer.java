package ru.aniskov.petproject.db;

import ru.aniskov.petproject.pojo.Quiz;
import ru.aniskov.petproject.pojo.QuizUser;

import java.util.Optional;

public interface DBFormer {
    Iterable<Quiz> findQuizAll();

    Optional<Quiz> findQuizById(long id);

    Iterable<Quiz> findQuizByCategory(long category);

    Quiz saveQuiz(Quiz quiz);

    Optional<QuizUser> findUserById(long id);

    Iterable<QuizUser> findUserAll();

    QuizUser saveUser(QuizUser quizUser);
}
