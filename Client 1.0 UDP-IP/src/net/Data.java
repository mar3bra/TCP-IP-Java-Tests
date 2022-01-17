package net;

import java.io.IOException;



import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Date;


public class Data implements Runnable {
    
    private final String IP;
    private final int PORT;
    private DatagramSocket socket;
    private DatagramPacket packet;
    public int token = 1;
    private int testNum = 0;
    private byte[] data = new byte[8];

    public Data(String ip, int port, int testNum) throws IOException{ //init Data
        this.IP = ip;
        this.PORT = port;

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
                System.out.println("PPS:" + Integer.toString(i));

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

        if(testNum == 0){
            
            long time = System.nanoTime();
            System.out.println(time);

            data = longToBytes(time);

            socket = new DatagramSocket(4555, InetAddress.getByName("localhost"));
            packet = new DatagramPacket(data, 8, InetAddress.getByName("localhost"), PORT);
            socket.send(packet);
            socket.close();
            return 1;
        }
        return 1;

    }

    public byte[] longToBytes(long x) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(x);
        return buffer.array();
    }
    
}
