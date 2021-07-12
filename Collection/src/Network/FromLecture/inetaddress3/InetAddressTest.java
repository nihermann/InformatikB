package Network.FromLecture.inetaddress3;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * get InetAddress object from localhost
 */
public class InetAddressTest {

    public static void main(String[] args) {
        
            InetAddress address;
            try {
                address = InetAddress.getLocalHost();
                System.out.println("Localhost: " + address);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            
    }

}
