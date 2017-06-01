package ru.anatoli.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.anatoli.mantis.appmanager.ApplicationManager;

import java.io.IOException;

/**
 * Created by anatoli.anukevich on 4/19/2017.
 */
public class TestBase {
    protected static final ApplicationManager applicationManager = new ApplicationManager(BrowserType.FIREFOX);

    @BeforeSuite
    public void setUp() throws Exception {
        applicationManager.init();
        applicationManager.ftpHelper().uploadFile("src/test/resources/config_inc.php", "config_inc.php", "config_inc.php.bak");
    }

    @AfterSuite
    public void tearDown() throws IOException {
        applicationManager.ftpHelper().restore("config_inc.php.bak", "config_inc.php");
        applicationManager.stop();
    }
}
