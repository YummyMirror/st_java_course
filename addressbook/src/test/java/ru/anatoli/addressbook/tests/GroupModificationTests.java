package ru.anatoli.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.GroupData;
import java.util.List;
import java.util.Set;

/**
 * Created by anatoli.anukevich on 4/19/2017.
 */
public class GroupModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        applicationManager.getNavigationHelper().goToGroupPage();
        //If there is no one group exist
        if (! applicationManager.getGroupHelper().isGroupExist()) {
            GroupData groupData1 = new GroupData().withGroupName("temp group name").withGroupHeader("temp group header").withGroupFooter("temp group footer");
            applicationManager.getGroupHelper().createGroup(groupData1);
        }
    }

    @Test
    public void testGroupModification() {
        Set<GroupData> before = applicationManager.getGroupHelper().getGroupSet();

        //Random element
        GroupData modifyGroup = before.iterator().next();
        GroupData groupData = new GroupData().withId(modifyGroup.getId()).withGroupName("aaa11").withGroupHeader("bbb22").withGroupFooter("ccc33");
        applicationManager.getGroupHelper().modifyGroup(groupData);

        Set<GroupData> after = applicationManager.getGroupHelper().getGroupSet();

        //Asserting by size of collections
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifyGroup);
        before.add(groupData);

        //Asserting collections
        Assert.assertEquals(before, after);
    }
}