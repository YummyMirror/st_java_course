package ru.anatoli.addressbook.hometask.five.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.anatoli.addressbook.hometask.five.models.UserData;

/**
 * Created by anatoli.anukevich on 4/18/2017.
 */
public class SessionHelper extends HelperBase {
    //Constructor
    public SessionHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void login(UserData userData) {
        click(By.name("user"));
        wd.findElement(By.name("user")).clear();
        wd.findElement(By.name("user")).sendKeys(userData.getUserName());
        click(By.name("pass"));
        wd.findElement(By.name("pass")).clear();
        wd.findElement(By.name("pass")).sendKeys(userData.getPassword());
        click(By.xpath("//form[@id='LoginForm']/input[3]"));
    }
}
