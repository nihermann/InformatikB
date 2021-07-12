package Network.FromLecture.udp1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Send a packet and get the time back
 */
public class TimeClient {
    java.text.DateFormat DF = null;

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String hostname = null;
        InetAddress iaddr = null;

        if (args.length == 0) {
            System.out.print("Insert hostname: ");
            try {
                hostname = br.readLine();
            } catch (IOException e1) {
                System.err.println("Could not read from stdin. Bye!");
                System.exit(1);
            }
        } else {
            hostname = args[0];
        }
        
        try {
            iaddr = InetAddress.getByName(hostname);
        } catch (UnknownHostException e1) {
            System.err.println("Host " + hostname + " unknown, Bye!");
            System.exit(2);
        }

        try {
            DatagramPacket dp = new DatagramPacket(new byte[20],20,iaddr,4711);
            DatagramSocket ds = new DatagramSocket();
            ds.send(dp);
            ds.receive(dp);
            System.out.println(new String(dp.getData()));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
