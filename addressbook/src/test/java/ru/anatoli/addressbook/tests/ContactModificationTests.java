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
public class ContactModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
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
    }

    @Test(enabled = true)
    public void testContactModification() {
        //Set<ContactData> before = applicationManager.getContactHelper().getContactSet();
        Contacts before = applicationManager.getContactHelper().getContactSet();   //remove after course

        ContactData modifyContact = before.iterator().next();
        ContactData contactData = new ContactData().withId(modifyContact.getId())
                                                    .withFirstName("aaa")
                                                    .withMiddleName("bbb")
                                                    .withLastName("ccc")
                                                    .withNickname("ddd")
                                                    .withTitle("eee")
                                                    .withGroup(null);
        applicationManager.getContactHelper().modifyContact(contactData);

        //Set<ContactData> after = applicationManager.getContactHelper().getContactSet();
        Contacts after = applicationManager.getContactHelper().getContactSet();   //remove after course

            //Asserting by size of collections
        //assertEquals(before.size(), after.size());
        assertThat(before.size(), equalTo(after.size()));   //remove after course

        //before.remove(modifyContact);
        //before.add(contactData);

            //Asserting by sorted collections
        //assertEquals(before, after);
        assertThat(after, equalTo(before.without(modifyContact).withAdded(contactData)));  //remove after course
    }
}