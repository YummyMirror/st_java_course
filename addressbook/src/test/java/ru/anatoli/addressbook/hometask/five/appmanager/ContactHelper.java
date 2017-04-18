package ru.anatoli.addressbook.hometask.five.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.anatoli.addressbook.hometask.five.models.ContactData;

/**
 * Created by anatoli.anukevich on 4/18/2017.
 */
public class ContactHelper extends HelperBase {
    //Constructor
    public ContactHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void submitContactForm() {
        click(By.name("submit"));
    }

    public void inputContactData(ContactData contactData) {
        input(By.name("firstname"), contactData.getFirstname());
        input(By.name("middlename"), contactData.getMiddlename());
        input(By.name("lastname"), contactData.getLastname());
        input(By.name("nickname"), contactData.getNickname());
        input(By.name("title"), contactData.getTitle());
        input(By.name("company"), contactData.getCompany());
        input(By.name("address"), contactData.getAddress());
    }

    public void initiateContactCreation() {
        click(By.linkText("add new"));
    }
}
