package ru.anatoli.addressbook.hometask.five;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Created by anatoli.anukevich on 4/18/2017.
 */
public class TestBase {
    protected final ApplicationManager applicationManager = new ApplicationManager();

    @BeforeMethod
    public void setUp() throws Exception {
        applicationManager.init();
    }

    @AfterMethod
    public void tearDown() {
        applicationManager.stop();
    }
}
