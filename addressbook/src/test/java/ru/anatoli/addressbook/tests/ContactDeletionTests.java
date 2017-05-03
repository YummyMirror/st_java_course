package ru.anatoli.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.ContactData;

import java.util.List;

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
        //If there is no one contact exist
        if (! applicationManager.getContactHelper().isContactExist()) {
            applicationManager.getContactHelper().createContact(contactData);
        }
        List<ContactData> before = applicationManager.getContactHelper().getContactList();
        applicationManager.getContactHelper().selectContact(before.size() - 1);
        applicationManager.getContactHelper().deleteSelectedContact();
        applicationManager.getContactHelper().confirmDeleteContact();
        applicationManager.getNavigationHelper().goToHomePage();
        List<ContactData> after = applicationManager.getContactHelper().getContactList();

        //Asserting by size of collections
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);

        //Asserting by collections
        Assert.assertEquals(before, after);
    }
}
