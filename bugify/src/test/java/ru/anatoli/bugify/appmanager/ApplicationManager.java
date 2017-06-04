package ru.anatoli.bugify.appmanager;

/**
 * Created by anatoli.anukevich on 4/19/2017.
 */
public class ApplicationManager {
    private ApiHelper apiHelper;

    public ApiHelper getApiHelper() {
        if (apiHelper == null) {
            apiHelper = new ApiHelper(this);
        }
        return apiHelper;
    }
}
