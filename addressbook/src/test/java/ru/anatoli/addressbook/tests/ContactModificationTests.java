package ru.anatoli.addressbook.tests;

import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.ContactData;

/**
 * Created by anatoli.anukevich on 4/20/2017.
 */
public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        ContactData contactData = new ContactData("aaa", "bbb", "ccc", "ddd", "eee");

        applicationManager.getNavigationHelper().goToHomePage();
        applicationManager.getContactHelper().initiateContactModification();
        applicationManager.getContactHelper().inputContactData(contactData);
        applicationManager.getContactHelper().submitContactModification();
        applicationManager.getContactHelper().returnToHomePage();
    }
}
