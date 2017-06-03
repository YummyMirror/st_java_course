package ru.anatoli.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.anatoli.mantis.models.Issue;
import ru.anatoli.mantis.models.Project;
import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;
import static org.testng.Assert.assertEquals;

/**
 * Created by anatoli.anukevich on 6/3/2017.
 */
public class SoapTests extends TestBase {
    @Test
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = applicationManager.soapHelper().getProjects();
        System.out.println(projects.size());
        for (Project pr : projects) {
            System.out.println(projects);
        }
    }

    @Test
    public void createIssue() throws RemoteException, ServiceException, MalformedURLException {
        Set<Project> projects = applicationManager.soapHelper().getProjects();
        Issue issue = new Issue().withSummary("test summary")
                                    .withDescription("test description")
                                    .withProject(projects.iterator().next());
        Issue created = applicationManager.soapHelper().createIssue(issue);
        assertEquals(issue.getSummary(), created.getSummary());
    }
}
