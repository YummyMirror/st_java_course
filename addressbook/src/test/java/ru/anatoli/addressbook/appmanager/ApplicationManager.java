package ru.anatoli.addressbook.appmanager;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import ru.anatoli.addressbook.models.UserData;

import java.util.concurrent.TimeUnit;

/**
 * Created by anatoli.anukevich on 4/19/2017.
 */
public class ApplicationManager {
    WebDriver wd;
    private String browser;
    private ContactHelper contactHelper;
    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;

    //Constructor
    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {
        UserData userData = new UserData("admin", "secret");

        System.setProperty("webdriver.gecko.driver", "E:\\Private\\Programs\\geckodriver\\geckodriver.exe");

        //Choosing browser
        if (browser.equals(BrowserType.FIREFOX)) {
            wd = new FirefoxDriver();
        }else if (browser.equals(BrowserType.CHROME)) {
            wd = new ChromeDriver();
        }else if (browser.equals(BrowserType.IE)) {
            wd = new InternetExplorerDriver();
        }
        groupHelper = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        contactHelper = new ContactHelper(wd);
        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        getUrl("http://localhost/addressbook");
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

    //Getters of delegats
    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }
}
