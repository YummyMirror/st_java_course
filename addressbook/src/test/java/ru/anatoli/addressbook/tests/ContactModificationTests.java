package ru.anatoli.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.ContactData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by anatoli.anukevich on 4/20/2017.
 */
public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
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
        applicationManager.getContactHelper().initiateContactModification(0);
        ContactData contactData = new ContactData("aaa",
                                                "bbb",
                                                "ccc",
                                                "ddd",
                                                "eee",
                                                 null);

        applicationManager.getContactHelper().inputContactData(contactData, false);
        applicationManager.getContactHelper().submitContactModification();
        applicationManager.getContactHelper().returnToHomePage();
        List<ContactData> after = applicationManager.getContactHelper().getContactList();

        //Asserting by size of collections
        Assert.assertEquals(before.size(), after.size());

        before.remove(0);
        before.add(contactData);

        //Sorting collections by ID
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);

        //Asserting by sorted collections
        Assert.assertEquals(before, after);
    }
}