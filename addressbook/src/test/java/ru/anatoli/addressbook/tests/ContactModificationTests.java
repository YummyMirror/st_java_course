package ru.anatoli.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.ContactData;
import java.util.List;

/**
 * Created by anatoli.anukevich on 4/20/2017.
 */
public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        ContactData contactData = new ContactData("aaa",
                                                "bbb",
                                                    "ccc",
                                                    "ddd",
                                                        "eee",
                                                        null);

        applicationManager.getNavigationHelper().goToHomePage();
        //If there is no one contact exist
        if (! applicationManager.getContactHelper().isContactExist()) {
            ContactData contactData1 = new ContactData("temp FirstName",
                                                    "temp MiddleName",
                                                        "temp LastName",
                                                        "temp nickname",
                                                            "temp title",
                                                            "aaa");
            applicationManager.getContactHelper().createContact(contactData1);
        }
        List<ContactData> before = applicationManager.getContactHelper().getContactList();
        applicationManager.getContactHelper().initiateContactModification();
        applicationManager.getContactHelper().inputContactData(contactData, false);
        applicationManager.getContactHelper().submitContactModification();
        applicationManager.getContactHelper().returnToHomePage();
        List<ContactData> after = applicationManager.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());
    }
}
