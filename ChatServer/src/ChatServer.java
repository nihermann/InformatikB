import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;


public class ChatServer extends Thread {
    private final ConcurrentHashMap<Integer, ClientThread> clients = new ConcurrentHashMap<Integer, ClientThread>();
    private final ServerSocket serverSocket;

    public ChatServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    @Override
    public void run() {
        for(int id = 0;;id++) {
            Socket user;
            try {
                user = serverSocket.accept();

                System.out.println("Accepted: " + user);

                ClientThread client = new ClientThread(user, id, this);

                clients.put(id, client);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeAll(ClientThread sender, String message){
        for (ClientThread s: this.clients.values()){
            if(!s.blackList.contains(sender) || s == sender){
                s.receive(sender.getName() + ": " + message);
            }
        }
    }

    public ClientThread getClientByName(String name){
        for(ClientThread c: this.clients.values()){
            if(c.getName().equals(name)) return c;
        }
        return null;
    }
}
