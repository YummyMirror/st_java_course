package ru.anatoli.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by anatoli.anukevich on 4/19/2017.
 */
public class NavigationHelper extends HelperBase {
    //Constructor
    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void goToGroupPage() {
        if (isElementPresent(By.tagName("h1"))
                && wd.findElement(By.tagName("h1")).getText().equals("Groups")
                && isElementPresent(By.name("new"))) {
            return;
        }else {
            click(By.linkText("groups"));
        }
    }

    public void goToHomePage() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }else {
            click(By.xpath("//*[@id='nav']/ul/li[1]/a"));
        }
    }
}
