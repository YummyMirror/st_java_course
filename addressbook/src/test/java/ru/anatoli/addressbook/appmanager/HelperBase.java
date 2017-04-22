package ru.anatoli.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by anatoli.anukevich on 4/19/2017.
 */
public class HelperBase {
    protected WebDriver wd;

    //Constructor
    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    protected void input(By locator, String value) {
        click(locator);
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(value);
    }

    protected boolean isSelected(By locator) {
        return wd.findElement(locator).isSelected();
    }

    protected void acceptAlert() {
        wd.switchTo().alert().accept();
    }
}
