package ru.anatoli.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.ContactData;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        ContactData contactData = new ContactData("FirstName", "MiddleName", "LastName", "nickname", "Title", "aaa");

        applicationManager.getNavigationHelper().goToHomePage();
        List<ContactData> before = applicationManager.getContactHelper().getContactList();
        applicationManager.getContactHelper().initiateContactCreation();
        applicationManager.getContactHelper().inputContactData(contactData, true);
        applicationManager.getContactHelper().submitContactCreation();
        applicationManager.getContactHelper().returnToHomePage();
        List<ContactData> after = applicationManager.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);
    }
}
