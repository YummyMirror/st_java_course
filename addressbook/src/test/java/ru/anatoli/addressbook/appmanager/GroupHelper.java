package ru.anatoli.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.anatoli.addressbook.models.GroupData;
import ru.anatoli.addressbook.models.Groups;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public void selectGroup(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    private void selectGroupById(int id) {
        wd.findElement(By.cssSelector("input[value = '" + id + "']")).click();
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

    public boolean isGroupExist() {
        return isElementPresent(By.xpath("//*[@id='content']/form/span[1]/input"));
    }

    public int getGroupNumber() {
        int groupNumber = wd.findElements(By.name("selected[]")).size();
        return groupNumber;
    }

    //private Set<GroupData> groupCache = null;
    private Groups groupCache = null; //remove after course

    //public Set<GroupData> getGroupSet() {
    public Groups getGroupSet() { //remove after course
        if (groupCache != null) {
            //return new HashSet<GroupData>(groupCache);
            return new Groups(groupCache); //remove after course
        }
        //groupCache groups = new HashSet<GroupData>();
        groupCache = new Groups(); //remove after course
        List<WebElement> webElements = wd.findElements(By.cssSelector("span.group"));
        for (int i = 0; i < webElements.size(); i++) {
            int id = Integer.parseInt(webElements.get(i).findElement(By.tagName("input")).getAttribute("value"));
            String name = webElements.get(i).getText();
            GroupData group = new GroupData().withId(id)
                                            .withGroupName(name)
                                            .withGroupHeader(null)
                                            .withGroupFooter(null);
            groupCache.add(group);
        }
        //return new HashSet<GroupData>(groupCache);
        return new Groups(groupCache); //remove after course
    }

    public void modifyGroup(GroupData groupData) {
        selectGroupById(groupData.getId());
        initiateGroupModification();
        inputGroupData(groupData);
        submitGroupModification();
        groupCache = null;
        returnToGroupPage();
    }

    public void deleteGroup(GroupData deleteGroup) {
        selectGroupById(deleteGroup.getId());
        deleteSelectedGroup();
        groupCache = null;
        returnToGroupPage();
    }

    public void createGroup(GroupData groupData) {
        initiateGroupCreation();
        inputGroupData(groupData);
        submitGroupCreation();
        groupCache = null;
        returnToGroupPage();
    }
}
