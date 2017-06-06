package ru.anatoli.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.ContactData;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by anatoli.anukevich on 6/6/2017.
 */
public class ContactInfoTests extends TestBase {
    @Test
    public void testContactInfo() {
        applicationManager.getNavigationHelper().goToHomePage();
        Set<ContactData> contacts = applicationManager.getContactHelper().getContactSet();
        ContactData randomContact = contacts.iterator().next();

        //Info from VIEW form
        String contactInfoFromViewForm = applicationManager.getContactHelper().infoFromViewForm(randomContact);

        applicationManager.getContactHelper().goToHomePage();

        //Info from EDIT form
        ContactData contactDataFromEditForm = applicationManager.getContactHelper().infoFromEditForm(randomContact);

        Assert.assertEquals(clean(contactInfoFromViewForm), mergeAll(contactDataFromEditForm));
    }

    private String mergeName(ContactData contactData) {
        return Arrays.asList(contactData.getFirstName().replaceAll("\n", ""),contactData.getLastName())
                .stream().filter(s -> !(s == null || s.equals("")))
                .map(ContactInfoTests::clean)
                .collect(Collectors.joining(" "));
    }

    private String mergeOther(ContactData contact) {
        return Arrays.asList(contact.getAddress(),
                contact.getHomePhone(),
                contact.getMobilePhone(),
                contact.getWorkPhone(),
                contact.getEmail(),
                contact.getEmail2(),
                contact.getEmail3())
                .stream().filter(s -> !(s == null || s.equals("")))
                .map(ContactInfoTests::clean).collect(Collectors.joining("\n"));
    }

    private String mergeAll(ContactData contact) {
        return Arrays.asList(mergeName(contact), mergeOther(contact)).stream().collect(Collectors.joining("\n"));
    }

    private static String clean(String contactInfo) {
        return contactInfo.replaceAll("[HMWP]: ", "")
                .replaceAll("Modify", "")
                .replaceAll("Print", "")
                .replaceAll("\n\n\n\n", "\n")
                .replaceAll("\n\n", "\n");
    }
}
