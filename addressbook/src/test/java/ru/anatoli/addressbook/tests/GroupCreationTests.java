package ru.anatoli.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {
    @Test
    public void testGroupCreation() {
        applicationManager.getNavigationHelper().goToGroupPage();
        List<GroupData> before = applicationManager.getGroupHelper().getGroupList();
        applicationManager.getGroupHelper().initiateGroupCreation();
        GroupData groupData = new GroupData("group name",
                                            null,
                                            "group footer");

        applicationManager.getGroupHelper().inputGroupData(groupData);
        applicationManager.getGroupHelper().submitGroupCreation();
        applicationManager.getGroupHelper().returnToGroupPage();
        List<GroupData> after = applicationManager.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() + 1);

        int maxId = 0;
        for (int i = 0; i < after.size(); i++) {
            if (after.get(i).getId() > maxId) {
                maxId = after.get(i).getId();
            }
        }
        
        groupData.setId(maxId);
        before.add(groupData);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}
