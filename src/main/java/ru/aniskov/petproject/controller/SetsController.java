package ru.aniskov.petproject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.aniskov.petproject.db.DBFormer;
import ru.aniskov.petproject.pojo.model.PassedSetLog;

@RestController
@RequestMapping(path = "${v1API}/sets")
public class SetsController {

    private static Logger _log = LoggerFactory.getLogger(SetsController.class);

    private DBFormer db;

    @Autowired
    public SetsController(DBFormer dbFormer) {
        this.db = dbFormer;
    }

    @PostMapping("/passed/new")
    public PassedSetLog postNewPassedSet(@RequestParam(value = "userId") long userId, @RequestParam(value = "setId") long setId, @RequestParam(value = "correct_percent") int percent){
        PassedSetLog passedSetLog = new PassedSetLog(userId, setId, percent);
        return db.savePassedSetLog(passedSetLog);
    }

}
