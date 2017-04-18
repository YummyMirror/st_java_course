package ru.anatoli.addressbook.hometask.five.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by anatoli.anukevich on 4/18/2017.
 */
public class NavigationHelper extends HelperBase {
    //Constructor
    public NavigationHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void goToHomePage() {
        click(By.linkText("home page"));
    }
}
