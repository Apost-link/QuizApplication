package ru.aniskov.petproject.db.service;

import ru.aniskov.petproject.pojo.SetInfo;
import ru.aniskov.petproject.pojo.model.PassedSetLog;

import java.util.Optional;

public interface SetService {
    SetInfo findSetInfo(long setId);

    PassedSetLog savePassedSetLog(PassedSetLog log);
}
