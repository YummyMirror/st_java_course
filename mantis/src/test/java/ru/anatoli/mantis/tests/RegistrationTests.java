package ru.anatoli.mantis.tests;

import org.testng.annotations.Test;

/**
 * Created by anatoli.anukevich on 5/28/2017.
 */
public class RegistrationTests extends TestBase {
    @Test
    public void testRegistration() {
        applicationManager.registrationHelper().registrate("", "");
    }
}
