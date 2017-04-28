package ru.anatoli.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.GroupData;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        GroupData groupData = new GroupData("group name", null, "group footer");

        applicationManager.getNavigationHelper().goToGroupPage();
        List<GroupData> before = applicationManager.getGroupHelper().getGroupList();
        applicationManager.getGroupHelper().initiateGroupCreation();
        applicationManager.getGroupHelper().inputGroupData(groupData);
        applicationManager.getGroupHelper().submitGroupCreation();
        applicationManager.getGroupHelper().returnToGroupPage();
        List<GroupData> after = applicationManager.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() + 1);
    }
}
