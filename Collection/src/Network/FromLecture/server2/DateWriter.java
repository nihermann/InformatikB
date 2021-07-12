package Network.FromLecture.server2;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;

/**
 * Thread serving the clients requests
 */
public class DateWriter extends Thread {

    private Socket socket;

    public DateWriter(Socket s) {
        socket = s;
    }

    public void run() {
        try {
            // get streams to write to the socket
            OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
            
            // write to the socket via the stream
            osw.write((new Date()).toString());
            osw.flush();

            // artificial delay
            Thread.sleep(5000);
            
            // ready and clean-up
            osw.close();
            socket.close();
        } catch (InterruptedException e) {
        } catch (IOException e) {
            System.out.println("Socket could not be created");
        }

    }
}
