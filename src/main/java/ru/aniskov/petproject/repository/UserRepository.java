package ru.aniskov.petproject.repository;

import org.springframework.data.repository.CrudRepository;
import ru.aniskov.petproject.pojo.QuizUser;

public interface UserRepository extends CrudRepository<QuizUser, Long> {
}