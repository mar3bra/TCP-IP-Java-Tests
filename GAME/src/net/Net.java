package net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

import client.Client;
import client.Clients;
import controllers.Keyboard;

import java.net.DatagramPacket;
import java.net.DatagramSocket;



public class Net implements Runnable {
    
    private static String IP;
    private static int PORT;
    private static int DPORT;

    private DatagramSocket socket;
    private DatagramPacket packet;
    private int testNum = 0;
    private byte[] data = new byte[10];


    public Net(){ //init Data

    }

    @Override
    public void run() {
        data = new byte[10];
        data[0] = (byte)testNum;

        if (testNum == 0){
            data[1] = 'p';
            data[2] = 'a';
            data[3] = 'c';
            data[4] = 'o';
            data[5] = 2;
            
        } else if (testNum == 1){
            data[1] = Keyboard.keys['a'] == null ? (byte)0 : Keyboard.keys['a'] == false ? (byte)0 : (byte)1;
            data[2] = Keyboard.keys['d'] == null ? (byte)0 : Keyboard.keys['d'] == false ? (byte)0 : (byte)1;
            data[3] = Keyboard.keys['w'] == null ? (byte)0 : Keyboard.keys['w'] == false ? (byte)0 : (byte)1;
            data[4] = Keyboard.keys['s'] == null ? (byte)0 : Keyboard.keys['s'] == false ? (byte)0 : (byte)1;

        }
            try {
                
                socket = new DatagramSocket(DPORT, InetAddress.getByName(IP));
                packet = new DatagramPacket(data, 10, InetAddress.getByName(IP), PORT);
                socket.send(packet);

                data = new byte[10];

                
                packet = new DatagramPacket(data, 10, InetAddress.getByName(IP), PORT);
                socket.receive(packet);

                byte[] a = packet.getData();
                
                System.out.println(a[0]);
                   
                if (a[0] == 0){

                    if (a[1] != -1){
                        System.out.println("holis");
                        String nick = "";
                        for (int i = 1; i < 5; i++) {
                            nick += (char)a[i]; 
                        }
                        Clients.addClient(nick, a[5]);
                    }
                    testNum = 1;
                    
                }


                if (a[0] == 1){
                    System.out.println(a[1]);
                    System.out.println(a[2]);
                    System.out.println(a[3]);

                    for (int i = 0; i < 2; i++) {
                        if (a[i * 3 + 1] != -1){
                            if (a[i * 3 + 1] == 1){
                                Clients.clients[0].updatePosition((int)a[i * 3 + 2], (int)a[i * 3 + 3]);
                            } else {
                                Clients.clients[1].updatePosition((int)a[i * 3 + 2], (int)a[i * 3 + 3]);
                            }
                        }
                    }
                    
                }
                socket.close();

            } catch (UnknownHostException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }    
            

    }


    public static void setConfig(String ip, int port, int dport) {
        IP = ip;
        PORT = port;
        DPORT = dport;

    }

}
