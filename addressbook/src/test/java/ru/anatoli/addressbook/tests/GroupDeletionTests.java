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
public class GroupDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        applicationManager.getNavigationHelper().goToGroupPage();
        //If there is no one group exist
        if (! applicationManager.getGroupHelper().isGroupExist()) {
            GroupData groupData = new GroupData().withGroupName("temp group name").withGroupHeader("temp group header").withGroupFooter("temp group footer");

            applicationManager.getGroupHelper().createGroup(groupData);
        }
    }

    @Test
    public void testGroupDeletion() {
        Set<GroupData> before = applicationManager.getGroupHelper().getGroupSet();

        //Random element
        GroupData deleteGroup = before.iterator().next();
        applicationManager.getGroupHelper().deleteGroup(deleteGroup);

        Set<GroupData> after = applicationManager.getGroupHelper().getGroupSet();

        //Asserting by size of collections
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deleteGroup);

        //Asserting by collections
        Assert.assertEquals(before, after);
    }
}
