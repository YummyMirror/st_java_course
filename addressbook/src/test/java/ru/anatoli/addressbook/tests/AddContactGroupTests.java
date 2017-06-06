package ru.anatoli.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.ContactData;
import ru.anatoli.addressbook.models.Contacts;
import ru.anatoli.addressbook.models.GroupData;
import ru.anatoli.addressbook.models.Groups;

import java.io.File;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by anatoli.anukevich on 6/6/2017.
 */
public class AddContactGroupTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        applicationManager.getNavigationHelper().goToGroupPage();
        if (applicationManager.getDbHelper().getGroups().size() == 0) {
            GroupData groupData = new GroupData().withGroupName("aaa");
            applicationManager.getGroupHelper().createGroup(groupData);
        }

        if (applicationManager.getDbHelper().getContacts().size() == 0) {
            applicationManager.getNavigationHelper().goToHomePage();
            ContactData contactData = new ContactData().withFirstName("temp FirstName")
                                                        .withMiddleName("middleName")
                                                        .withLastName("lastName")
                                                        .withNickname("nickname")
                                                        .withPhoto(new File("src/test/resources/", "image.png"))
                                                        .withTitle("title")
                                                        .withAddress("address")
                                                        .withHomePhone("111")
                                                        .withMobilePhone("222")
                                                        .withWorkPhone("333")
                                                        .withEmail("1@mail.ru")
                                                        .withEmail2("2@mail.ru")
                                                        .withEmail3("3@mail.ru");
            applicationManager.getContactHelper().createContact(contactData);
        }
    }

    @Test
    public void testAddContactGroup() {
        Contacts contacts = applicationManager.getDbHelper().getContacts();
        ContactData randomContact = contacts.iterator().next();

        GroupData group;
        Groups groups = applicationManager.getDbHelper().getGroups();

        if (groups.equals(randomContact.getGroups())) {
            applicationManager.getGroupHelper().createGroup(new GroupData().withGroupName("new_group"));
            groups = applicationManager.getDbHelper().getGroups();
        }
        groups.removeAll(randomContact.getGroups());
        group = groups.iterator().next();

        applicationManager.getNavigationHelper().goToHomePage();
        applicationManager.getContactHelper().addContactGroups(randomContact, group);

        applicationManager.getDbHelper().refresh(randomContact);

        assertThat(randomContact.getGroups(), hasItem(group));
    }
}
