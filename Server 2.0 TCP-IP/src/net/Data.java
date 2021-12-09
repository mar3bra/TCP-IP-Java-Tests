package net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import clients.Clients;


public class Data implements Runnable {

    private final int PORT;
    public Thread dataThread;
    private ServerSocket serverSocket;
    private Socket clientSocket;

    public Data(int port) throws IOException{ //init Data
        this.PORT = port;
        this.dataThread = new Thread(this, "Data Thread");
    }

    @Override
    public void run() {
        // Codi de transmisió de dades
        System.out.println("Listening to sockets...");
        try {
            serverSocket = new ServerSocket(PORT);
            while ((clientSocket = serverSocket.accept()) != null) { // El objecte de clientSocket serà el entrant per el mètode .accept
                System.out.println("socket connected");
                DataInputStream is = new DataInputStream(clientSocket.getInputStream());
                //DataOutputStream os = new DataOutputStream(socket.getOutputStream());
                Byte index = is.readByte();
                index(index, clientSocket);
                clientSocket.close();
        }
            
        } catch (IOException e) {
            //TODO: handle exception
            System.out.println(e);
        }

        
    }


    private void index(int i, Socket clientSocket) throws IOException{

        if (i == 0){ //Enparellar la pertició amb el 
            DataOutputStream os = new DataOutputStream(clientSocket.getOutputStream());
            os.writeByte(1);
            System.out.println("New client connected");
            InetAddress name = clientSocket.getInetAddress();
            Clients.addClient(name);
        } else if (i == 1){ //Test de latencia
            
        }   
        

    }
    
}
