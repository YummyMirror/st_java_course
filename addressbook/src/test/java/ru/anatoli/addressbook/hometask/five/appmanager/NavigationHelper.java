package ru.anatoli.addressbook.hometask.five.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by anatoli.anukevich on 4/18/2017.
 */
public class NavigationHelper {
    private FirefoxDriver wd;

    //Constructor
    public NavigationHelper(FirefoxDriver wd) {
        this.wd = wd;
    }

    public void goToHomePage() {
        wd.findElement(By.linkText("home page")).click();
    }
}
