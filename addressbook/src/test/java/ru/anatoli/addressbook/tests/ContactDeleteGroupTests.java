package ru.anatoli.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.ContactData;
import ru.anatoli.addressbook.models.Contacts;
import ru.anatoli.addressbook.models.GroupData;
import ru.anatoli.addressbook.models.Groups;
import java.io.File;
import static org.testng.Assert.assertEquals;

/**
 * Created by anatoli.anukevich on 6/7/2017.
 */
public class ContactDeleteGroupTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions(){
        if(applicationManager.getDbHelper().getGroups().size() == 0){
            applicationManager.getNavigationHelper().goToGroupPage();

            GroupData groupData = new GroupData().withGroupName("test1");
            applicationManager.getGroupHelper().createGroup(groupData);
        }

        if(applicationManager.getDbHelper().getContacts().size() == 0) {
            applicationManager.getNavigationHelper().goToHomePage();
            applicationManager.getContactHelper().initiateContactCreation();

            ContactData contactData = new ContactData().withFirstName("firstName")
                                                        //.withMiddleName("middleName")
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
    public void testContactDeleteGroup() {
        applicationManager.getNavigationHelper().goToHomePage();

        Groups groups = applicationManager.getDbHelper().getGroups();
        GroupData randomGroup = groups.iterator().next();
        applicationManager.getContactHelper().selectGroupBeforeDelete(randomGroup);

        //BEFORE
        Contacts before = applicationManager.getDbHelper().getContacts();
        ContactData removedContact = before.iterator().next();
        applicationManager.getContactHelper().removeSelectedContactFromGroup(removedContact);

        applicationManager.getNavigationHelper().goToHomePage();

        //AFTER
        Contacts after = applicationManager.getDbHelper().getContacts();

        assertEquals(before, after);
    }
}
