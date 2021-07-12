package Network.FromLecture.inetaddress2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Testing the reachability of a host.
 * It might be that the host is reachable but the answer of the host just takes longer than 1 second.
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
        
        // create InetAddress object
        try {
            InetAddress address = InetAddress.getByName(hostname);
            System.out.println(address);
            
            // test for reachability (waiting one second)
            if (address.isReachable(1000)) {
                System.out.println("host " + hostname + " is reachable");
            } else {
                System.out.println("host " + hostname + " is not reachable");
            }
        } catch (UnknownHostException e) {
            System.err.println("Unknown host");
            System.exit(2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
