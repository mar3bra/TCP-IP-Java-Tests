package net;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.Date;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import clients.Clients;

public class Send implements Runnable {

    public Thread sendThread = new Thread(this, "send thread");
    private Net data;
    private DatagramSocket serverSocket;

    @Override
    public void run() {
        // TODO Auto-generated method stub
        long nanoTime = System.nanoTime();
        long delta = 0;
        final int nano = 1_000_000_000;
        int i = 50; 
        int aps = nano / i;
        
        
        while (true){
            //El codi de repetició de conexió va aquí
            long startNanoTime = System.nanoTime();
            delta += startNanoTime - nanoTime;
            nanoTime = startNanoTime;


            try {
                serverSocket = new DatagramSocket(9000, InetAddress.getByName("localhost"));

                if (delta >= aps ){
                    for (int a = 0; a < Clients.clients.length; a++) {
                    
                    if (Clients.clients[a].request == 1){
                        byte[] sendPacketData = new byte[10];

                        sendPacketData[0] = (byte)1; 
                        for(int j = 1; j < 10; j++){
                            sendPacketData[j] = (byte)-1;
                        }

                        for (int j = 0; j < Clients.clients.length; j++){
                            if (Clients.clients[j].PORT == Clients.clients[a].PORT){
                                sendPacketData[1+0*3] = 1;
                            } else {
                                sendPacketData[1+1*3] = 0;
                            }
            
                                sendPacketData[1 + j*3 + 1] = (byte)Clients.clients[j].x;
                                sendPacketData[1 + j*3 + 2] = (byte)Clients.clients[j].y;
            
                        }
            
                        DatagramPacket sendPacket = new DatagramPacket(sendPacketData, 10, Clients.clients[a].IP, Clients.clients[a].PORT);
                        serverSocket.send(sendPacket); 
                    }   

                    if (Clients.clients[a].request == 0){
                            byte[] sendPacketData = new byte[10];
                            sendPacketData[0] = 0;
                            for(int j = 1; j < 10; j++){
                                sendPacketData[j] = (byte)-1;
                            }
                            for (int x = 0; x < Clients.clients.length; x++){
                                if (Clients.clients[x].PORT != Clients.clients[a].PORT){
                                    for (int j = 0; j < Clients.clients[x].NICK.length(); j++) {
                                        sendPacketData[1 + j] = (byte)Clients.clients[x].NICK.charAt(j);
                                        System.out.println(x);
                                        sendPacketData[5] = Clients.clients[x].COLOR;
                                    }

                                }
                            }
                            DatagramPacket sendPacket = new DatagramPacket(sendPacketData, 10, Clients.clients[a].IP, Clients.clients[a].PORT);
                            serverSocket.send(sendPacket);  
                            Clients.clients[a].request = 1; 
                    }
                    
                }   
                    delta -= aps;
                    //System.out.println("a");
    
                }
                serverSocket.close();
            } catch (SocketException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (UnknownHostException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
                


            
        }
        
    }

    public Send() throws IOException{
        data = new Net(3050);
        data.dataThread.start();

    }
    
}
