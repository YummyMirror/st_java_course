package ru.anatoli.addressbook.tests;

import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.GroupData;
import ru.anatoli.addressbook.models.User;

public class GroupCreationAndDeletionTests extends TestBase {

    @Test
    public void GroupCreationAndDeletionTests() {
        User user = new User("admin", "secret");
        GroupData groupData = new GroupData("Test Group name", "Test Group header", "Test Group footer");

        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().initGroupCreation();
        app.getGroupHelper().inputGroupData(groupData);
        app.getGroupHelper().submitGroupData();
        app.getGroupHelper().returnToGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().removeSelectedGroup();
        app.getGroupHelper().returnToGroupPage();
        app.goToHomePage();
    }

}
