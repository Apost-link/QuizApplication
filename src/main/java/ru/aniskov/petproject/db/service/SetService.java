package ru.aniskov.petproject.db.service;

import ru.aniskov.petproject.pojo.SetInfo;

import java.util.Optional;

public interface SetService {
    Optional<SetInfo> findSetInfo(long setId);
}
