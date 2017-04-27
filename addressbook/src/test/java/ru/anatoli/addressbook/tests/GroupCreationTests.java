package ru.anatoli.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.GroupData;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        GroupData groupData = new GroupData("group name", null, "group footer");

        applicationManager.getNavigationHelper().goToGroupPage();
        int before = applicationManager.getGroupHelper().getGroupNumber();
        applicationManager.getGroupHelper().initiateGroupCreation();
        applicationManager.getGroupHelper().inputGroupData(groupData);
        applicationManager.getGroupHelper().submitGroupCreation();
        applicationManager.getGroupHelper().returnToGroupPage();
        int after = applicationManager.getGroupHelper().getGroupNumber();
        Assert.assertEquals(after, before + 1);
    }
}
