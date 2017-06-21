package ru.anatoli.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;
import ru.anatoli.addressbook.appmanager.ApplicationManager;
import ru.anatoli.addressbook.models.ContactData;
import ru.anatoli.addressbook.models.Contacts;
import ru.anatoli.addressbook.models.GroupData;
import ru.anatoli.addressbook.models.Groups;

import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;

/**
 * Created by anatoli.anukevich on 4/19/2017.
 */
@Listeners(MyTestListener.class)
public class TestBase {
    protected static final ApplicationManager applicationManager = new ApplicationManager(BrowserType.CHROME);

    @BeforeSuite
    public void setUp(ITestContext context) throws Exception {
        applicationManager.init();
        context.setAttribute("applicationManager", applicationManager);
    }

    @AfterSuite
    public void tearDown() {
        applicationManager.stop();
    }

    public void compareBDvsUIdataGroups() {
        if (Boolean.getBoolean("verifyUI")) {
            Groups dbData = applicationManager.getDbHelper().getGroups();
            Groups uiData = applicationManager.getGroupHelper().getGroupSet();
            assertEquals(uiData, dbData);
        /*
        assertEquals(uiData, dbData.stream().map((g) -> new GroupData().withId(g.getId())
                                                                        .withGroupName(g.getGroupName()))
                                            .collect(Collectors.toSet()));
        */
        }
    }

    public void compareBDvsUIdataContacts() {
        if (Boolean.getBoolean("verifyUI")) {
            Contacts dbData = applicationManager.getDbHelper().getContacts();
            Contacts uiData = applicationManager.getContactHelper().getContactSet();
            assertEquals(uiData, dbData);
        /*
        assertEquals(uiData, dbData.stream().map((c) -> new ContactData().withId(c.getId())
                                                                            .withFirstName(c.getFirstName())
                                                                            .withMiddleName(c.getMiddleName()))
                                                .collect(Collectors.toSet()));
        */
        }
    }
}
