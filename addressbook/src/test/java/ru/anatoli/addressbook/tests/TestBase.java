package ru.anatoli.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.anatoli.addressbook.appmanager.ApplicationManager;

/**
 * Created by anatoli.anukevich on 4/19/2017.
 */
public class TestBase {
    protected final ApplicationManager applicationManager = new ApplicationManager(BrowserType.CHROME);

    @BeforeMethod
    public void setUp() throws Exception {
        applicationManager.init();
    }

    @AfterMethod
    public void tearDown() {
        applicationManager.stop();
    }
}
