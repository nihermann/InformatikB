package Network;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String argv[]) {
        try {
            ServerSocket servSock = new ServerSocket(6000);
            Socket sock = servSock.accept();
            var in = new LineNumberReader(new InputStreamReader(sock.getInputStream()));
            System.out.print(in.readLine());
            in.close();
            sock.close();
            servSock.close();
        } catch (IOException e) {
            System.err.println("Client: IO-Error" + e.getMessage());
            System.exit(1);
        }
    }
}
