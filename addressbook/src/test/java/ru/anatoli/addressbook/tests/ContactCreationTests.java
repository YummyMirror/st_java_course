package ru.anatoli.addressbook.tests;

import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        ContactData contactData = new ContactData("FirstName", "MiddleName", "LastName", "nickname", "Title");

        applicationManager.getNavigationHelper().initiateContactCreatin();
        applicationManager.getContactHelper().inputContactData(contactData);
        applicationManager.getContactHelper().submitContactCreation();
        applicationManager.getContactHelper().returnToHomePage();
    }
}
