package ru.anatoli.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        ContactData contactData = new ContactData("FirstName", "MiddleName", "LastName", "nickname", "Title", "aaa");

        applicationManager.getNavigationHelper().goToHomePage();
        int before = applicationManager.getContactHelper().getContactNumber();
        applicationManager.getContactHelper().initiateContactCreation();
        applicationManager.getContactHelper().inputContactData(contactData, true);
        applicationManager.getContactHelper().submitContactCreation();
        applicationManager.getContactHelper().returnToHomePage();
        int after = applicationManager.getContactHelper().getContactNumber();
        Assert.assertEquals(after, before + 1);
    }
}
