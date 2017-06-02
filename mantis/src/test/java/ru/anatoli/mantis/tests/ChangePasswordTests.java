package ru.anatoli.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.anatoli.mantis.models.MailMessage;
import ru.anatoli.mantis.models.UserData;
import ru.lanwen.verbalregex.VerbalExpression;
import java.util.List;
import java.util.Set;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by anatoli.anukevich on 6/1/2017.
 */
public class ChangePasswordTests extends TestBase {
    @BeforeMethod
    public void startMailServer() throws Exception{
        applicationManager.mailHelper().start();
    }

    @Test
    public void testChangePassword() throws Exception {
        String username = "user111";
        String newPassword = "password111";

        applicationManager.loginHelper().login("Administrator","root");
        applicationManager.navigationHelper().Users();

        UserData selectUser = applicationManager.dbHelper().getUsers().stream().filter((u) -> username.equals(u.getUsername())).iterator().next();
        applicationManager.navigationHelper().clickUser(selectUser.getId());
        applicationManager.navigationHelper().resetPassword();

        List<MailMessage> mailMessages = applicationManager.mailHelper().waitForMail(2,10000);
        String confirmationLink = findConfirmationLink(mailMessages, selectUser.getEmail());
        applicationManager.navigationHelper().changePassword(confirmationLink,newPassword);
        assertTrue(applicationManager.session().login(username,newPassword));
        assertTrue(applicationManager.session().isLoggedInAs(username));

    }


    @AfterMethod(alwaysRun = true)
    public void stopMailServer() throws Exception {
        applicationManager.mailHelper().stop();
    }


    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m)->m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return  regex.getText(mailMessage.text);
    }
}
