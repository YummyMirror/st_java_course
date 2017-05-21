package ru.anatoli.addressbook.appmanager;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import ru.anatoli.addressbook.models.UserData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by anatoli.anukevich on 4/19/2017.
 */
public class ApplicationManager {
    WebDriver wd;
    private Properties properties;
    private String browser;
    private ContactHelper contactHelper;
    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private DbHelper dbHelper;

    //Constructor
    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() throws IOException {
        String configFile = System.getProperty("configFile", "local.properties");
        File file = new File("src/test/resources/", configFile);
        FileReader reader = new FileReader(file);
        properties = new Properties();
        properties.load(reader);

        UserData userData = new UserData(properties.getProperty("adminUser"), properties.getProperty("adminPassword"));

        System.setProperty("webdriver.gecko.driver", "E:\\Private\\Programs\\geckodriver\\geckodriver.exe");

        dbHelper = new DbHelper();
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
        getUrl(properties.getProperty("baseUrl"));
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

    public DbHelper getDbHelper() {
        return dbHelper;
    }
}
