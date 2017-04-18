package ru.anatoli.addressbook.hometask.five.tests;

import org.testng.annotations.Test;

import ru.anatoli.addressbook.hometask.five.models.GroupData;

public class GroupCreationTests extends TestBase {

    @Test
    public void GroupCreationTests() {
        GroupData groupData = new GroupData("qqq", "www", "eee");

        applicationManager.getNavigationManager().goToGroupPage();
        applicationManager.getGroupHelper().initiateGroupCreation();
        applicationManager.getGroupHelper().inputGroupData(groupData);
        applicationManager.getGroupHelper().submitGroupForm();
        applicationManager.getGroupHelper().returnToGroupPage();
    }
}
