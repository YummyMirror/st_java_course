package ru.anatoli.test;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.NameValuePair;
import org.apache.http.client.fluent.Executor;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import static org.testng.Assert.*;
import org.apache.http.client.fluent.Request;

/**
 * Created by anatoli.anukevich on 6/4/2017.
 */
public class RestTests {
    @Test(enabled = true)
    public void createIssueTest() throws IOException {
        Set<Issue> before = getIssues();

        Issue issue = new Issue().withSubject("test subject 100").withDescription("test description 100");
        int id = createIssue(issue);

        before.add(issue.withId(id));
        Set<Issue> after = getIssues();

        assertEquals(before, after);
    }

    private Set<Issue> getIssues() throws IOException {
        String json = Executor.newInstance().auth("LSGjeU4yP1X493ud1hNniA==", "").execute(Request.Get("http://demo.bugify.com/api/issues.json"))
                                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    }

    private int createIssue(Issue issue) throws IOException {
        String json = Executor.newInstance().auth("LSGjeU4yP1X493ud1hNniA==", "").execute(Request.Post("http://demo.bugify.com/api/issues.json")
                .bodyForm(new BasicNameValuePair("subject", issue.getSubject()),
                          new BasicNameValuePair("description", issue.getDescription())))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        int issue_id = parsed.getAsJsonObject().get("issue_id").getAsInt();
        return issue_id;
    }
}
