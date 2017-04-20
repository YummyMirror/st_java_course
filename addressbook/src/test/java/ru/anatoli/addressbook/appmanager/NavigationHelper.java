package ru.anatoli.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by anatoli.anukevich on 4/19/2017.
 */
public class NavigationHelper extends HelperBase {
    //Constructor
    public NavigationHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void goToGroupPage() {
        click(By.linkText("groups"));
    }

    public void initiateContactCreatin() {
        click(By.linkText("add new"));
    }

    public void goToHomePage() {
        click(By.xpath("//*[@id='nav']/ul/li[1]/a"));
    }
}
