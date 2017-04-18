package ru.anatoli.addressbook.hometask.five.models;

public class GroupData {
    private final String groupName;
    private final String groupHeader;
    private final String groupFooter;

    //Constructor
    public GroupData(String groupName, String groupHeader, String groupFooter) {
        this.groupName = groupName;
        this.groupHeader = groupHeader;
        this.groupFooter = groupFooter;
    }

    //Getters
    public String getGroupName() {
        return groupName;
    }

    public String getGroupHeader() {
        return groupHeader;
    }

    public String getGroupFooter() {
        return groupFooter;
    }
}
