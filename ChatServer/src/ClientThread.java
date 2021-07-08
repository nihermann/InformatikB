import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashSet;
import java.util.Objects;

public class ClientThread extends Thread{
    private final int id;
    private String name;

    private final ChatServer server;
    private final Socket mySocket;

    public HashSet<ClientThread> blackList = new HashSet<ClientThread>();

    private boolean hasLeft = false;


    public ClientThread(Socket socket, int id, ChatServer serverSocket){
        setDaemon(true);
        this.id = id;
        this.mySocket = socket;
        this.server = serverSocket;
    }

    public void run(){
        try(var inp = new BufferedReader(new InputStreamReader(this.mySocket.getInputStream()))) {
            while (!hasLeft){
                String message = inp.readLine();
                // edge cases:
                if (message == null) {
                    System.out.println("Stream Empty");
                    continue;
                }
                if(isCommand(message)) {
                    System.out.println("is command");
                    continue;
                }

                // message handling:
                server.writeAll(this, message);
                System.out.println(message);
            }
        } catch (SocketException e){
            System.out.println(name + " has left the chat room.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                this.mySocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isCommand(String message) {
        try {
            if (message.startsWith("CNAME ")) {
                name = message.substring(6);
                return true;
            } else if (message.startsWith("IGNORE ")) {
                blackList.add(server.getClientByName(message.substring(7)));
                return true;
            } else if (message.startsWith("LEAVE")) {
                hasLeft = true;
                return true;
            }
        } catch(Exception e){
            // send error to user.
            receive("There went something wrong with your Command.");
        }
        return false;
    }

    public void receive(String s) {
        try(var outStream = new OutputStreamWriter(mySocket.getOutputStream())) {
            outStream.write(s);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientThread that = (ClientThread) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
