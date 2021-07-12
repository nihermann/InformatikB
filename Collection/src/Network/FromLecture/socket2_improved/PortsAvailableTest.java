package Network.FromLecture.socket2_improved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Collections;

/**
 * Testing which ports are available for a host. 
 * We have one thread per port to make the test faster.
 */
public class PortsAvailableTest {

    public static void main(String[] args) {

        // map to store port/socket pairs
        Map<Integer, Socket> map = new TreeMap<Integer, Socket>();
        // synchronize the map as all our threads will access the map concurrently to insert (wrapper)
        map = Collections.synchronizedMap(map);
        
        // a list for all threads
        List<SocketTestThread> l = new LinkedList<SocketTestThread>();

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
        
        long time = System.currentTimeMillis();
        
        // create InetAddress object and start a thread per each port
        try {
            InetAddress address = InetAddress.getByName(hostname);
            for (int i = 0; i < 100; i++) {
                SocketTestThread s = new SocketTestThread(address,i,map);
                l.add(s);
                s.start();
                System.out.println("New thread now testing port number " + i);
            }

            // wait for all threads to finish their job
            for(SocketTestThread s: l) {
                s.join();
            }
            
            // print the results (free ports)
            System.out.println("Available ports:");
            for(Integer i:map.keySet()) {
                System.out.println("\t" + i);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println((System.currentTimeMillis()-time)/1000 + " second(s)");

    }

}
