package ru.anatoli.addressbook.tests;

import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.GroupData;

/**
 * Created by anatoli.anukevich on 4/19/2017.
 */
public class GroupModificationTests extends TestBase {
    @Test
    public void testGroupModification() {
        GroupData groupData = new GroupData("aaa", "bbb", null);

        applicationManager.getNavigationHelper().goToGroupPage();
        applicationManager.getGroupHelper().selectFirstGroup();
        applicationManager.getGroupHelper().initiateGroupModification();
        applicationManager.getGroupHelper().inputGroupData(groupData);
        applicationManager.getGroupHelper().submitGroupModification();
    }
}
