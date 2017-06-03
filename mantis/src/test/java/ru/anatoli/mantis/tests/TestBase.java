package ru.anatoli.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.anatoli.mantis.appmanager.ApplicationManager;

import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

/**
 * Created by anatoli.anukevich on 4/19/2017.
 */
public class TestBase {
    protected static final ApplicationManager applicationManager = new ApplicationManager(BrowserType.IE);

    @BeforeSuite
    public void setUp() throws Exception {
        applicationManager.init();
        applicationManager.ftpHelper().uploadFile("src/test/resources/config_inc.php", "config_inc.php", "config_inc.php.bak");
    }

    @AfterSuite
    public void tearDown() throws IOException {
        applicationManager.ftpHelper().restore("config_inc.php.bak", "config_inc.php");
        applicationManager.stop();
    }

    private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
        return new MantisConnectLocator()
                .getMantisConnectPort(new URL("http://localhost/mantisbt-123/api/soap/mantisconnect.php"));
    }

    private boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        //Connecting to the Mantis API
        MantisConnectPortType mantisConnect = getMantisConnect();

        //Getting issue by ID
        IssueData issueData = mantisConnect.mc_issue_get("administrator", "root", BigInteger.valueOf(issueId));

        //Getting NAME of issue's resolution
        String resolutionName = issueData.getResolution().getName();
        if (resolutionName.equals("new")) {
            return true;
        } else if (resolutionName.equals("fixed")){
            return false;
        } else {
            return true;
        }
    }

    public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        if (isIssueOpen(issueId)) {
            System.out.println("Dude, your test is ignored due to there is no bugfix exist yet");
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}
