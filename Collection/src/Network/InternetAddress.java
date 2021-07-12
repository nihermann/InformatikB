package Network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class InternetAddress {
    public static void main(String args[]) {
        try {
            String address = "www.uni-osnabasdrueck.de";
            InetAddress iaddr = InetAddress.getByName(address);

            System.out.println(iaddr.getHostName() + ": " + iaddr.getHostAddress());

            System.out.println("Reachable: " + iaddr.isReachable(2000));
        }
        catch (UnknownHostException e) {
            System.err.println("no such host");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
