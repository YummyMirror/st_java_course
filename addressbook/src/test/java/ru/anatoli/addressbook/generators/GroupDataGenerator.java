package ru.anatoli.addressbook.generators;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.anatoli.addressbook.models.GroupData;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anatoli.anukevich on 5/13/2017.
 */
public class GroupDataGenerator {
    public static void main(String[] args) throws IOException {
        int number = Integer.parseInt(args[0]);
        String pathToFile = args[1];
        String fileName = args[2];
        String format = args[3];
        File file = new File(pathToFile, fileName);

        List<GroupData> listWithGroups = generateGroups(number);
        if (format.equals("csv")) {
            saveAsCsv(listWithGroups, file);
        }else if (format.equals("json")) {
            saveAsJson(listWithGroups, file);
        }else {
            System.out.println("Input wrong format " + format);
        }
    }

    private static void saveAsJson(List<GroupData> listWithGroups, File file) throws IOException {
        FileWriter writer = new FileWriter(file);
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(listWithGroups);
        writer.write(json);
        writer.flush();
        writer.close();
    }

    private static void saveAsCsv(List<GroupData> listWithGroups, File file) throws IOException {
        FileWriter writer = new FileWriter(file);
        for (int i = 0; i < listWithGroups.size(); i++) {
            writer.write(String.format("%s; %s; %s \n",
                                                            listWithGroups.get(i).getGroupName(),
                                                            listWithGroups.get(i).getGroupHeader(),
                                                            listWithGroups.get(i).getGroupFooter()));
        }
        writer.flush();
        writer.close();
    }

    private static List<GroupData> generateGroups(int number) {
        List<GroupData> groups = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            groups.add(new GroupData().withGroupName(String.format("groupName %s", i))
                                        .withGroupHeader(String.format("groupHeader %s", i))
                                        .withGroupFooter(String.format("groupFooter %s", i)));
        }
        return groups;
    }
}
