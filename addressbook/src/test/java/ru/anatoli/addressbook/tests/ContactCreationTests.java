package ru.anatoli.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.ContactData;
import ru.anatoli.addressbook.models.Contacts;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {
    @DataProvider
    public Iterator<Object[]> validContactDataFromCsv() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        File file = new File("src/test/resources/", "contactFile.csv");
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = bufferedReader.readLine();
        while (line != null) {
            String splitData[] = line.split(";");
            list.add(new Object[] {new ContactData().withFirstName(splitData[0])
                                        .withMiddleName(splitData[1])
                                        .withLastName(splitData[2])
                                        .withNickname(splitData[3])
                                        //.withPhoto(new File(splitData[4]))
                                        .withTitle(splitData[4])
                                        .withAddress(splitData[5])
                                        .withHomePhone(splitData[6])
                                        .withMobilePhone(splitData[7])
                                        .withWorkPhone(splitData[8])
                                        .withEmail(splitData[9])
                                        .withEmail2(splitData[10])
                                        .withEmail3(splitData[11])
                                        .withGroup("aaa")});
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        reader.close();
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> validContactDataFromJson() throws IOException {
        File file = new File("src/test/resources/", "contactFile.json");
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = bufferedReader.readLine();
        String json = "";
        while (line != null) {
            json += line;
            line = bufferedReader.readLine();
        }
        Gson gson = new Gson(); //https://github.com/google/gson/blob/master/UserGuide.md#TOC-Object-Examples
        Type collectionType = new TypeToken<List<ContactData>>(){}.getType();
        List<ContactData> contacts =  gson.fromJson(json, collectionType);
        bufferedReader.close();
        reader.close();
        return contacts.stream().map((c) -> new Object[] {c}).iterator();
    }

    @BeforeMethod
    public void ensurePrecondition() {
        applicationManager.getNavigationHelper().goToHomePage();
    }

    @Test(dataProvider = "validContactDataFromJson")
    public void testContactCreation(ContactData contactData) {
        //Set<ContactData> before = applicationManager.getContactHelper().getContactSet();
        //Contacts before = applicationManager.getContactHelper().getContactSet();  //remove after course
        Contacts before = applicationManager.getDbHelper().getContacts();

        applicationManager.getContactHelper().createContact(contactData);

        //Set<ContactData> after = applicationManager.getContactHelper().getContactSet();
        //Contacts after = applicationManager.getContactHelper().getContactSet(); //remove after course
        Contacts after = applicationManager.getDbHelper().getContacts();

            //Asserting by size of collections
        //assertEquals(after.size(), before.size() + 1);
        //assertThat(after.size(), equalTo(before.size() + 1));  //remove after course

        //contactData.withId(after.stream().max((c1, c2) -> Integer.compare(c1.getId(), c2.getId())).get().getId());
        //before.add(contactData);

            //Asserting by sorted collections
        //assertEquals(before, after);
        assertThat(after, equalTo(before.withAdded(
                        contactData.withId(after.stream().max((c1, c2) -> Integer.compare(c1.getId(), c2.getId())).get().getId()))));  //remove after course
        compareBDvsUIdataContacts();
    }
}
