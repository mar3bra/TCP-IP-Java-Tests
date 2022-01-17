package client;
import java.awt.Color; 

public class Clients {
    public static Client[] clients = new Client[0];


    public static void addClient(String name, byte color){
        Color cColor;
        if (color == 1){
            cColor  = new Color(255,0,0);
        } else {
            cColor  = new Color(0,0,255);
        }
        Client[] newArray = new Client[clients.length + 1];
        System.arraycopy(clients, 0, newArray, 0, clients.length);
        newArray[newArray.length - 1] = new Client(name, cColor);
        clients = newArray;
    }



    
}
