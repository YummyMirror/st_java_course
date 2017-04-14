package ru.anatoli.addressbook;

public class User {
    private final String userName;
    private final String password;

    //Constructor
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    //Getters
    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
