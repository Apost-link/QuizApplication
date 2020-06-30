package ru.aniskov.petproject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.aniskov.petproject.db.service.SetService;
import ru.aniskov.petproject.pojo.SetInfo;
import ru.aniskov.petproject.pojo.model.PassedSetLog;

import java.util.Optional;

@RestController
@RequestMapping(path = "${v1API}/sets")
public class SetsController {

    private static Logger _log = LoggerFactory.getLogger(SetsController.class);

    private SetService service;

    @Autowired
    public SetsController(SetService service) {
        this.service = service;
    }

    @GetMapping("/set/{id}")
    public SetInfo getSetInfoById(@PathVariable long id) {
        Optional<SetInfo> result = service.findSetInfo(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Bad parameters");
        }
    }

//    @PostMapping("/passed/new")
//    public PassedSetLog postNewPassedSet(@RequestParam(value = "userId") long userId, @RequestParam(value = "setId") long setId, @RequestParam(value = "correct_percent") int percent){
//        PassedSetLog passedSetLog = new PassedSetLog(userId, setId, percent);
//        return service.savePassedSetLog(passedSetLog);
//    }

}
