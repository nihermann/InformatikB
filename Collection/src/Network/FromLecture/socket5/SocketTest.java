package Network.FromLecture.socket5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Load a web page using a socket connection.
 */
public class SocketTest {

    public static void main(String[] args) {
        String hostname = null;
        String page = null;
        int port = 80;
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // read data
        try {
            System.out.print("Insert hostname: ");
            hostname = br.readLine();
            
            System.out.print("Insert web page: ");
            page = br.readLine();
        } catch (IOException e1) {
            System.err.println("Could not read from stdin. Bye!");
            System.exit(1);
        }
        
        
        try {
            // Socket socket = new Socket(InetAddress.getByName(hostname), port);
            Socket socket = new Socket(hostname, port);
            
            System.out.println("Connection established: " + socket);
            OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream());
            BufferedReader     in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                           
            // GET call to the web server
            out.write("GET "+ page + " \r\n\r\n");
            //  out.write("GET "+ page + " HTTP/1.0\r\n\r\n");
            out.flush();
            System.out.println("Query sent");
            
            // read result and write it to System.out
            String s;
            while((s = in.readLine()) != null) {
                System.out.println(s);
            }
            
            out.close();
            in.close();
            socket.close();
            
        } catch (UnknownHostException e) {
            System.err.println("Host " + hostname + " not known!");
        } catch (IOException e) {
            System.err.println("Cannot read from host " + hostname + "!");
        }
    }

}
