package ru.aniskov.petproject.data;

import ru.aniskov.petproject.pojo.User;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class UserData {
    private List<User> userList;
    private final AtomicLong counter = new AtomicLong();

    public UserData(){
        this.userList = initUserList();
    }

    public User getUser(long id){
        for(User user: userList){
            if(user.getId() == id){
                return user;
            }
        }

        return null;
    }

    public List<User> getUserList(){
        return userList;
    }

    public User putUser(String name){
        User newUser = new User(counter.getAndIncrement(), name);
        userList.add(newUser);
        return newUser;
    }

    private List<User> initUserList(){
        List<User> userlist = new LinkedList<>();
        userlist.add(new User(counter.getAndIncrement(), "Oleg"));
        userlist.add(new User(counter.getAndIncrement(), "Dadada"));
        return userlist;
    }
}
