package ru.anatoli.addressbook.hometask.five;

public class UserData {
    private final String userName;
    private final String password;

    //Constructor
    public UserData(String userName, String password) {
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
