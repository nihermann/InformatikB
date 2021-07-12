package Network.FromLecture.socket2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Testing which ports are open at a given host. 
 */
public class PortsAvailableTest {

    public static void main(String[] args) {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String hostname = null;

        // read hostname
        try {
            System.out.print("Insert hostname: ");
            hostname = br.readLine();
        } catch (IOException e1) {
            System.err.println("Could not read from stdin. Bye!");
            System.exit(1);
        }

        InetAddress address = null;

        long time = System.currentTimeMillis();
        
        // create InetAddress
        try {
            address = InetAddress.getByName(hostname);
        } catch (UnknownHostException e) {
            System.err.println("Host " + hostname + " not found. Bye!");
            System.exit(1);
        }
        
        // check all ports
        System.out.println("Available ports:");
        for (int i = 0; i < 100; i++) {
            Socket s;
            try {
                System.out.println("Testing port " + i);
                s = new Socket(address,i);
                
                // socket creation successful => port is open (otherwise we would have an IOException)
                System.out.println("\t Port available: " + s);
                s.close();
            } catch (IOException e) {
                // Be quiet
            }

        }
        System.out.println((System.currentTimeMillis()-time)/1000 + " second(s)");
    }

}
