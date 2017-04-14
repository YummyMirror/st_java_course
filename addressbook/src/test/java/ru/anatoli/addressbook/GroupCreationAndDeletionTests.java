package ru.anatoli.addressbook;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;

public class GroupCreationAndDeletionTests {
    FirefoxDriver wd;
    
    @BeforeMethod
    public void setUp() throws Exception {
        System.setProperty("webdriver.gecko.driver", "E:\\Private\\Programs\\geckodriver\\geckodriver.exe");
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }
    
    @Test
    public void GroupCreationAndDeletionTests() {
        User user = new User("admin", "secret");
        GroupData groupData = new GroupData("Test Group name", "Test Group header", "Test Group footer");

        getUrl("http://localhost/addressbook/group.php");
        login(user);
        goToGroupPage();
        initGroupCreation();
        inputGroupData(groupData);
        submitGroupData();
        returnToGroupPage();
        selectGroup();
        removeSelectedGroup();
        returnToGroupPage();
        goToHomePage();
    }

    private void goToHomePage() {
        wd.findElement(By.linkText("home")).click();
    }

    private void removeSelectedGroup() {
        wd.findElement(By.xpath("//div[@id='content']/form/input[5]")).click();
    }

    private void selectGroup() {
        if (!wd.findElement(By.xpath("//div[@id='content']/form/span[3]/input")).isSelected()) {
            wd.findElement(By.xpath("//div[@id='content']/form/span[3]/input")).click();
        }
    }

    private void returnToGroupPage() {
        wd.findElement(By.linkText("group page")).click();
    }

    private void submitGroupData() {
        wd.findElement(By.name("submit")).click();
    }

    private void inputGroupData(GroupData groupData) {
        wd.findElement(By.name("group_name")).click();
        wd.findElement(By.name("group_name")).clear();
        wd.findElement(By.name("group_name")).sendKeys(groupData.getName());
        wd.findElement(By.name("group_header")).click();
        wd.findElement(By.name("group_header")).clear();
        wd.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
        wd.findElement(By.name("group_footer")).click();
        wd.findElement(By.name("group_footer")).clear();
        wd.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
    }

    private void initGroupCreation() {
        wd.findElement(By.name("new")).click();
    }

    private void goToGroupPage() {
        wd.findElement(By.linkText("groups")).click();
    }

    private void login(User user) {
        wd.findElement(By.name("user")).click();
        wd.findElement(By.name("user")).clear();
        wd.findElement(By.name("user")).sendKeys(user.getUserName());
        wd.findElement(By.name("pass")).click();
        wd.findElement(By.name("pass")).clear();
        wd.findElement(By.name("pass")).sendKeys(user.getPassword());
        wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
    }

    private void getUrl(String url) {
        wd.get(url);
    }

    @AfterMethod
    public void tearDown() {
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
