package ru.anatoli.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.anatoli.addressbook.models.UserData;

/**
 * Created by anatoli.anukevich on 4/19/2017.
 */
public class SessionHelper extends HelperBase {
    //Constructor
    public SessionHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void login(UserData userData) {
        input(By.name("user"), userData.getUserName());
        input(By.name("pass"), userData.getPassword());
        click(By.xpath("//form[@id='LoginForm']/input[3]"));
    }
}
