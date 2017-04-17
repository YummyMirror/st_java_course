package ru.anatoli.addressbook.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.anatoli.addressbook.appmanager.ApplicationManager;

/**
 * Created by anatoli.anukevich on 4/16/2017.
 */
public class TestBase {

    protected final ApplicationManager app = new ApplicationManager();

    @BeforeMethod
    public void setUp() throws Exception {
        app.start();
    }

    @AfterMethod
    public void tearDown() {
        app.stop();
    }

}
