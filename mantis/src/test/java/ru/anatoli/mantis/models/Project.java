package ru.anatoli.mantis.models;

/**
 * Created by anatoli.anukevich on 6/3/2017.
 */
public class Project {
    private int id;
    private String name;

    //Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    //Setters
    public Project withId(int id) {
        this.id = id;
        return this;
    }

    public Project withName(String name) {
        this.name = name;
        return this;
    }
}
