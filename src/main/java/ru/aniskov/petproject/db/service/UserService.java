package ru.aniskov.petproject.db.service;

import ru.aniskov.petproject.pojo.model.QuizUser;

public interface UserService {

    QuizUser findUserById(long id);

    QuizUser findUserByName(String name);

    Iterable<QuizUser> findUserAll();

    QuizUser saveUser(QuizUser quizUser);

}
