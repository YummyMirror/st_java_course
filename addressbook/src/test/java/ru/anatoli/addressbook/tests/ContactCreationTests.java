package ru.anatoli.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test(enabled = false)
    public void testContactCreation() {
        applicationManager.getNavigationHelper().goToHomePage();
        List<ContactData> before = applicationManager.getContactHelper().getContactList();
        applicationManager.getContactHelper().initiateContactCreation();
        ContactData contactData = new ContactData("FirstName1",
                                                    "MiddleName1",
                                                    "LastName1",
                                                    "nickname",
                                                    "Title",
                                                    "aaa");

        applicationManager.getContactHelper().inputContactData(contactData, true);
        applicationManager.getContactHelper().submitContactCreation();
        applicationManager.getContactHelper().returnToHomePage();
        List<ContactData> after = applicationManager.getContactHelper().getContactList();

        //Asserting by size of collections
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contactData);

        //Sorting collections by ID
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);

        //Asserting by sorted collections
        Assert.assertEquals(before, after);
    }
}
