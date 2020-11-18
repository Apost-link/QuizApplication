package ru.aniskov.petproject.db.service;

import ru.aniskov.petproject.pojo.SetInfo;
import ru.aniskov.petproject.db.model.PassedSetLog;

public interface SetService {
    SetInfo findSetInfo(long setId);

    PassedSetLog savePassedSetLog(PassedSetLog log);
}
