package ru.anatoli.addressbook.hometask.five.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.anatoli.addressbook.hometask.five.models.GroupData;

/**
 * Created by anatoli.anukevich on 4/18/2017.
 */
public class GroupHelper extends HelperBase {
    //Constructor
    public GroupHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void submitGroupForm() {
        click(By.name("submit"));
    }

    public void inputGroupData(GroupData groupData) {
        click(By.name("group_name"));
        wd.findElement(By.name("group_name")).clear();
        wd.findElement(By.name("group_name")).sendKeys(groupData.getGroupName());
        click(By.name("group_header"));
        wd.findElement(By.name("group_header")).clear();
        wd.findElement(By.name("group_header")).sendKeys(groupData.getGroupHeader());
        click(By.name("group_footer"));
        wd.findElement(By.name("group_footer")).clear();
        wd.findElement(By.name("group_footer")).sendKeys(groupData.getGroupFooter());
    }

    public void initiateGroupCreation() {
        click(By.name("new"));
    }

    public void deleteSelectedGroup() {
        click(By.xpath("//div[@id='content']/form/input[5]"));
    }

    public void selectGroup() {
        click(By.xpath("//*[@id='content']/form/span[1]/input"));
    }
}
