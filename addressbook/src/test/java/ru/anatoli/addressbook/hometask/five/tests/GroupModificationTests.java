package ru.anatoli.addressbook.hometask.five.tests;

import org.testng.annotations.Test;
import ru.anatoli.addressbook.hometask.five.models.GroupData;

/**
 * Created by anatoli.anukevich on 4/19/2017.
 */
public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification() {
        GroupData groupData = new GroupData("zzz", "yyy", "eee");

        applicationManager.getNavigationManager().goToGroupPage();
        applicationManager.getGroupHelper().selectGroup();
        applicationManager.getGroupHelper().initiateGroupModification();
        applicationManager.getGroupHelper().inputGroupData(groupData);
        applicationManager.getGroupHelper().submitGroupFormModification();
        applicationManager.getGroupHelper().returnToGroupPage();

    }
}
