package ru.anatoli.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Created by anatoli.anukevich on 4/19/2017.
 */
public class GroupModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        applicationManager.getNavigationHelper().goToGroupPage();
        //If there is no one group exist
        if (! applicationManager.getGroupHelper().isGroupExist()) {
            GroupData groupData1 = new GroupData("temp group name", "temp group header", "temp group footer");
            applicationManager.getGroupHelper().createGroup(groupData1);
        }
    }

    @Test
    public void testGroupModification() {
        List<GroupData> before = applicationManager.getGroupHelper().getGroupList();
        int index = before.size() - 1;
        GroupData groupData = new GroupData("aaa11", "bbb22", "ccc33");

        applicationManager.getGroupHelper().modifyGroup(index, groupData);
        List<GroupData> after = applicationManager.getGroupHelper().getGroupList();

        //Asserting by size of collections
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(groupData);

        //Sorting collections by ID
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);

        //Asserting by sorted collections
        //Assert.assertEquals(before, after);
    }
}