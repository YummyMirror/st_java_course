package ru.anatoli.addressbook.hometask.five.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.anatoli.addressbook.hometask.five.models.ContactData;
import ru.anatoli.addressbook.hometask.five.models.UserData;

import java.util.concurrent.TimeUnit;

/**
 * Created by anatoli.anukevich on 4/18/2017.
 */
public class ApplicationManager {
    FirefoxDriver wd;

    public void init() {
        UserData userData = new UserData("admin", "secret");

        System.setProperty("webdriver.gecko.driver", "E:\\Private\\Programs\\geckodriver\\geckodriver.exe");
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        getUrl("http://localhost/addressbook/index.php");
        login(userData);
    }

    public void login(UserData userData) {
        wd.findElement(By.name("user")).click();
        wd.findElement(By.name("user")).clear();
        wd.findElement(By.name("user")).sendKeys(userData.getUserName());
        wd.findElement(By.name("pass")).click();
        wd.findElement(By.name("pass")).clear();
        wd.findElement(By.name("pass")).sendKeys(userData.getPassword());
        wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
    }

    public void getUrl(String url) {
        wd.get(url);
    }

    public void returnToHomePage() {
        wd.findElement(By.linkText("home page")).click();
    }

    public void submitContactForm() {
        wd.findElement(By.name("submit")).click();
    }

    public void inputContactData(ContactData contactData) {
        wd.findElement(By.name("firstname")).click();
        wd.findElement(By.name("firstname")).clear();
        wd.findElement(By.name("firstname")).sendKeys(contactData.getFirstname());
        wd.findElement(By.name("middlename")).click();
        wd.findElement(By.name("middlename")).clear();
        wd.findElement(By.name("middlename")).sendKeys(contactData.getMiddlename());
        wd.findElement(By.name("lastname")).click();
        wd.findElement(By.name("lastname")).clear();
        wd.findElement(By.name("lastname")).sendKeys(contactData.getLastname());
        wd.findElement(By.name("nickname")).click();
        wd.findElement(By.name("nickname")).clear();
        wd.findElement(By.name("nickname")).sendKeys(contactData.getNickname());
        wd.findElement(By.name("title")).click();
        wd.findElement(By.name("title")).clear();
        wd.findElement(By.name("title")).sendKeys(contactData.getTitle());
        wd.findElement(By.name("company")).click();
        wd.findElement(By.name("company")).clear();
        wd.findElement(By.name("company")).sendKeys(contactData.getCompany());
        wd.findElement(By.name("address")).click();
        wd.findElement(By.name("address")).clear();
        wd.findElement(By.name("address")).sendKeys(contactData.getAddress());
    }

    public void initiateContactCreation() {
        wd.findElement(By.linkText("add new")).click();
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
}
