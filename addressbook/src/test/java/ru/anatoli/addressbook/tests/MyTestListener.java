package ru.anatoli.addressbook.tests;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import ru.anatoli.addressbook.appmanager.ApplicationManager;
import ru.yandex.qatools.allure.annotations.Attachment;

/**
 * Created by anatoli.anukevich on 6/20/2017.
 */
public class MyTestListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ApplicationManager applicationManager = (ApplicationManager) result.getTestContext().getAttribute("applicationManager");
        saveScreenshot(applicationManager.takeScreenshot());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ApplicationManager applicationManager = (ApplicationManager) result.getTestContext().getAttribute("applicationManager");
        saveScreenshot(applicationManager.takeScreenshot());
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ApplicationManager applicationManager = (ApplicationManager) result.getTestContext().getAttribute("applicationManager");
        saveScreenshot(applicationManager.takeScreenshot());
    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }
}
