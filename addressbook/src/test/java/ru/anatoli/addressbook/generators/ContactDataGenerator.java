package ru.anatoli.addressbook.generators;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.anatoli.addressbook.models.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anatoli.anukevich on 5/14/2017.
 */
public class ContactDataGenerator {
    public static void main(String[] args) throws IOException {
        int number = Integer.parseInt(args[0]);
        String pathToFile = args[1];
        String fileName = args[2];
        String format = args[3];
        File file = new File(pathToFile, fileName);

        List<ContactData> listWithContacts =  generateContacts(number);
        if (format.equals("csv")) {
            saveAsCsv(listWithContacts, file);
        }else if (format.equals("json")) {
            saveAsJson(listWithContacts, file);
        }else {
            System.out.println("Input wrong format " + format);
        }
    }

    private static void saveAsJson(List<ContactData> listWithContacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json =  gson.toJson(listWithContacts);
        FileWriter writer = new FileWriter(file);
        writer.write(json);
        writer.flush();
        writer.close();
    }

    private static void saveAsCsv(List<ContactData> listWithContacts, File file) throws IOException {
        FileWriter writer = new FileWriter(file);
        for (int i = 0; i < listWithContacts.size(); i++) {
            writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s \n",
                                                                            listWithContacts.get(i).getFirstName(),
                                                                            listWithContacts.get(i).getMiddleName(),
                                                                            listWithContacts.get(i).getLastName(),
                                                                            listWithContacts.get(i).getPhoto(),
                                                                            listWithContacts.get(i).getTitle(),
                                                                            listWithContacts.get(i).getAddress(),
                                                                            listWithContacts.get(i).getHomePhone(),
                                                                            listWithContacts.get(i).getMobilePhone(),
                                                                            listWithContacts.get(i).getWorkPhone(),
                                                                            listWithContacts.get(i).getEmail(),
                                                                            listWithContacts.get(i).getEmail2(),
                                                                            listWithContacts.get(i).getEmail3()));
        }
        writer.flush();
        writer.close();
    }

    private static List<ContactData> generateContacts(int number) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < number; i++) {
            contacts.add(new ContactData().withFirstName(String.format("firstName %s", i))
                                            .withMiddleName(String.format("middleName %s", i))
                                            .withLastName(String.format("lastName %s", i))
                                            .withPhoto(new File("src/test/resources/", "image.png"))
                                            .withTitle(String.format("title %s", i))
                                            .withAddress(String.format("address %s", i))
                                            .withHomePhone("111")
                                            .withMobilePhone("222")
                                            .withWorkPhone("333")
                                            .withEmail("1@mail.ru")
                                            .withEmail2("2@mail.ru")
                                            .withEmail3("3@mail.ru"));
        }
        return contacts;
    }
}
