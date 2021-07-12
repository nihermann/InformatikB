package Network.FromLecture.inetaddress1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Demonstrates the creation of an InetAddress object using a hostname
 */
public class InetAddressTest {

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
        
        // create InetAdress object and print the address to System.out
        try {
            InetAddress address = InetAddress.getByName(hostname);
            System.out.println(address);
        } catch (UnknownHostException e) {
            System.err.println("Host unknown");
            System.exit(2);
        }
    }

}
