package ru.aniskov.petproject.db.repository;

import org.springframework.data.repository.CrudRepository;
import ru.aniskov.petproject.db.model.QuizUser;

import java.util.Optional;

public interface UserRepository extends CrudRepository<QuizUser, Long> {
    Optional<QuizUser> findByName(String username);
}
