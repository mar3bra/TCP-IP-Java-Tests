import java.io.IOException;


import net.Data;


public class Server{
    private static final String NAME = "Sever app";
    private static final int PORT = 3050;
    private Data data;

    
    private Server (){
        try { //Crear el socket del servidor
            data = new Data(PORT);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Server initializing...");
        Server server = new Server();
        server.data.dataThread.run();
    }

}
