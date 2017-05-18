package ru.anatoli.addressbook.tests;

import org.testng.annotations.Test;
import ru.anatoli.addressbook.models.GroupData;
import ru.anatoli.addressbook.models.Groups;

import java.sql.*;

/**
 * Created by anatoli.anukevich on 5/18/2017.
 */
public class DBConnectionTest {
    @Test
    public void testDbConnetion() {
        //https://dev.mysql.com/doc/connector-j/5.1/en/connector-j-usagenotes-connect-drivermanager.html
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?user=root&password=");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT group_id, group_name, group_header, group_footer FROM group_list");
            Groups groups = new Groups();
            while (resultSet.next()) {
                groups.add(new GroupData().withId(resultSet.getInt("group_id"))
                                            .withGroupName(resultSet.getString("group_name"))
                                            .withGroupHeader(resultSet.getString("group_header"))
                                            .withGroupFooter(resultSet.getString("group_footer")));
            }
            resultSet.close();
            statement.close();
            connection.close();
            System.out.println(groups);
        } catch (SQLException ex) {
            //Handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
