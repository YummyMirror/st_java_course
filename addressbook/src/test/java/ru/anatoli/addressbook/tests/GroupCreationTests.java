package ru.anatoli.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {
    @Test
    public void testGroupCreation() {
        applicationManager.getNavigationHelper().goToGroupPage();
        List<GroupData> before = applicationManager.getGroupHelper().getGroupList();
        GroupData groupData = new GroupData().withGroupName("group name1").withGroupFooter("group footer");

        applicationManager.getGroupHelper().createGroup(groupData);
        List<GroupData> after = applicationManager.getGroupHelper().getGroupList();

        //Asserting by size of collections
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(groupData);

        //Sorting collections by ID
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);

        //Asserting by sorted collections
        Assert.assertEquals(before, after);
    }
}
