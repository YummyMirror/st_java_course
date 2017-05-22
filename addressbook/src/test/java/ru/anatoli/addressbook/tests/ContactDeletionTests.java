package ru.anatoli.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.ContactData;
import ru.anatoli.addressbook.models.Contacts;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by anatoli.anukevich on 4/20/2017.
 */
public class ContactDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        if (applicationManager.getDbHelper().getContacts().size() == 0) {
            applicationManager.getNavigationHelper().goToHomePage();
            ContactData contactData = new ContactData().withFirstName("temp FirstName")
                                                        .withMiddleName("temp MiddleName")
                                                        .withLastName("temp LastName")
                                                        .withNickname("temp nickname")
                                                        .withTitle("temp Title").withGroup("aaa");
            applicationManager.getContactHelper().createContact(contactData);
        }
        /*
        applicationManager.getNavigationHelper().goToHomePage();
        //If there is no one contact exist
        if (! applicationManager.getContactHelper().isContactExist()) {
            ContactData contactData = new ContactData().withFirstName("temp FirstName")
                                                        .withMiddleName("temp MiddleName")
                                                        .withLastName("temp LastName")
                                                        .withNickname("temp nickname")
                                                        .withTitle("temp Title").withGroup("aaa");
            applicationManager.getContactHelper().createContact(contactData);
        }
        */
    }

    @Test(enabled = true)
    public void testContactDeletion() {
        //Set<ContactData> before = applicationManager.getContactHelper().getContactSet();
        //Contacts before = applicationManager.getContactHelper().getContactSet(); //remove after course
        Contacts before = applicationManager.getDbHelper().getContacts();

        ContactData deleteContact = before.iterator().next();
        applicationManager.getContactHelper().removeContact(deleteContact);

        //Set<ContactData> after = applicationManager.getContactHelper().getContactSet();
        //Contacts after = applicationManager.getContactHelper().getContactSet();  //remove after course
        Contacts after = applicationManager.getDbHelper().getContacts();

            //Asserting by size of collections
        //assertEquals(after.size(), before.size() - 1);
        //assertThat(after.size(), equalTo(before.size() - 1));

        //before.remove(deleteContact);

            //Asserting by collections
        //assertEquals(before, after);
        assertThat(after, equalTo(before.without(deleteContact)));  //remove after course
        compareBDvsUIdataContacts();
    }
}
