package ru.anatoli.addressbook.tests;

import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.ContactData;
import java.util.Set;
import static org.testng.Assert.assertEquals;

/**
 * Created by anatoli.anukevich on 5/13/2017.
 */
public class ContactEmailTests extends TestBase {
    @Test
    public void testContactEmails() {
        applicationManager.getNavigationHelper().goToHomePage();
        Set<ContactData> objects = applicationManager.getContactHelper().getContactSet();
        ContactData outsideData = objects.iterator().next();
        ContactData insideData = applicationManager.getContactHelper().infoFromEditForm(outsideData);

        assertEquals(outsideData.getEmail(), cleaned(insideData.getEmail()));
        assertEquals(outsideData.getEmail2(), cleaned(insideData.getEmail2()));
        assertEquals(outsideData.getEmail3(), cleaned(insideData.getEmail3()));
    }

    public String cleaned(String emails) {
        return emails.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
