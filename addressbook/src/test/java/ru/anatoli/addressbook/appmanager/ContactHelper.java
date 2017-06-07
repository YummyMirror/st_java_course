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
import ru.anatoli.addressbook.models.GroupData;

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
        attach(By.name("photo"), contactData.getPhoto());
        input(By.name("title"), contactData.getTitle());
        input(By.name("address"), contactData.getAddress());
        input(By.name("home"), contactData.getHomePhone());
        input(By.name("mobile"), contactData.getMobilePhone());
        input(By.name("work"), contactData.getWorkPhone());
        input(By.name("email"), contactData.getEmail());
        input(By.name("email2"), contactData.getEmail2());
        input(By.name("email3"), contactData.getEmail3());

//        if (creation) {
//            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
//        }else {
//            Assert.assertFalse(isElementPresent(By.name("new_group")));
//        }
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    private void selectContactById(int id) {
        click(By.xpath("//input[@id='" + id + "']"));
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
        wd.findElement(By.xpath("//a[@href='edit.php?id=" + id + "']")).click();
        //wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
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
        contactCache = null;
        returnToHomePage();
    }

    public void removeContact(ContactData contactData) {
        selectContactById(contactData.getId());
        deleteSelectedContact();
        confirmDeleteContact();
        contactCache = null;
        goToHomePage();
    }

    public void modifyContact(ContactData contactData) {
        initiateContactModificationById(contactData.getId());
        inputContactData(contactData, false);
        submitContactModification();
        contactCache = null;
        returnToHomePage();
    }

    //private Set<ContactData> contactCache = null;
    private Contacts contactCache = null; //remove after course

    //public Set<ContactData> getContactSet() {
    public Contacts getContactSet() { //remove after course
        if (contactCache != null) {
            //return new HashSet<ContactData>(contactCache);
            return new Contacts(contactCache);  //remove after course
        }
        //contactCache contacts = new HashSet<ContactData>();
        contactCache = new Contacts(); //remove after course
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (int i = 0; i < rows.size(); i++) {
            List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("id"));
            String firstName = cells.get(2).getText();
            String lastName = cells.get(1).getText();

            String phones = cells.get(5).getText();
            String phone[] = phones.split("\n");

            String emails = cells.get(4).getText();
            String email[] = emails.split("\n");

            String address = cells.get(3).getText();

            ContactData contact = new ContactData().withId(id)
                                                    .withFirstName(firstName)
                                                    .withMiddleName(null)
                                                    .withLastName(lastName)
                                                    .withNickname(null)
                                                    .withTitle(null)
                                                    .withAddress(address)
                                                    .withHomePhone(phone[0])
                                                    .withMobilePhone(phone[1])
                                                    .withWorkPhone(phone[2])
                                                    .withEmail(email[0])
                                                    .withEmail2(email[1])
                                                    .withEmail3(email[2])
                                                    .withGroup(null);
            contactCache.add(contact);
        }
        //return new HashSet<ContactData>(contactCache);
        return new Contacts(contactCache);  //remove after course
    }

    public ContactData infoFromEditForm(ContactData randObject) {
        initiateContactModificationById(randObject.getId());
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        //String middleName = wd.findElement(By.name("middlename")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String homePhone = wd.findElement(By.name("home")).getAttribute("value");
        String mobilePhone = wd.findElement(By.name("mobile")).getAttribute("value");
        String workPhone = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getText();

        ContactData contactData = new ContactData().withFirstName(firstName)
                                                    //.withMiddleName(middleName)
                                                    .withLastName(lastName)
                                                    .withHomePhone(homePhone)
                                                    .withMobilePhone(mobilePhone)
                                                    .withWorkPhone(workPhone)
                                                    .withEmail(email)
                                                    .withEmail2(email2)
                                                    .withEmail3(email3)
                                                    .withAddress(address);
        wd.navigate().back();
        return contactData;
    }

    public String infoFromViewForm(ContactData contactData){
        infoContactById(contactData.getId());
        String content = wd.findElement(By.id("content")).getText();
        return content;
    }

    public void infoContactById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='view.php?id=%s']", id))).click();
    }

    private void groupChoose(GroupData group) {
        new Select(wd.findElement(By.name("to_group"))).selectByValue(Integer.toString(group.getId()));
    }

    private void addGroups() {
        click(By.name("add"));
    }

    public void addContactGroups(ContactData contact, GroupData group) {
        selectContactById(contact.getId());
        groupChoose(group);
        addGroups();
    }

    public void selectGroupBeforeDelete(GroupData groupData){
        new Select(wd.findElement(By.name("group"))).selectByVisibleText(groupData.getGroupName());
    }

    public void removeSelectedContactFromGroup(ContactData contactData) {
        selectContactById(contactData.getId());
        deleteContactFromGroup();
    }

    private void deleteContactFromGroup() {
        click(By.name("remove"));
    }
}