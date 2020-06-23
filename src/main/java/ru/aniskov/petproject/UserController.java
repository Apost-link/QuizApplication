package ru.aniskov.petproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.aniskov.petproject.pojo.User;

import java.util.List;

@RestController
public class UserController {

    private static Logger _log = LoggerFactory.getLogger(UserController.class);

    private TestDataObject data;

    public UserController(){
        ApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
        this.data = context.getBean(TestDataObject.class);
    }


    @GetMapping("/user")
    public User getUser(@RequestParam(value="id", defaultValue = "0") int index){
        return data.getUserData().getUser(index);
    }

    @GetMapping("/user/all")
    public List<User> getAllUser(){
        return data.getUserData().getUserList();
    }

    @PostMapping("/user")
    public User postUser(@RequestParam(value="name") String name){
        return data.getUserData().putUser(name);
    }

}
