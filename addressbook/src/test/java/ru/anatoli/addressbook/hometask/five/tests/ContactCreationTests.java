package ru.anatoli.addressbook.hometask.five.tests;

import org.testng.annotations.Test;
import ru.anatoli.addressbook.hometask.five.models.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        ContactData contactData = new ContactData("first name", "middle name", "last name",
                "nickname", "title", "company", "address");

        applicationManager.getContactHelper().initiateContactCreation();
        applicationManager.getContactHelper().inputContactData(contactData);
        applicationManager.getContactHelper().submitContactForm();
        applicationManager.getNavigationManager().goToHomePage();
    }
}
