import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class DummyJsonClientImpl implements DummyJsonClient {
    public Response getUser(int id) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://dummyjson.com/users/" + id);
        CloseableHttpResponse executed = null;

        try {
            executed = httpClient.execute(httpGet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HttpEntity entity = executed.getEntity();
        try {
            String responseContent = EntityUtils.toString(entity);
            StatusLine statusLine = executed.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            Response response = new Response(statusCode, responseContent);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());

            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    // dummyjson.com/auth/login/
    public Response login(User u) {
        String requestBody = "{\"username\":\"" + u.username + "\",\"password\":\"" + u.password + "\"}";
        StringEntity requestEntity = null;
        try {
            requestEntity = new StringEntity(requestBody);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://dummyjson.com/auth/login");
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.setEntity(requestEntity);
        CloseableHttpResponse executed = null;
        try (httpClient) {
            executed = httpClient.execute(httpPost);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HttpEntity entity = executed.getEntity();
        try {
            String responseContent = EntityUtils.toString(entity);
            StatusLine statusLine = executed.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            Response response = new Response(statusCode, responseContent);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //dummyjson.com/auth/posts/{user.id}
    public Response getPosts(User user, Token token) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://dummyjson.com/auth/posts/" + user.id);
        httpGet.setHeader("Authorization", "Bearer " + token.getToken());
        CloseableHttpResponse executed = null;
        try {
            executed = httpClient.execute(httpGet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HttpEntity entity = executed.getEntity();
        try {
            String responseContent = EntityUtils.toString(entity);
            StatusLine statusLine = executed.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            Response response = new Response(statusCode, responseContent);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());

            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
