package ru.anatoli.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.anatoli.addressbook.models.GroupData;

/**
 * Created by anatoli.anukevich on 4/19/2017.
 */
public class GroupHelper extends HelperBase {
    //Constructor
    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void inputGroupData(GroupData groupData) {
        input(By.name("group_name"), groupData.getGroupName());
        input(By.name("group_header"), groupData.getGroupHeader());
        input(By.name("group_footer"), groupData.getGroupFooter());
    }

    public void initiateGroupCreation() {
        click(By.name("new"));
    }

    public void selectFirstGroup() {
        click(By.xpath("//*[@id='content']/form/span[1]/input"));
    }

    public void deleteSelectedGroup() {
        click(By.name("delete"));
    }

    public void initiateGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModification() {
        click(By.name("update"));
    }
}
