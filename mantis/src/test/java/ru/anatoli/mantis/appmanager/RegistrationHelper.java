package ru.anatoli.mantis.appmanager;

import org.openqa.selenium.WebDriver;

/**
 * Created by anatoli.anukevich on 5/28/2017.
 */
public class RegistrationHelper {
    private ApplicationManager applicationManager;
    private WebDriver wd;

    public RegistrationHelper(ApplicationManager applicationManager) {
        this.applicationManager = applicationManager;
        this.wd = applicationManager.getDriver();
    }

    public void registrate(String username, String email) {
        wd.get("http://localhost/mantisbt-1.2.19/signup_page.php");
    }
}
