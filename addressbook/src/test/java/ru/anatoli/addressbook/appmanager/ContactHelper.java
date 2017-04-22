package ru.anatoli.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.anatoli.addressbook.models.ContactData;

/**
 * Created by anatoli.anukevich on 4/19/2017.
 */
public class ContactHelper extends HelperBase {
    //Constructor
    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void inputContactData(ContactData contactData) {
        input(By.name("firstname"), contactData.getFirstName());
        input(By.name("middlename"), contactData.getMiddleName());
        input(By.name("lastname"), contactData.getLastName());
        input(By.name("nickname"), contactData.getNickname());
        input(By.name("title"), contactData.getTitle());
    }

    public void selectFirstContact() {
        if (!isSelected(By.name("selected[]"))) {
            click(By.name("selected[]"));
        }
    }

    public void deleteSelectedContact() {
        click(By.xpath("//*[@id='content']/form[2]/div[2]/input"));
    }

    public void confirmDeleteContact() {
        acceptAlert();
    }

    public void initiateContactModification() {
        click(By.xpath("//*[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }
}
