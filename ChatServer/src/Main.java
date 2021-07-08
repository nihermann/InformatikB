import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ChatServer server = new ChatServer(6000);
        server.start();
    }
}
