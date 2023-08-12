import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DummyJsonClientTest {
    int id = 1;
    DummyJsonClientImpl client = new DummyJsonClientImpl();
    User userFromResponse = DummyJsonMapper.getUser(client.getUser(id));
    Token tokenFromResponse = DummyJsonMapper.getToken(client.login(userFromResponse));
    String expectedPostTitle = "His mother had always taught him";
    public DummyJsonClientTest() throws JsonProcessingException {
    }

    @Test
    void getUser() throws JsonProcessingException {
        Response response = client.getUser(id);
        User user = DummyJsonMapper.getUser(response);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertEquals("atuny0", user.getUsername());
        Assertions.assertEquals(id, user.getId());
        Assertions.assertEquals("9uQFF1Lh", user.getPassword());
    }

    @Test
    void login() throws JsonProcessingException {
        Response response = client.login(getUserForTest());
        Assertions.assertNotNull(response);
        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertNotNull(DummyJsonMapper.getToken(response));
    }

    @Test
    void getPosts() throws JsonProcessingException {
        Response response = client.getPosts(getUserForTest(), tokenFromResponse);
        Post post = DummyJsonMapper.getPost(response);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertEquals(1, post.getId());
        Assertions.assertEquals(expectedPostTitle, post.getTitle());
        Assertions.assertEquals(9, post.getUserId());

    }

    private User getUserForTest(){
        return new User(1, "atuny0", "9uQFF1Lh");
    }
}

