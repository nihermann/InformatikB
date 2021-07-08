import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        Socket socket = new Socket(host, port);
        try(var inp = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            var out = new OutputStreamWriter(socket.getOutputStream())){
            Thread listener = new Thread(()->{
               while(true){
                   char[] buffer = new char[1024];
                   int anzahlZeichen = 0;
                   try {
                       anzahlZeichen = inp.read(buffer, 0, 1024);
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
                   String message = new String(buffer, 0, anzahlZeichen);

                   System.out.println(message);
               }
            });
            listener.setDaemon(true);
            listener.start();


            while(true){
                var cmdLine = new BufferedReader(new InputStreamReader(System.in));
                String message = cmdLine.readLine();
                System.out.println("send");
                out.write(message);
                out.flush();
            }

        } finally {
            socket.close();
        }
    }
}
