package ru.aniskov.petproject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.aniskov.petproject.db.DBFormer;
import ru.aniskov.petproject.pojo.QuizUser;

import java.util.Optional;

@RestController
public class UserController {

    private static Logger _log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private DBFormer db;

    public UserController(){
    }

    @GetMapping("/user/{id}")
    public Optional<QuizUser> getUser(@PathVariable long id){
        return db.findUserById(id);
    }

    @GetMapping("/user/all")
    public Iterable<QuizUser> getAllUser(){
        return db.findUserAll();
    }

    @PostMapping("/user/new")
    public QuizUser postUser(@RequestParam(value="name") String name){
        return db.saveUser(new QuizUser(name));
    }

}
