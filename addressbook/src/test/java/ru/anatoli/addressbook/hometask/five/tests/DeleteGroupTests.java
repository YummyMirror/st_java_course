package ru.anatoli.addressbook.hometask.five.tests;

import org.testng.annotations.Test;
import ru.anatoli.addressbook.hometask.five.tests.TestBase;

public class DeleteGroupTests extends TestBase {
    
    @Test
    public void testDeleteGroup() {
        applicationManager.getNavigationManager().goToGroupPage();
        applicationManager.getGroupHelper().selectGroup();
        applicationManager.getGroupHelper().deleteSelectedGroup();
        applicationManager.getGroupHelper().returnToGroupPage();
    }
}
