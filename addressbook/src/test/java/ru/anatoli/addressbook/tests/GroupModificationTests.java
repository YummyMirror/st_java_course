package ru.anatoli.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.GroupData;

import java.util.HashSet;
import java.util.List;

/**
 * Created by anatoli.anukevich on 4/19/2017.
 */
public class GroupModificationTests extends TestBase {
    @Test
    public void testGroupModification() {
        applicationManager.getNavigationHelper().goToGroupPage();
        //If there is no one group exist
        if (! applicationManager.getGroupHelper().isGroupExist()) {
            GroupData groupData1 = new GroupData("temp group name",
                                                "temp group header",
                                                "temp group footer");
            applicationManager.getGroupHelper().createGroup(groupData1);
        }
        List<GroupData> before = applicationManager.getGroupHelper().getGroupList();
        applicationManager.getGroupHelper().selectGroup(before.size() - 1);
        applicationManager.getGroupHelper().initiateGroupModification();
        GroupData groupData = new GroupData(before.get(before.size() - 1).getId(),
                                            "aaa1",
                                            "bbb2",
                                            "ccc3");

        applicationManager.getGroupHelper().inputGroupData(groupData);
        applicationManager.getGroupHelper().submitGroupModification();
        applicationManager.getGroupHelper().returnToGroupPage();
        List<GroupData> after = applicationManager.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(groupData);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}
