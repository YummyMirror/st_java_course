package ru.anatoli.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.ContactData;

/**
 * Created by anatoli.anukevich on 4/20/2017.
 */
public class ContactDeletionTests extends TestBase {
    @Test
    public void testContactDeletion() {
        ContactData contactData = new ContactData("temp FirstName",
                                                "temp MiddleName",
                                                    "temp LastName",
                                                    "temp nickname",
                                                        "temp Title",
                                                        "aaa");

        applicationManager.getNavigationHelper().goToHomePage();
        int before = applicationManager.getContactHelper().getContactNumber();
        //If there is no one contact exist
        if (! applicationManager.getContactHelper().isContactExist()) {
            applicationManager.getContactHelper().createContact(contactData);
        }
        applicationManager.getContactHelper().selectFirstContact();
        applicationManager.getContactHelper().deleteSelectedContact();
        applicationManager.getContactHelper().confirmDeleteContact();
        applicationManager.getNavigationHelper().goToHomePage();
        int after = applicationManager.getContactHelper().getContactNumber();
        Assert.assertEquals(after, before - 1);
    }
}
