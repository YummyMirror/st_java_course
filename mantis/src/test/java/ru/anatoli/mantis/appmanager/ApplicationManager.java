package ru.anatoli.mantis.appmanager;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import ru.anatoli.mantis.models.UserData;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by anatoli.anukevich on 4/19/2017.
 */
public class ApplicationManager {
    private WebDriver wd;
    private Properties properties;
    private String browser;
    private RegistrationHelper registrationHelper;
    private FtpHelper ftpHelper;
    private MailHelper mailHelper;
    private LoginHelper loginHelper;
    private NavigationHelper navigationHelper;
    private DbHelper dbHelper;
    private SoapHelper soapHelper;

    //Constructor
    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() throws IOException {
        String configFile = System.getProperty("configFile", "local.properties");
        File file = new File("src/bugify/resources/", configFile);
        FileReader reader = new FileReader(file);
        properties = new Properties();
        properties.load(reader);

        System.setProperty("webdriver.gecko.driver", "E:\\Private\\Programs\\geckodriver\\geckodriver.exe");
    }

    public void getUrl(String url) {
        wd.get(url);
    }

    public void stop() {
        if (wd != null) {
            wd.quit();
        }
    }

    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public String getProperty(String value) {
        return properties.getProperty(value);
    }

    public HttpSessionHelper session() {
        return new HttpSessionHelper(this);
    }

    public RegistrationHelper registrationHelper() {
        if (registrationHelper == null) {
            registrationHelper = new RegistrationHelper(this);
        }
        return registrationHelper;
    }

    public LoginHelper loginHelper() throws Exception {
        if (loginHelper() == null) {
            loginHelper = new LoginHelper(this);
        }
        return  loginHelper();
    }

    public NavigationHelper navigationHelper() throws Exception {
        if (navigationHelper == null) {
            navigationHelper = new NavigationHelper(this);
        }
        return  navigationHelper;
    }

    public DbHelper dbHelper() {
        return dbHelper;
    }

    public FtpHelper ftpHelper() {
        if (ftpHelper == null) {
            ftpHelper = new FtpHelper(this);
        }
        return ftpHelper;
    }

    public MailHelper mailHelper() {
        if (mailHelper == null) {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public SoapHelper soapHelper() {
        if (soapHelper == null) {
            soapHelper = new SoapHelper(this);
        }
        return soapHelper;
    }

    public WebDriver getDriver() {
        if (wd == null) {
            //Choosing browser
            if (browser.equals(BrowserType.FIREFOX)) {
                wd = new FirefoxDriver();
            }else if (browser.equals(BrowserType.CHROME)) {
                wd = new ChromeDriver();
            }else if (browser.equals(BrowserType.IE)) {
                wd = new InternetExplorerDriver();
            }
            wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            getUrl(properties.getProperty("baseUrl"));
        }
        return wd;
    }
}
