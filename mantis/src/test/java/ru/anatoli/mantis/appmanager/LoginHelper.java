package ru.anatoli.mantis.appmanager;

import org.openqa.selenium.By;

/**
 * Created by anatoli.anukevich on 6/1/2017.
 */
public class LoginHelper extends HelperBase {
    public LoginHelper(ApplicationManager applicationManager) {
            super(applicationManager);
        }

    public void login(String username, String password){
        wd.get("http://localhost/mantisbt-1.2.19/login_page.php");
        input(By.name("username"), username);
        input(By.name("password"), password);
        click(By.cssSelector("input[value='Login']"));
    }
}