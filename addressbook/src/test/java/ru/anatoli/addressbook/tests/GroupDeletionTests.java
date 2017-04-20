package ru.anatoli.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by anatoli.anukevich on 4/19/2017.
 */
public class GroupDeletionTests extends TestBase {
    @Test
    public void testGroupDeletion() {
        applicationManager.getNavigationHelper().goToGroupPage();
        applicationManager.getGroupHelper().selectFirstGroup();
        applicationManager.getGroupHelper().deleteSelectedGroup();
        applicationManager.getGroupHelper().returnToGroupPage();
    }
}
