package ru.anatoli.mantis.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.anatoli.mantis.models.MailMessage;
import ru.lanwen.verbalregex.VerbalExpression;

import java.io.IOException;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

/**
 * Created by anatoli.anukevich on 5/28/2017.
 */
public class RegistrationTests extends TestBase {
    @BeforeMethod
    public void startMailServer() {
        applicationManager.mailHelper().start();
    }

    @Test
    public void testRegistration() throws IOException {
        long now = System.currentTimeMillis();
        String username = String.format("user%s", now);
        String password = "password";
        String email = String.format("user%s@localhost.localdomain", now);

        applicationManager.registrationHelper().registrate(username, email);
        List<MailMessage> mailMessages = applicationManager.mailHelper().waitForMail(2, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        applicationManager.registrationHelper().finish(confirmationLink, password);
        assertTrue(applicationManager.session().login(username, password));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @BeforeMethod(alwaysRun = true)
    public void stopMailServer() {
        applicationManager.mailHelper().stop();
    }
}
