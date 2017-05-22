package ru.anatoli.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.anatoli.addressbook.appmanager.ApplicationManager;
import ru.anatoli.addressbook.models.GroupData;
import ru.anatoli.addressbook.models.Groups;

import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;

/**
 * Created by anatoli.anukevich on 4/19/2017.
 */
public class TestBase {
    protected static final ApplicationManager applicationManager = new ApplicationManager(BrowserType.IE);

    @BeforeSuite
    public void setUp() throws Exception {
        applicationManager.init();
    }

    @AfterSuite
    public void tearDown() {
        applicationManager.stop();
    }

    public void compareBDvsUIdata() {
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
}
