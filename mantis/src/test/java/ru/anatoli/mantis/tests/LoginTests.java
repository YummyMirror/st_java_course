package ru.anatoli.mantis.tests;

import org.testng.annotations.Test;
import ru.anatoli.mantis.appmanager.HttpSessionHelper;
import java.io.IOException;
import static org.testng.Assert.assertTrue;

/**
 * Created by anatoli.anukevich on 5/27/2017.
 */
public class LoginTests extends TestBase {
    @Test
    public void loginTests() throws IOException {
        HttpSessionHelper session = applicationManager.session();
        boolean login = session.login("administrator", "root");
        assertTrue(login);

        boolean whoLoogedIn = session.isLoggedInAs("administrator");
        assertTrue(whoLoogedIn);
    }
}
