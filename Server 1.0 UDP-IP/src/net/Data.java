package net;

import java.io.IOException;

import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.Date;
import java.net.DatagramPacket;
import java.net.DatagramSocket;





public class Data implements Runnable {

    private final int PORT;
    public Thread dataThread;
    private DatagramSocket serverSocket;
    private DatagramPacket paquet;
    private byte[] data;

    

    public Data(int port) throws IOException{ //init Data
        this.PORT = port;
        data = new byte[8];
        this.dataThread = new Thread(this, "Data Thread");
    }

    @Override
    public void run() {
        // Codi de transmisió de dades
        System.out.println("Listening to sockets...");
        try {
            while (true) {
                paquet = new DatagramPacket(data, 8);
                serverSocket = new DatagramSocket(PORT, InetAddress.getByName("localhost"));
                serverSocket.receive(paquet);
                serverSocket.close(); 
                long time = System.nanoTime();
                System.out.println("client connected");
                long timeClient = bytesToLong(paquet.getData());

                System.out.println(time - timeClient);

            }
        } catch (IOException e) {
            //TODO: handle exception
            System.out.println(e);
        }

        
    }

    public long bytesToLong(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.put(bytes);
        buffer.flip();
        return buffer.getLong();
    }
    
}
