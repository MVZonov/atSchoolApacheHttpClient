import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DummyJsonMapper {
    private final  static ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    public static User getUser(Response response) throws JsonProcessingException {
        String payload = response.getBody();
        return mapper.readValue(payload, User.class);
    }

    public static Token getToken(Response response) throws JsonProcessingException {
        String payload = response.getBody();
        return mapper.readValue(payload, Token.class);
    }

    public static Post getPost(Response response) throws JsonProcessingException {
        String payload = response.getBody();
        return mapper.readValue(payload, Post.class);
    }
}
