package Network.FromLecture.socket3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Demonstrating the communication with an echo server.
 */
public class SocketTest {

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String hostname = null;
        int port = 12345; // port for the echo server process
        
        // read hostname
        try {
            System.out.print("Insert hostname: ");
            hostname = br.readLine();
        } catch (IOException e1) {
            System.err.println("Could not read from stdin. Bye!");
            System.exit(1);
        }

        try {
            // Socket socket = new Socket(InetAddress.getByName(hostname), port);

            // establish socket connection
            final Socket socket = new Socket(hostname,port);
            System.out.println(socket);

            // get streams to read from and write to the socket
            final InputStream in = socket.getInputStream();
            OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream());

            // create thread which reads the data from the server and writes it to System.out
            Thread readerThread = new Thread() {
                public void run() {
                    int len = 0;
                    byte[] b = new byte[1024];
                    try {
                        while (!isInterrupted() && (len = in.read(b)) != -1) {
                            if (!isInterrupted()) {
                                System.out.write(b,0,len);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.err.println("read");
                }
            };

            readerThread.start();

            String input = null;
            
            // read data from System.in and send it to the echo server
            do {
                input = br.readLine();
                if (input != null) {
                    out.write(input + "\n");
                    out.flush();
                } else {
                    readerThread.interrupt();
                    out.write("end");
                    out.flush();
                }
            } while (input != null);

            try {
                readerThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            in.close();
            out.close();
            socket.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
