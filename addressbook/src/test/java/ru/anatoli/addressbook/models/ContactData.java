package ru.anatoli.addressbook.models;

public class ContactData {
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String nickname;
    private final String title;
    private String group;

    //Constructor
    public ContactData(String firstName, String middleName, String lastName, String nickname, String title, String group) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.title = title;
        this.group = group;
    }

    //Getters
    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public String getTitle() {
        return title;
    }

    public String getGroup() {
        return group;
    }

    //ToString method
    @Override
    public String toString() {
        return "ContactData{" +
                "firstName='" + firstName + '\'' +
                '}';
    }

    //Equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        return firstName != null ? firstName.equals(that.firstName) : that.firstName == null;
    }

    //HashCode method
    @Override
    public int hashCode() {
        return firstName != null ? firstName.hashCode() : 0;
    }
}
