import java.io.IOException;

import net.Data;


public class Client{
    
    private int testNum;

    private Thread thread;
    private Thread dataThread;

    private Data data;


    private Client(){

        testNum = 1;


        try {
            data = new Data("localhost", 3050, testNum);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        dataThread = new Thread(data, "Net");
    }
    public static void main(String[] args) throws Exception {
        Client client = new Client();
        client.dataThread.start();
        

    }
}


