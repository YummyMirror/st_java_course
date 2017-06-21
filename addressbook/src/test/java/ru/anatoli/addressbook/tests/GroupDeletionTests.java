package ru.anatoli.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.GroupData;
import ru.anatoli.addressbook.models.Groups;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by anatoli.anukevich on 4/19/2017.
 */
public class GroupDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        if (applicationManager.getDbHelper().getGroups().size() == 0) {
            applicationManager.getNavigationHelper().goToGroupPage();
            GroupData groupData = new GroupData().withGroupName("temp group name")
                                                    .withGroupHeader("temp group header")
                                                    .withGroupFooter("temp group footer");
            applicationManager.getGroupHelper().createGroup(groupData);
        }
        /*
        applicationManager.getNavigationHelper().goToGroupPage();
        //If there is no one group exist
        if (! applicationManager.getGroupHelper().isGroupExist()) {
            GroupData groupData = new GroupData().withGroupName("temp group name")
                                                    .withGroupHeader("temp group header")
                                                    .withGroupFooter("temp group footer");
            applicationManager.getGroupHelper().createGroup(groupData);
        }
        */
    }

    @Test
    public void testGroupDeletion() {
        //Set<GroupData> before = applicationManager.getGroupHelper().getGroupSet();
        //Groups before = applicationManager.getGroupHelper().getGroupSet(); //remove after course
        Groups before = applicationManager.getDbHelper().getGroups();

            //Random element
        GroupData deleteGroup = before.iterator().next();
        applicationManager.getNavigationHelper().goToGroupPage();
        applicationManager.getGroupHelper().deleteGroup(deleteGroup);

        //Set<GroupData> after = applicationManager.getGroupHelper().getGroupSet();
        //Groups after = applicationManager.getGroupHelper().getGroupSet(); //remove after course
        Groups after = applicationManager.getDbHelper().getGroups();

            //Asserting by size of collections
        //assertEquals(after.size(), before.size() - 1);
        //assertThat(after.size(), equalTo(before.size() - 1)); //remove after course

        //before.remove(deleteGroup);

            //Asserting by collections
        //assertEquals(before, after);
        assertThat(after, equalTo(before.without(deleteGroup))); //remove after course
        compareBDvsUIdataGroups();
    }

    @Test
    public void login() {
        applicationManager.getNavigationHelper().goToGroupPage();
        boolean login = applicationManager.getGroupHelper().login();
        Assert.assertTrue(login);
    }
}
