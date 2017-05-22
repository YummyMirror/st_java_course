package ru.anatoli.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.ContactData;
import ru.anatoli.addressbook.models.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by anatoli.anukevich on 4/20/2017.
 */
public class ContactModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        if (applicationManager.getDbHelper().getContacts().size() == 0) {
            applicationManager.getNavigationHelper().goToHomePage();
            ContactData contactData1 = new ContactData().withFirstName("temp FirstName")
                                                        .withMiddleName("temp MiddleName")
                                                        .withLastName("temp LastName")
                                                        .withNickname("temp nickname")
                                                        .withTitle("temp title")
                                                        .withGroup("aaa");
            applicationManager.getContactHelper().createContact(contactData1);
        }
        /*
        applicationManager.getNavigationHelper().goToHomePage();
        //If there is no one contact exist
        if (! applicationManager.getContactHelper().isContactExist()) {
            ContactData contactData1 = new ContactData().withFirstName("temp FirstName")
                                                        .withMiddleName("temp MiddleName")
                                                        .withLastName("temp LastName")
                                                        .withNickname("temp nickname")
                                                        .withTitle("temp title")
                                                        .withGroup("aaa");
            applicationManager.getContactHelper().createContact(contactData1);
        }
        */
    }

    @Test(enabled = true)
    public void testContactModification() {
        //Set<ContactData> before = applicationManager.getContactHelper().getContactSet();
        //Contacts before = applicationManager.getContactHelper().getContactSet();   //remove after course
        Contacts before = applicationManager.getDbHelper().getContacts();

        ContactData modifyContact = before.iterator().next();
        ContactData contactData = new ContactData().withId(modifyContact.getId())
                                                    .withFirstName("aaa1")
                                                    .withMiddleName("bbb1")
                                                    .withLastName("ccc1")
                                                    .withNickname("ddd1")
                                                    .withPhoto(new File("src/test/resources/", "image.png"))
                                                    .withTitle("eee1")
                                                    .withAddress("address")
                                                    .withHomePhone("111")
                                                    .withMobilePhone("222")
                                                    .withWorkPhone("333")
                                                    .withEmail("1@mail.ru")
                                                    .withEmail2("2@mail.ru")
                                                    .withEmail3("3@mail.ru")
                                                    .withGroup(null);

        applicationManager.getContactHelper().modifyContact(contactData);

        //Set<ContactData> after = applicationManager.getContactHelper().getContactSet();
        //Contacts after = applicationManager.getContactHelper().getContactSet();   //remove after course
        Contacts after = applicationManager.getDbHelper().getContacts();

            //Asserting by size of collections
        //assertEquals(before.size(), after.size());
        //assertThat(before.size(), equalTo(after.size()));   //remove after course

        //before.remove(modifyContact);
        //before.add(contactData);

            //Asserting by sorted collections
        //assertEquals(before, after);
        assertThat(after, equalTo(before.without(modifyContact).withAdded(contactData)));  //remove after course
        compareBDvsUIdataContacts();
    }
}