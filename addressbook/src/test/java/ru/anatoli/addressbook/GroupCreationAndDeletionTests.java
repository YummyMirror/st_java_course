package ru.anatoli.addressbook;

import org.testng.annotations.Test;

public class GroupCreationAndDeletionTests extends TestBase {

    @Test
    public void GroupCreationAndDeletionTests() {
        User user = new User("admin", "secret");
        GroupData groupData = new GroupData("Test Group name", "Test Group header", "Test Group footer");

        getUrl("http://localhost/addressbook/group.php");
        login(user);
        goToGroupPage();
        initGroupCreation();
        inputGroupData(groupData);
        submitGroupData();
        returnToGroupPage();
        selectGroup();
        removeSelectedGroup();
        returnToGroupPage();
        goToHomePage();
    }

}
