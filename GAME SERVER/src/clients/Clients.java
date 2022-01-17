package clients;

import java.net.InetAddress;

public class Clients {
    public static int newClientConnect = -1;
    public static Client[] clients = new Client[0];

    
    public static void addClient(InetAddress ip, int port, String nick, byte c){




        Client[] newArray = new Client[clients.length + 1];
        System.arraycopy(clients, 0, newArray, 0, clients.length);
        newArray[newArray.length - 1] = new Client(ip, port, nick, c);
        clients = newArray;
        System.out.println("client inserted: " + clients[clients.length-1].IP.toString());
    }
}
