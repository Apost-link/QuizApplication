package ru.aniskov.petproject.db.service;

import ru.aniskov.petproject.pojo.model.PassedSetLog;

public interface PassedSetLogService {
    Iterable<PassedSetLog> findPassedSetsByUserId(long userId);

    PassedSetLog savePassedSetLog(PassedSetLog passedSetLog);
}
