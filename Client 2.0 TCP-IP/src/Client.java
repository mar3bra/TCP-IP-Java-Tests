import java.io.IOException;

import net.Data;
import storage.Storage;

public class Client{
    
    private int testNum;

    private Thread thread;
    private Thread dataThread;

    private Data data;
    private Storage storage;
    

    private Client(){

        testNum = 1;
        storage = new Storage();


        try {
            data = new Data("localhost", 3050, testNum, storage);
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


