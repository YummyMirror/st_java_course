package ru.anatoli.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.GroupData;

import java.util.Comparator;
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
        
        groupData.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(groupData);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}
