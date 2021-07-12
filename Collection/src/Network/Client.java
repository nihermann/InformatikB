package Network;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String argv[]) {
        try {
            Socket socket = new Socket("localhost",6000);
            OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream());
            out.write("Hello world");
            out.flush();
            out.close();
            socket.close();
        }
        catch (UnknownHostException e) {
            System.err.println("Client: Unknown Host" + e.getMessage());
            System.exit(1);
        }
        catch (IOException e) {
            System.err.println("Client: IO-Error" + e.getMessage());
            System.exit(1);
        }
    }
}
