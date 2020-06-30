package ru.aniskov.petproject.db.service;

import ru.aniskov.petproject.pojo.model.QuizUser;

import java.util.Optional;

public interface UserService {

    Optional<QuizUser> findUserById(long id);

    Optional<QuizUser> findUserByName(String name);

    Iterable<QuizUser> findUserAll();

    QuizUser saveUser(QuizUser quizUser);

}
