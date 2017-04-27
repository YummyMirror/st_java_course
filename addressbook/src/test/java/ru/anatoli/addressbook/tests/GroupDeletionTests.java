package ru.anatoli.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.GroupData;

/**
 * Created by anatoli.anukevich on 4/19/2017.
 */
public class GroupDeletionTests extends TestBase {
    @Test
    public void testGroupDeletion() {
        GroupData groupData = new GroupData("temp group name",
                                        "temp group header",
                                        "temp group footer");

        applicationManager.getNavigationHelper().goToGroupPage();
        int before = applicationManager.getGroupHelper().getGroupNumber();
        //If there is no one group exist
        if (! applicationManager.getGroupHelper().isGroupExist()) {
            applicationManager.getGroupHelper().createGroup(groupData);
        }
        applicationManager.getGroupHelper().selectFirstGroup();
        applicationManager.getGroupHelper().deleteSelectedGroup();
        applicationManager.getGroupHelper().returnToGroupPage();
        int after = applicationManager.getGroupHelper().getGroupNumber();
        Assert.assertEquals(after, before - 1);
    }
}
