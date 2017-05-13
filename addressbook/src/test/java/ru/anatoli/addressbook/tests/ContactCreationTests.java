package ru.anatoli.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.ContactData;
import ru.anatoli.addressbook.models.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        applicationManager.getNavigationHelper().goToHomePage();
    }

    @Test(enabled = true)
    public void testContactCreation() {
        //Set<ContactData> before = applicationManager.getContactHelper().getContactSet();
        Contacts before = applicationManager.getContactHelper().getContactSet();  //remove after course

        File photo = new File("src/test/resources/", "image.png");
        ContactData contactData = new ContactData().withFirstName("FirstName111")
                                                    .withMiddleName("MiddleName1111")
                                                    .withLastName("LastName111")
                                                    .withNickname("nickname")
                                                    .withPhoto(photo)
                                                    .withTitle("Title")
                                                    .withAddress("111\n222\n333")
                                                    .withHomePhone("123")
                                                    .withMobilePhone("456")
                                                    .withWorkPhone("789")
                                                    .withEmail("1@mail.ru")
                                                    .withEmail2("2@mail.ru")
                                                    .withEmail3("3@mail.ru")
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
