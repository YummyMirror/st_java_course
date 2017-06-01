package ru.anatoli.mantis.appmanager;

import org.openqa.selenium.By;

/**
 * Created by anatoli.anukevich on 5/28/2017.
 */
public class RegistrationHelper extends HelperBase {
    public RegistrationHelper(ApplicationManager applicationManager) {
        super(applicationManager);
    }

    public void registrate(String username, String email) {
        wd.get("http://localhost/mantisbt-1.2.19/signup_page.php");
        input(By.name("username"), username);
        input(By.name("email"), email);
        click(By.cssSelector("input[value='Signup']"));
    }

    public void finish(String confirmationLink, String password) {
        applicationManager.getUrl(confirmationLink);
        input(By.name("password"), password);
        input(By.name("password_confirm"), password);
        click(By.cssSelector("input[value='Update User']"));
    }
}
