package ru.anatoli.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.anatoli.addressbook.models.ContactData;
import ru.anatoli.addressbook.models.Contacts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public void goToHomePage() {
            click(By.xpath("//*[@id='nav']/ul/li[1]/a"));
    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void inputContactData(ContactData contactData, boolean creation) {
        input(By.name("firstname"), contactData.getFirstName());
        input(By.name("middlename"), contactData.getMiddleName());
        input(By.name("lastname"), contactData.getLastName());
        input(By.name("nickname"), contactData.getNickname());
        input(By.name("title"), contactData.getTitle());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        }else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    private void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[id = '" + id + "']")).click();
    }

    public void deleteSelectedContact() {
        click(By.xpath("//*[@id='content']/form[2]/div[2]/input"));
    }

    public void confirmDeleteContact() {
        acceptAlert();
    }

    public void initiateContactModification(int index) {
        wd.findElements(By.className("Edit")).get(index).click();
        //wd.findElements(By.cssSelector("td.center > a")).get(index).click();
    }

    private void initiateContactModificationById(int id) {
        wd.findElement(By.xpath("//a[@href='edit.php?id = " + id + "']")).click();
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public boolean isContactExist() {
        return isElementPresent(By.name("selected[]"));
    }

    public void initiateContactCreation() {
        click(By.linkText("add new"));
    }

    public void createContact(ContactData contactData) {
        initiateContactCreation();
        inputContactData(contactData, true);
        submitContactCreation();
        returnToHomePage();
    }

    public void removeContact(int index) {
        selectContact(index);
        deleteSelectedContact();
        confirmDeleteContact();
        goToHomePage();
    }

    public void removeContact(ContactData contactData) {
        selectContactById(contactData.getId());
        deleteSelectedContact();
        confirmDeleteContact();
        goToHomePage();
    }

    public void modifyContact(ContactData contactData, int index) {
        initiateContactModification(index);
        inputContactData(contactData, false);
        submitContactModification();
        returnToHomePage();
    }

    public void modifyContact(ContactData contactData) {
        initiateContactModificationById(contactData.getId());
        inputContactData(contactData, false);
        submitContactModification();
        returnToHomePage();
    }

    public int getContactNumber() {
        int contactNumber = wd.findElements(By.name("selected[]")).size();
        return contactNumber;
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> webElements = wd.findElements(By.name("entry"));
        for (int i = 0; i < webElements.size(); i++) {
            //String name = webElements.get(i).getText();
            String firstName = webElements.get(i).findElement(By.xpath("//td[3]")).getText();
            String lastName = webElements.get(i).findElement(By.xpath("//td[2]")).getText();
            ContactData contact = new ContactData().withFirstName(firstName)
                                                    .withMiddleName(null)
                                                    .withLastName(lastName)
                                                    .withNickname(null)
                                                    .withTitle(null)
                                                    .withGroup(null);
            contacts.add(contact);
        }
        return contacts;
    }

    //public Set<ContactData> getContactSet() {
    public Contacts getContactSet() { //remove after course
        //Set<ContactData> contacts = new HashSet<ContactData>();
        Contacts contacts = new Contacts(); //remove after course
        List<WebElement> webElements = wd.findElements(By.name("entry"));
        for (int i = 0; i < webElements.size(); i++) {
            //String name = webElements.get(i).getText();
            int id = Integer.parseInt(webElements.get(i).findElement(By.tagName("input")).getAttribute("id"));
            //String firstName = webElements.get(i).findElement(By.xpath("//td[3]")).getText();
            String firstName = webElements.get(i).findElement(By.cssSelector("td:nth-child(3)")).getText();
            //String lastName = webElements.get(i).findElement(By.xpath("//td[2]")).getText();
            String lastName = webElements.get(i).findElement(By.cssSelector("td:nth-child(2)")).getText();
            ContactData contact = new ContactData().withId(id)
                                                    .withFirstName(firstName)
                                                    .withMiddleName(null)
                                                    .withLastName(lastName)
                                                    .withNickname(null)
                                                    .withTitle(null)
                                                    .withGroup(null);
            contacts.add(contact);
        }
        return contacts;
    }
}