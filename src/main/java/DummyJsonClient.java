import java.io.IOException;

public interface DummyJsonClient {
    // dummyjson.com/users/{id}
    Response getUser(int id);

    // dummyjson.com/auth/login/
    Response login(User u);

    // dummyjson.com/auth/posts/{user.id}
    Response getPosts(User u, Token token);
}