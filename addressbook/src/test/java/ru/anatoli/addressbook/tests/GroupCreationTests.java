package ru.anatoli.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.GroupData;
import ru.anatoli.addressbook.models.Groups;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {
    @DataProvider
    public Iterator<Object[]> validGroupDataFromCsv() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        File file = new File("src/test/resources/groupFile.csv");
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = bufferedReader.readLine();
        while (line != null) {
            //line.replaceAll("\\s", "");
            String splitData[] = line.split(";");
            list.add(new Object[] {new GroupData().withGroupName(splitData[0]).withGroupHeader(splitData[1]).withGroupFooter(splitData[2])});
            line = bufferedReader.readLine();
        }
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> validGroupDataFromJson() throws IOException {
        File file = new File("src/test/resources/groupFile.json");
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = bufferedReader.readLine();
        String json = "";
        while (line != null) {
            json += line;
            line = bufferedReader.readLine();
        }
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<GroupData>>(){}.getType(); //List<GroupData>.class
        List<GroupData> groups = gson.fromJson(json, collectionType);
        return groups.stream().map((g) -> new Object[] {g}).iterator();
    }

    @BeforeMethod
    public void ensurePrecondition() {
        applicationManager.getNavigationHelper().goToGroupPage();
    }

    @Test(dataProvider = "validGroupDataFromJson")
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
