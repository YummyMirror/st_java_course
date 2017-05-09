package ru.anatoli.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.ContactData;
import ru.anatoli.addressbook.models.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

    @Test(enabled = true)
    public void testContactCreation() {
        applicationManager.getNavigationHelper().goToHomePage();

        //Set<ContactData> before = applicationManager.getContactHelper().getContactSet();
        Contacts before = applicationManager.getContactHelper().getContactSet();  //remove after course

        ContactData contactData = new ContactData().withFirstName("FirstName111")
                                                    .withMiddleName("MiddleName1111")
                                                    .withLastName("LastName111")
                                                    .withNickname("nickname")
                                                    .withTitle("Title")
                                                    .withGroup("aaa");
        applicationManager.getContactHelper().createContact(contactData);

        //Set<ContactData> after = applicationManager.getContactHelper().getContactSet();
        Contacts after = applicationManager.getContactHelper().getContactSet(); //remove after course

            //Asserting by size of collections
        //assertEquals(after.size(), before.size() + 1);
        assertThat(after.size(), equalTo(before.size() + 1));  //remove after course

        //contactData.withId(after.stream().max((c1, c2) -> Integer.compare(c1.getId(), c2.getId())).get().getId());
        //before.add(contactData);

            //Asserting by sorted collections
        //assertEquals(before, after);
        assertThat(after, equalTo(before.withAdded(
                        contactData.withId(after.stream().max((c1, c2) -> Integer.compare(c1.getId(), c2.getId())).get().getId()))));  //remove after course
    }
}
