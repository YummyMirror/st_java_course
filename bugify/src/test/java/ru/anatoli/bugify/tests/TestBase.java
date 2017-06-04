package ru.anatoli.bugify.tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;
import ru.anatoli.bugify.appmanager.ApplicationManager;
import ru.anatoli.bugify.models.Issue;
import java.io.IOException;
import java.util.List;

/**
 * Created by anatoli.anukevich on 4/19/2017.
 */
public class TestBase {
    protected ApplicationManager applicationManager = new ApplicationManager();

    private boolean isIssueOpen(int issueId) throws IOException {
        String json = Executor.newInstance().auth("LSGjeU4yP1X493ud1hNniA==", "").execute(Request.Get("http://demo.bugify.com/api/issues/" + issueId + ".json"))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        List<Issue> listWithOneObject = new Gson().fromJson(issues, new TypeToken<List<Issue>>() {}.getType());
        String state_name = listWithOneObject.get(0).getState_name();

        if (state_name.equals("Resolved")) {
            return false;
        } else if (state_name.equals("Closed")) {
            return false;
        } else {
            return true;
        }
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            System.out.println("Dude, test is ignored due to the there is no bugfix exist yet");
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}
