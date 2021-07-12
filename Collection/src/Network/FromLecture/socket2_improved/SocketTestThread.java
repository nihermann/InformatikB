package Network.FromLecture.socket2_improved;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Map;

/**
 * Thread testing the availability of a port.
 * If available the port number plus socket are stored in a Map.
 */
public class SocketTestThread extends Thread {
    private InetAddress inet;
    private int port;
    private Socket socket;
    private Map<Integer,Socket> map;
    
    public SocketTestThread(InetAddress address, int port, Map<Integer,Socket> map) {
        inet = address;
        this.port = port;
        this.map = map;
    }
    
    public void run() {
        try {
            socket = new Socket(inet,port);
            
            // successful socket connection => insert into map
            map.put(port,socket);
            socket.close();
            System.out.println("Thread testing port: " + port + " finished");
            
        } catch(UnknownHostException e1) {
            // do nothing and be quiet
        } catch(ConnectException e2) {
            // do nothing and be quiet            
        }catch (IOException e3) {
            // do nothing and be quiet
        } 
        
    }
}
