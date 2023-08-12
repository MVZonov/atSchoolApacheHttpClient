import com.fasterxml.jackson.core.JsonProcessingException;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {

        int id = 18;
        DummyJsonClient dummyJsonClient = new DummyJsonClientImpl();

        User userFromResponse = DummyJsonMapper.getUser(dummyJsonClient.getUser(id));
        System.out.println(userFromResponse);
        Response userData = dummyJsonClient.login(userFromResponse);

        Token tokenFromResponse = DummyJsonMapper.getToken(userData);
        System.out.println(tokenFromResponse);

        Response posts = dummyJsonClient.getPosts(userFromResponse, tokenFromResponse);
        System.out.println(posts.getBody());
    }
}
