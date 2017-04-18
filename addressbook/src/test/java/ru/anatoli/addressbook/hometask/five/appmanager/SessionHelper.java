package ru.anatoli.addressbook.hometask.five.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.anatoli.addressbook.hometask.five.models.UserData;

/**
 * Created by anatoli.anukevich on 4/18/2017.
 */
public class SessionHelper {
    private FirefoxDriver wd;

    //Constructor
    public SessionHelper(FirefoxDriver wd) {
        this.wd = wd;
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
}
