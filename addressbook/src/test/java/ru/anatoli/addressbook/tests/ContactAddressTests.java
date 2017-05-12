package ru.anatoli.addressbook.tests;

import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.ContactData;
import java.util.Set;
import static org.testng.Assert.*;

/**
 * Created by anatoli.anukevich on 5/13/2017.
 */
public class ContactAddressTests extends TestBase {
    @Test
    public void testContactAddress() {
        applicationManager.getNavigationHelper().goToHomePage();
        Set<ContactData> objects = applicationManager.getContactHelper().getContactSet();
        ContactData outsideData = objects.iterator().next();
        ContactData insideData = applicationManager.getContactHelper().infoFromEditForm(outsideData);

        assertEquals(outsideData.getAddress(), insideData.getAddress());
    }
}
