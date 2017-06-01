package ru.anatoli.mantis.appmanager;

import org.openqa.selenium.By;

/**
 * Created by anatoli.anukevich on 6/1/2017.
 */
public class NavigationHelper extends HelperBase {
    public NavigationHelper(ApplicationManager applicationManager) {
        super(applicationManager);
    }

    public void Users(){
        click(By.cssSelector("a[href='/mantisbt-1.2.19/manage_user_page.php']"));
    }

    public void clickUser(int id){
        click(By.cssSelector("a[href='manage_user_edit_page.php?user_id=" + id + "']"));
    }
    
    public void resetPassword(){
        click(By.cssSelector("input[value='Reset Password']"));
    }

    public void changePassword(String confirmationLink, String password) {
        wd.get(confirmationLink);
        input(By.name("password"),password);
        input(By.name("password_confirm"),password);
        click(By.cssSelector("input[value='UpdateUser']"));
    }
}
