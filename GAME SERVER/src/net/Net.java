package net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;

import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.Date;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import clients.Client;
import clients.Clients;


public class Net implements Runnable {

    public Thread dataThread;
    private int token = 2;
    private final int PORT;
    private DatagramSocket serverSocket;
    private DatagramPacket Packet;
    private byte[] data;

    

    public Net(int port) throws IOException{ //init Data
        this.PORT = port;
        data = new byte[10];
        this.dataThread = new Thread(this, "Data Thread");
    }

    @Override
    public void run() {
        // Codi de transmisió de dades
        System.out.println("Listening to sockets...");

        try {
            while (true) {
                Packet = new DatagramPacket(data, 10);
                serverSocket = new DatagramSocket(PORT, InetAddress.getByName("localhost"));
                serverSocket.receive(Packet);
                byte[] dataPacket = Packet.getData();
                index(dataPacket, Packet, serverSocket);
                serverSocket.close(); 
                

                    
            }
        } catch (IOException e) {
                //TODO: handle exception
                System.out.println(e);
        }
        
        
    }

    private void index(byte[] dataPacket, DatagramPacket Packet, DatagramSocket serverSocket) throws IOException{
        if (dataPacket[0] == 0 ){

            if (Clients.clients.length == 1){
                System.out.println(Packet.getPort());
                Clients.newClientConnect = Packet.getPort();
  
                Clients.clients[0].request = 0;
            }
            System.out.println(Clients.newClientConnect);
            String nick = "";
            for (int i = 1; i < 5; i++) {
                nick += (char)dataPacket[i]; 
            }
            Clients.addClient(Packet.getAddress(), Packet.getPort(), nick, dataPacket[5]);
            
        
            
        } else if (dataPacket[0] == 1){
            //System.out.println("sasa");

            //System.out.println(Clients.newClientConnect == Packet.getPort());
            
                for (int i = 0; i < Clients.clients.length; i++) {
                    if (Clients.clients[i].PORT != Packet.getPort()){
                        Clients.clients[i].request = 1; 
                    }
                }
            
                
                Boolean left = dataPacket[1] == 0 ? false : true;
                Boolean right = dataPacket[2] == 0 ? false : true;
                Boolean up = dataPacket[3] == 0 ? false : true;
                Boolean down = dataPacket[4] == 0 ? false : true;

                for (int i = 0; i < Clients.clients.length; i++){
                    if (Clients.clients[i].PORT == Packet.getPort()){
                        Clients.clients[i].movePlayer(left, right, up, down);
                    }

                }
                
            
        }
    }
}
