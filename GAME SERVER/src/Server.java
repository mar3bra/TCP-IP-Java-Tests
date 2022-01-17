import java.io.IOException;

import clients.Clients;
import net.Net;
import net.Send;


public class Server{
    private static final String NAME = "game server";
    private static final int PORT = 3050;
    private Net data;
    private Send send;


    
    private Server (){
        try { //Crear el socket del servidor
            data = new Net(PORT);
            send = new Send();
            

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Server initializing...");
        Server server = new Server();
        server.send.sendThread.run();

    }

}
