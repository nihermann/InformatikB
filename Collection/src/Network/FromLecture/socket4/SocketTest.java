package Network.FromLecture.socket4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * connect to a host and port and listen for information
 */
public class SocketTest {

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String hostname = null;
        int port = 0;
        
        // read data
        try {
            System.out.print("Insert hostname: ");
            hostname = br.readLine();
            
            System.out.print("Insert port: ");
            port = Integer.parseInt(br.readLine());
        } catch (IOException e1) {
            System.err.println("Could not read from stdin. Bye!");
            System.exit(1);
        } catch(NumberFormatException e2) {
            System.out.println("Port is not a valid number!");
            System.exit(1);
        }

        try {
            // Socket socket = new Socket(InetAddress.getByName(hostname), port);

            // establish socket connection
            Socket socket = new Socket(hostname,port);
            System.out.println(socket);
            
            // get streams to read from the socket connection
            InputStream in = socket.getInputStream();
            
            // read until EOF and print what was read
            int len = 0;
            byte[] b = new byte[1024];
            while((len=in.read(b))!=-1) {
                System.out.write(b,0,len);
            }
            in.close();
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
