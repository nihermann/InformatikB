package Network.FromLecture.waiterServer;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * A simple time server: sequential processing of requests
 */
public class ServerTest {

    public static void main(String[] args) {

        try {
            ServerSocket server = new ServerSocket(12345);

            // endless loop: the server is always waiting
            for (;;) {
                Socket s = server.accept(); // wait for client connections and accept them
                System.out.println("Connected with socket: " + s);
                
                // get streams 
                OutputStreamWriter osw = new OutputStreamWriter(s.getOutputStream());
                osw.write((new Date()).toString());
                osw.flush();
                try {  // artificial delay to simulate some action
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                }
                // close all resources
                osw.close();
                s.close();
            }

        } catch (IOException e) {
            System.out.println("Socket could not be created");
        }
    }

}
