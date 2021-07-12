package Network.FromLecture.server2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TimeServer with threads for each request.
 */
public class ServerTest {

    public static void main(String[] args) {

        ServerSocket server = null;
        try {
            server = new ServerSocket(12346);
        } catch (IOException e1) {
            e1.printStackTrace();
            System.exit(1);
        }

        // always accepting
        for (;;) {
            Socket s;
            try {
                // wait for clients to establish a connection
                s = server.accept();
                System.out.println("Connected with socket: " + s);
                
                // new connection => start a new thread to serve the request (Servant)
                new DateWriter(s).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
