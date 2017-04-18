package ru.anatoli.addressbook.hometask.five.appmanager;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.anatoli.addressbook.hometask.five.models.UserData;

import java.util.concurrent.TimeUnit;

/**
 * Created by anatoli.anukevich on 4/18/2017.
 */
public class ApplicationManager {
    FirefoxDriver wd;
    private SessionHelper sessionHelper;
    private NavigationHelper navigationManager;
    private ContactHelper contactHelper;

    public void init() {
        UserData userData = new UserData("admin", "secret");

        System.setProperty("webdriver.gecko.driver", "E:\\Private\\Programs\\geckodriver\\geckodriver.exe");
        wd = new FirefoxDriver();
        contactHelper = new ContactHelper(wd);
        navigationManager = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        getUrl("http://localhost/addressbook/index.php");
        sessionHelper.login(userData);
    }

    public void getUrl(String url) {
        wd.get(url);
    }

    public void stop() {
        wd.quit();
    }

    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    //Getters
    public ContactHelper getContactHelper() {
        return contactHelper;
    }

    public NavigationHelper getNavigationManager() {
        return navigationManager;
    }

    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }
}
