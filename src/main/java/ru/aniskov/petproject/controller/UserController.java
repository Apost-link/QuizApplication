package ru.aniskov.petproject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.aniskov.petproject.db.DBFormer;
import ru.aniskov.petproject.pojo.model.PassedSetLog;
import ru.aniskov.petproject.pojo.model.QuizUser;

import java.util.Optional;

@RestController
@RequestMapping(path = "${v1API}/user")
public class UserController {

    private static Logger _log = LoggerFactory.getLogger(UserController.class);

    private DBFormer db;

    @Autowired
    public UserController(DBFormer db) {
        this.db = db;
    }

    @GetMapping("/{id}")
    public Optional<QuizUser> getUser(@PathVariable long id){
        return db.findUserById(id);
    }

    @GetMapping("/all")
    public Iterable<QuizUser> getAllUser(){
        return db.findUserAll();
    }

    @GetMapping("/{userId}/passed_sets")
    public Iterable<PassedSetLog> getUserPassedSets(@PathVariable long userId){
        return db.findPassedSetsByUserId(userId);
    }

    @PostMapping("/new")
    public QuizUser postUser(@RequestParam(value="name") String name){
        return db.saveUser(new QuizUser(name));
    }

}
