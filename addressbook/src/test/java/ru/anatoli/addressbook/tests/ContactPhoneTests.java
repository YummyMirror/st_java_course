package ru.anatoli.addressbook.tests;

import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.ContactData;
import java.util.Set;
import static org.testng.Assert.*;

/**
 * Created by anatoli.anukevich on 5/12/2017.
 */
public class ContactPhoneTests extends TestBase {
    @Test
    public void testContactPhones() {
        applicationManager.getNavigationHelper().goToHomePage();
            //OUTSIDE
        Set<ContactData> objects = applicationManager.getContactHelper().getContactSet();
        ContactData outsideData = objects.iterator().next();
            //INSIDE
        ContactData insideData = applicationManager.getContactHelper().infoFromEditForm(outsideData);

        assertEquals(outsideData.getHomePhone(), cleaner(insideData.getHomePhone()));
        assertEquals(outsideData.getMobilePhone(), cleaner(insideData.getMobilePhone()));
        assertEquals(outsideData.getWorkPhone(), cleaner(insideData.getWorkPhone()));
    }

    public String cleaner(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
