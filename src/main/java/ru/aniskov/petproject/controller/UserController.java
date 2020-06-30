package ru.aniskov.petproject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.aniskov.petproject.db.service.UserService;
import ru.aniskov.petproject.pojo.model.QuizUser;
import ru.aniskov.petproject.pojo.model.Role;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "${v1API}/user")
public class UserController {

    private static Logger _log = LoggerFactory.getLogger(UserController.class);

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Optional<QuizUser> getUser(@PathVariable long id){
        Optional<QuizUser> user = service.findUserById(id);
        if(user.isPresent()){
            return user;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id = " + id + " not found");
        }
    }

    @GetMapping("/all")
    public Iterable<QuizUser> getAllUser(){
        List<QuizUser> users = (List<QuizUser>) service.findUserAll();
        if(!users.isEmpty()){
            return users;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User list is empty");
        }
    }

    @PostMapping("/new")
    public QuizUser postUser(@RequestParam(value="name") String name , @RequestParam(value="role") String role, @RequestParam(value="password") String password){
        Optional<QuizUser> existUserWithName = service.findUserByName(name);
        if(!existUserWithName.isPresent()){
            if(Role.isRolePresent(role)){
                return service.saveUser(new QuizUser(name, password, role));
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Role " + role + " does not exist");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with name " + name + " already exist");
        }
    }

}
