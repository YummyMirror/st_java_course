package ru.anatoli.bugify.tests;

import org.testng.annotations.Test;
import ru.anatoli.bugify.models.Issue;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

/**
 * Created by anatoli.anukevich on 6/4/2017.
 */
public class RestTests extends TestBase {
    @Test(enabled = true)
    public void createIssueTest() throws IOException {
        //Checking if bug is fixed by ID
        skipIfNotFixed(7);

        Set<Issue> before = applicationManager.getApiHelper().getIssues();

        Issue issue = new Issue().withSubject("bugify subject 100")
                                 .withDescription("bugify description 100");
        int id = applicationManager.getApiHelper().createIssue(issue);

        before.add(issue.withId(id));
        Set<Issue> after = applicationManager.getApiHelper().getIssues();

        assertEquals(before, after);
    }
}
