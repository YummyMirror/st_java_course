package ru.anatoli.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.io.File;

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
        if (value != null) {
            String existingValue = wd.findElement(locator).getAttribute("value");
            if (! value.equals(existingValue)) {
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(value);
            }
        }
    }

    protected void attach(By locator, File file) {
        if (file != null) {
            wd.findElement(locator).sendKeys(file.getAbsolutePath());
        }
    }

    protected boolean isSelected(By locator) {
        return wd.findElement(locator).isSelected();
    }

    protected void acceptAlert() {
        wd.switchTo().alert().accept();
    }

    protected boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        }catch (NoSuchElementException ex) {
            return  false;
        }
    }
}
