package ru.aniskov.petproject.db.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.aniskov.petproject.db.repository.PassedSetLogRepository;
import ru.aniskov.petproject.db.service.PassedSetLogService;
import ru.aniskov.petproject.pojo.model.PassedSetLog;

@Component
public class PassedSetLogServiceImpl implements PassedSetLogService {

    private PassedSetLogRepository repository;

    @Autowired
    public PassedSetLogServiceImpl(PassedSetLogRepository repository){
        this.repository = repository;
    }

    @Override
    public Iterable<PassedSetLog> findPassedSetsByUserId(long userId) {
        return repository.findAllByUserId(userId);
    }
}
