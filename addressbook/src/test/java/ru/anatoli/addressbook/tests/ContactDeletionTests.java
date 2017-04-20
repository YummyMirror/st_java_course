package ru.anatoli.addressbook.tests;

import org.testng.annotations.Test;

/**
 * Created by anatoli.anukevich on 4/20/2017.
 */
public class ContactDeletionTests extends TestBase {
    @Test
    public void testContactDeletion() {
        applicationManager.getNavigationHelper().goToHomePage();
        applicationManager.getContactHelper().selectFirstContact();
        applicationManager.getContactHelper().deleteSelectedContact();
        applicationManager.getContactHelper().confirmDeleteContact();
        applicationManager.getNavigationHelper().goToHomePage();
    }
}
