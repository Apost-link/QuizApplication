package ru.aniskov.petproject.db;

import ru.aniskov.petproject.exception.IllegalParametersException;
import ru.aniskov.petproject.pojo.model.PassedSetLog;
import ru.aniskov.petproject.pojo.model.Quiz;
import ru.aniskov.petproject.pojo.model.QuizUser;
import ru.aniskov.petproject.pojo.SetInfo;

import java.util.Optional;

public interface DBFormer {
    Iterable<Quiz> findQuizAll();

    Optional<Quiz> findQuizById(long id);

    Iterable<Quiz> findQuizByCategory(long category);

    Quiz saveQuiz(Quiz quiz);

    Optional<QuizUser> findUserById(long id);

    Optional<QuizUser> findUserByName(String name);

    Iterable<QuizUser> findUserAll();

    QuizUser saveUser(QuizUser quizUser);

    SetInfo findSetInfo(long setId) throws IllegalParametersException;

    Iterable<PassedSetLog> findPassedSetsByUserId(long userId);

    PassedSetLog savePassedSetLog(PassedSetLog passedSetLog);
}
