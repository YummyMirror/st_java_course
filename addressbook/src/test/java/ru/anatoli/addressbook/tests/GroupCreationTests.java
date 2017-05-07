package ru.anatoli.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class GroupCreationTests extends TestBase {
    @Test
    public void testGroupCreation() {
        applicationManager.getNavigationHelper().goToGroupPage();
        Set<GroupData> before = applicationManager.getGroupHelper().getGroupSet();

        GroupData groupData = new GroupData().withGroupName("group name1").withGroupFooter("group footer");
        applicationManager.getGroupHelper().createGroup(groupData);

        Set<GroupData> after = applicationManager.getGroupHelper().getGroupSet();

        //Asserting by size of collections
        Assert.assertEquals(after.size(), before.size() + 1);

        groupData.withId(after.stream().max((g1, g2) -> Integer.compare(g1.getId(), g2.getId())).get().getId());
        before.add(groupData);

        //Asserting collections
        Assert.assertEquals(before, after);
    }
}
