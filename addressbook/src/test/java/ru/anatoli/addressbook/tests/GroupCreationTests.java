package ru.anatoli.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.GroupData;
import ru.anatoli.addressbook.models.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {
    @DataProvider
    public Iterator<Object[]> validGroupData() {
        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[] {new GroupData().withGroupName("name1").withGroupHeader("header1").withGroupFooter("footer1")});
        list.add(new Object[] {new GroupData().withGroupName("name2").withGroupHeader(null).withGroupFooter("footer2")});
        list.add(new Object[] {new GroupData().withGroupName("name3").withGroupHeader("header3").withGroupFooter(null)});
        return list.iterator();
    }

    @BeforeMethod
    public void ensurePrecondition() {
        applicationManager.getNavigationHelper().goToGroupPage();
    }

    @Test(dataProvider = "validGroupData")
    public void testGroupCreation(GroupData groupData) {
        //Set<GroupData> before = applicationManager.getGroupHelper().getGroupSet();
        Groups before = applicationManager.getGroupHelper().getGroupSet();  //remove after course

        applicationManager.getGroupHelper().createGroup(groupData);

        //Set<GroupData> after = applicationManager.getGroupHelper().getGroupSet();
        Groups after = applicationManager.getGroupHelper().getGroupSet();  //remove after course

            //Asserting by size of collections
        //assertEquals(after.size(), before.size() + 1);
        assertThat(after.size(), equalTo(before.size() + 1)); //remove after course

        //groupData.withId(after.stream().max((g1, g2) -> Integer.compare(g1.getId(), g2.getId())).get().getId());
        //before.add(groupData);

            //Asserting collections
        //assertEquals(before, after);
        assertThat(after, equalTo(before.withAdded(groupData.withId(
                                after.stream().max((g1, g2) -> Integer.compare(g1.getId(), g2.getId())).get().getId())))); //remove after course
    }
}
