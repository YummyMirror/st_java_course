package ru.anatoli.addressbook.generators;

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
        File file = new File(pathToFile, fileName);

        List<GroupData> listWithGroups = generateGroups(number);
        save(listWithGroups, file);
    }

    private static void save(List<GroupData> listWithGroups, File file) throws IOException {
        FileWriter writer = new FileWriter(file);
        int counter = 1;
        for (int i = 0; i < listWithGroups.size(); i++) {
            writer.write(String.format("%d. %s; %s; %s \n",
                                                            counter,
                                                            listWithGroups.get(i).getGroupName(),
                                                            listWithGroups.get(i).getGroupHeader(), listWithGroups.get(i).getGroupFooter()));
            counter++;
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
