package Network.FromLecture.udp1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Receives a data packet, copies the data in an own packet, 
 * adds the time information to the packet and returns the packet to the client.
 */
public class TimeServer {
    
    private static final java.text.DateFormat DF = java.text.DateFormat
            .getDateTimeInstance();

    
    private static String getTime() {
        return DF.format(new java.util.Date());
    }

    public static void main(String[] args) {
        try {
            DatagramSocket ds = new DatagramSocket(4711);
            DatagramPacket dp = new DatagramPacket(new byte[20], 20);
            while (true) {
                ds.receive(dp);
                System.out.println("Request from port " + dp.getPort() + "  " + dp.getAddress());
                dp.setData(getTime().getBytes());
                ds.send(dp);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}