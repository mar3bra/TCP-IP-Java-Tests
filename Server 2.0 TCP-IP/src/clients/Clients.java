package clients;

import java.net.InetAddress;

public class Clients {
    public static Client[] clients = new Client[0];

    
    public static void addClient(InetAddress ip){
        Client[] newArray = new Client[clients.length + 1];
        System.arraycopy(clients, 0, newArray, 0, clients.length);
        newArray[newArray.length - 1] = new Client(ip);
        clients = newArray;
        System.out.println("client inserted: " + clients[clients.length-1].IP.toString());
    }
}
