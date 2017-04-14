package ru.anatoli.addressbook;

public class GroupData {
    private final String name;
    private final String header;
    private final String footer;
    //Constructor
    public GroupData(String name, String header, String footer) {
        this.name = name;
        this.header = header;
        this.footer = footer;
    }

    //Getters
    public String getName() {
        return name;
    }

    public String getHeader() {
        return header;
    }

    public String getFooter() {
        return footer;
    }
}
