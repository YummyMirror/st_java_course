package ru.anatoli.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.GroupData;
import java.util.List;

/**
 * Created by anatoli.anukevich on 4/19/2017.
 */
public class GroupDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        applicationManager.getNavigationHelper().goToGroupPage();
        //If there is no one group exist
        if (! applicationManager.getGroupHelper().isGroupExist()) {
            GroupData groupData = new GroupData("temp group name", "temp group header", "temp group footer");

            applicationManager.getGroupHelper().createGroup(groupData);
        }
    }

    @Test
    public void testGroupDeletion() {
        List<GroupData> before = applicationManager.getGroupHelper().getGroupList();
        int index = before.size() - 1;

        applicationManager.getGroupHelper().deleteGroup(index);
        List<GroupData> after = applicationManager.getGroupHelper().getGroupList();

        //Asserting by size of collections
        Assert.assertEquals(after.size(), index);

        before.remove(index);

        //Asserting by collections
        Assert.assertEquals(before, after);
    }
}
