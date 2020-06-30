package ru.aniskov.petproject.db.repository;

import org.springframework.data.repository.CrudRepository;
import ru.aniskov.petproject.pojo.model.PassedSetLog;

public interface PassedSetLogRepository extends CrudRepository<PassedSetLog, Long> {
    public Iterable<PassedSetLog> findAllByUserId(long userId);
}
