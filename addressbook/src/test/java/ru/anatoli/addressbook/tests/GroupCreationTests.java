package ru.anatoli.addressbook.tests;

import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.GroupData;
import ru.anatoli.addressbook.models.Groups;

import java.util.Set;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {
    @Test
    public void testGroupCreation() {
        applicationManager.getNavigationHelper().goToGroupPage();
        //Set<GroupData> before = applicationManager.getGroupHelper().getGroupSet();
        Groups before = applicationManager.getGroupHelper().getGroupSet();  //remove after course

        GroupData groupData = new GroupData().withGroupName("group name1")
                                            .withGroupHeader(null)
                                            .withGroupFooter("group footer");
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
