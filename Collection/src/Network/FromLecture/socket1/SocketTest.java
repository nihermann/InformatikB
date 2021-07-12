package Network.FromLecture.socket1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Demonstrating the usage of a socket at a host (open socket at a port)
 */
public class SocketTest {

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String hostname = null;
        int port = 0;
        
        // read input
        try {
            System.out.print("Insert hostname: ");
            hostname = br.readLine();
            
            System.out.print("Insert port: ");
            port = Integer.parseInt(br.readLine());
        } catch (IOException e1) {
            System.err.println("Could not read from stdin. Bye!");
            System.exit(1);
        } catch(NumberFormatException e2) {
            System.out.println("Port is not a valid number!");
            System.exit(1);
        }

        // open socket
        try {
            // Socket socket = new Socket(InetAddress.getByName(hostname), port);
            
            // establish the new connection
            Socket socket = new Socket(hostname,port);
            System.out.println(socket);
            socket.close();
            
        } catch (UnknownHostException e) {
            System.out.println("Unknown host");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException");
            e.printStackTrace();
        }
    }

}
