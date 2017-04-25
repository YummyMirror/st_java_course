package ru.anatoli.addressbook.tests;

import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.GroupData;

/**
 * Created by anatoli.anukevich on 4/19/2017.
 */
public class GroupModificationTests extends TestBase {
    @Test
    public void testGroupModification() {
        GroupData groupData = new GroupData("aaa",
                                            "bbb",
                                             "ccc");

        applicationManager.getNavigationHelper().goToGroupPage();
        //If there is no one group exist
        if (! applicationManager.getGroupHelper().isGroupExist()) {
            GroupData groupData1 = new GroupData("temp group name",
                                                "temp group header",
                                                "temp group footer");
            applicationManager.getGroupHelper().createGroup(groupData1);
        }
        applicationManager.getGroupHelper().selectFirstGroup();
        applicationManager.getGroupHelper().initiateGroupModification();
        applicationManager.getGroupHelper().inputGroupData(groupData);
        applicationManager.getGroupHelper().submitGroupModification();
    }
}
