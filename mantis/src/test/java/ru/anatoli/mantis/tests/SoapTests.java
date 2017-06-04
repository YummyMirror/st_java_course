package ru.anatoli.mantis.tests;

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
    @Test(enabled = true)
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
        //Checking if bug is fixed
        skipIfNotFixed(1);

        Set<Project> projects = applicationManager.soapHelper().getProjects();
        System.out.println(projects.size());
        for (Project project : projects) {
            System.out.println(project.getName());
        }
    }

    @Test(enabled = true)
    public void createIssue() throws RemoteException, ServiceException, MalformedURLException {
        //Checking if bug is fixed
        skipIfNotFixed(5);

        Set<Project> projects = applicationManager.soapHelper().getProjects();
        Project randomProject = projects.iterator().next();
        Issue issue = new Issue().withSummary("bugify summary5")
                                    .withDescription("bugify description5")
                                    .withProject(randomProject);
        Issue created = applicationManager.soapHelper().createIssue(issue);
        assertEquals(issue.getSummary(), created.getSummary());
    }
}
