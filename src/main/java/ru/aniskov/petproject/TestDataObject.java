package ru.aniskov.petproject;

import ru.aniskov.petproject.data.UserData;

public class TestDataObject {
    private UserData userData;

    public TestDataObject(){
        this.userData = new UserData();
    }

    public UserData getUserData(){
        return userData;
    }
}
