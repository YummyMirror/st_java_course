package ru.anatoli.bugify.appmanager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import ru.anatoli.bugify.models.Issue;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by anatoli.anukevich on 6/4/2017.
 */
public class ApiHelper {
    private ApplicationManager applicationManager;

    public ApiHelper(ApplicationManager applicationManager) {
        this.applicationManager = applicationManager;
    }

    public Set<Issue> getIssues() throws IOException {
        String json = Executor.newInstance().auth("LSGjeU4yP1X493ud1hNniA==", "").execute(Request.Get("http://demo.bugify.com/api/issues.json"))
                                                                                 .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    }

    public int createIssue(Issue issue) throws IOException {
        String json = Executor.newInstance().auth("LSGjeU4yP1X493ud1hNniA==", "").execute(Request.Post("http://demo.bugify.com/api/issues.json")
                .bodyForm(new BasicNameValuePair("subject", issue.getSubject()),
                          new BasicNameValuePair("description", issue.getDescription())))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        int issue_id = parsed.getAsJsonObject().get("issue_id").getAsInt();
        return issue_id;
    }
}
