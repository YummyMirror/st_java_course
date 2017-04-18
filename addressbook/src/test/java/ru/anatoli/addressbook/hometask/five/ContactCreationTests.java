package ru.anatoli.addressbook.hometask.five;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        ContactData contactData = new ContactData("first name", "middle name", "last name",
                "nickname", "title", "company", "address");

        applicationManager.initiateContactCreation();
        applicationManager.inputContactData(contactData);
        applicationManager.submitContactForm();
        applicationManager.returnToHomePage();
    }
}
