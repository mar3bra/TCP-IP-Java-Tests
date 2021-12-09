package net;

import java.io.IOException;
import java.net.Socket;

import storage.Storage;

import java.io.DataInputStream;
import java.io.DataOutputStream;



public class Data implements Runnable {
    
    private final String IP;
    private final int PORT;
    private Socket clientSocket;
    public int token = 0;
    private int testNum;
    private Storage storage;


    public Data(String ip, int port, int testNum, Storage storage) throws IOException{ //init Data
        this.IP = ip;
        this.PORT = port;
        this.storage = storage;

        Socket socket = new Socket(IP,PORT);
        DataOutputStream os = new DataOutputStream(socket.getOutputStream());
        os.writeByte(0);

        DataInputStream is = new DataInputStream(socket.getInputStream());
        token = is.readByte();
        socket.close();

        this.testNum = testNum;



    }

    @Override
    public void run() {
        System.out.println("client started");

        long nanoTime = System.nanoTime();
        long delta = 0;
        final int nano = 1_000_000_000;
        int i = 1; //Per el test de latència li asignem un
        int aps = nano / i;
        
        
        while (token != 0){
            //El codi de repetició de conexió va aquí
            long startNanoTime = System.nanoTime();
            delta += startNanoTime - nanoTime;
            nanoTime = startNanoTime;
            
            if (delta >= aps ){
                delta -= aps;
                i++; //Per el test de latència comentem aquesta linea
                aps = nano / i;
                try {
                    token = query(testNum);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    token = 0;
                    e.printStackTrace();
                    System.out.println("PPS:" + Integer.toString(i));
                }
                
            }
        }
        
    }

    ///Casos de debolucions-----------------------------------------------------------------
    private int query(int token) throws IOException{

        if(testNum == 1){
            token = 0;
            long startNs = System.nanoTime(); 
            Socket socket = new Socket(IP,PORT);
            DataOutputStream os = new DataOutputStream(socket.getOutputStream());
            os.writeByte(1);
            socket.close();
            long finishNs = System.nanoTime(); 
            int delta = (int) finishNs - (int)startNs;
            storage.addPing(delta);
            /*
            if(storage.getPingArray().length >= 1000){ //Per el test de latència descomenem aquesta secció
                System.out.println("saving");
                storage.save();
                return 0;
            } 
            */

            return 1;
        }
        return 1;

    }
    
}
